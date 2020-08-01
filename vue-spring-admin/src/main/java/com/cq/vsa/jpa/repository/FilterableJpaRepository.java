package com.cq.vsa.jpa.repository;

import com.cq.vsa.jpa.annotation.SecurityFilter;
import com.cq.vsa.jpa.domain.OrgBelong;
import com.cq.vsa.jpa.util.UserOrgUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: chengqi
 * @Date: Created in 16:48 2019/4/21
 */
public class FilterableJpaRepository<T, ID extends Serializable> extends BaseSimpleJpaRepository<T, ID> {

    private static final String ORG_CODE = "orgCode";

    public FilterableJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public FilterableJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public Optional<T> findById(ID id) {
        return process(super.findById(id));
    }

    @Override
    public T getOne(ID id) {
        return process(super.getOne(id));
    }

    @Override
    public List<T> findAll() {
        return super.findAll().stream().filter(item -> process(item) != null).collect(Collectors.toList());
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return super.findAllById(ids).stream().filter(item -> process(item) != null).collect(Collectors.toList());
    }

    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll(sort).stream().filter(item -> process(item) != null).collect(Collectors.toList());
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return super.findAll((Specification<T>) (root, query, criteriaBuilder) -> root.get(ORG_CODE).in(UserOrgUtil.getOrgNexus()), pageable);
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return process(super.findOne(spec));
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return super.findAll(spec).stream().filter(item -> process(item) != null).collect(Collectors.toList());
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return super.findAll(((Specification<T>) (root, query, criteriaBuilder) -> root.get(ORG_CODE).in(UserOrgUtil.getOrgNexus())).and(spec), pageable);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return super.findAll(spec, sort).stream().filter(item -> process(item) != null).collect(Collectors.toList());
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        Optional<S> optional = super.findOne(example);
        if (!optional.isPresent()) {
            return optional;
        }
        if (needFilter()) {
            S s = optional.get();
            checkEntityType(s);
            OrgBelong org = (OrgBelong) s;
            return UserOrgUtil.matches(org)? optional: Optional.empty();
        }
        return optional;
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        TypedQuery<Long> countQuery = getCountQuery(((Specification<S>) (root, query, criteriaBuilder) -> root.get(ORG_CODE).in(UserOrgUtil.getOrgNexus())).
                and(new ExampleSpecification<>(example)), example.getProbeType());
        return executeCountQuery(countQuery);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return super.findAll(example).stream().filter(item -> process(item) != null).collect(Collectors.toList());
    }

    private T process(T t) {
        if (t == null) {
            return null;
        }
        if (needFilter()) {
            checkEntityType(t);
            OrgBelong org = (OrgBelong) t;
            return UserOrgUtil.matches(org) ? t: null;
        }
        return t;
    }

    private Optional<T> process(Optional<T> optional) {
        if (!optional.isPresent()) {
            return optional;
        }
        if (needFilter()) {
            T t = optional.get();
            checkEntityType(t);
            OrgBelong org = (OrgBelong) t;
            return UserOrgUtil.matches(org)? optional: Optional.empty();
        }
        return optional;
    }

    private boolean needFilter() {
        Method method = getRepositoryMethodMetadata().getMethod();
        SecurityFilter annotation = method.getAnnotation(SecurityFilter.class);
        return annotation != null;
    }

    private void checkEntityType(T t) {
        if (!(t instanceof OrgBelong)) {
            throw new IllegalStateException(String.format("Entity %s must instanceof %s", t.getClass(), OrgBelong.class));
        }
    }

    /**
     * Executes a count query and transparently sums up all values returned.
     *
     * @param query must not be {@literal null}.
     * @return
     */
    private static long executeCountQuery(TypedQuery<Long> query) {

        Assert.notNull(query, "TypedQuery must not be null!");

        List<Long> totals = query.getResultList();
        long total = 0L;

        for (Long element : totals) {
            total += element == null ? 0 : element;
        }

        return total;
    }

    /**
     * copy from spring data jpa
     * @param <S>
     */
    private class ExampleSpecification<S extends T> implements Specification<S> {

        private static final long serialVersionUID = 1L;

        private final Example<S> example;

        /**
         * Creates new {@link SimpleJpaRepository.ExampleSpecification}.
         *
         * @param example
         */
        ExampleSpecification(Example<S> example) {

            Assert.notNull(example, "Example must not be null!");
            this.example = example;
        }

        /**
         * (non-Javadoc)
         * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
         * @param root
         * @param query
         * @param cb
         * @return
         */
        @Override
        public Predicate toPredicate(Root<S> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return QueryByExamplePredicateBuilder.getPredicate(root, cb, example);
        }
    }
}
