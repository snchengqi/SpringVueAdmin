package com.cq.vsa.jpa.repository;

import com.cq.vsa.jpa.annotation.QueryField;
import com.cq.vsa.jpa.domain.*;
import com.cq.vsa.jpa.enumobj.QueryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 16:48 2019/4/20
 */
public class BaseSimpleJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseJpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseSimpleJpaRepository.class);

    public BaseSimpleJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseSimpleJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public List<T> findAll(DataQuery dataQuery) {
        return this.findAll(new BaseSimpleJpaRepositorySpecification(dataQuery));
    }

    @Override
    public List<T> findAll(DataQuery dataQuery, Sort sort) {
        return this.findAll(new BaseSimpleJpaRepositorySpecification(dataQuery), sort);
    }

    @Override
    public Page<T> findAll(DataQuery dataQuery, Pageable pageable) {
        return this.findAll(new BaseSimpleJpaRepositorySpecification(dataQuery), pageable);
    }

    @Override
    public List<T> findAll(SortableDataQuery dataQuery) {
        String order = dataQuery.getOrder();
        String direction = StringUtils.isEmpty(dataQuery.getDirection())? SortableDataQuery.DEFAULT_DIRECTION: dataQuery.getDirection();
        if (StringUtils.isEmpty(order)) {
            return this.findAll(new BaseSimpleJpaRepositorySpecification(dataQuery));
        }
        Sort sort = Sort.by(Sort.Direction.fromString(direction), order);
        return this.findAll(dataQuery, sort);
    }

    @Override
    public Page<T> findAll(PageableDataQuery dataQuery) {
        return this.findAll(dataQuery, PageRequest.of(dataQuery.getPage(), dataQuery.getSize()));
    }

    @Override
    public Page<T> findAll(PageAndSortDataQuery dataQuery) {
        String order = dataQuery.getOrder();
        String direction = StringUtils.isEmpty(dataQuery.getDirection())? SortableDataQuery.DEFAULT_DIRECTION: dataQuery.getDirection();
        Pageable pageable = StringUtils.isEmpty(order)? PageRequest.of(dataQuery.getPage(), dataQuery.getSize()):
                PageRequest.of(dataQuery.getPage(), dataQuery.getSize(), Sort.by(Sort.Direction.fromString(direction), order));
        return this.findAll(dataQuery, pageable);
    }

    /**
     *Inner BaseSimpleJpaRepositorySpecification
     */
    protected class BaseSimpleJpaRepositorySpecification implements Specification<T> {

        DataQuery dataQuery;

        BaseSimpleJpaRepositorySpecification(DataQuery dataQuery) {
            this.dataQuery = dataQuery;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return generatePredicate(dataQuery, root, query, criteriaBuilder);
        }
    }

    /**
     * 核心方法，拼接条件
     * @param dataQuery
     * @param root
     * @param query
     * @param criteriaBuilder
     * @return
     */
    protected Predicate generatePredicate(DataQuery dataQuery, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        //get all fields of data query object
        Field[] fields = dataQuery.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);

            String queryField;
            QueryType queryType;
            Object value;

            QueryField annotation = field.getAnnotation(QueryField.class);
            if (annotation == null) {
                continue;
            }
            if (!StringUtils.isEmpty(annotation.name())) {
                queryField = annotation.name();
            } else {
                queryField = field.getName();
            }
            queryType = annotation.type();
            try {
                value = field.get(dataQuery);
            } catch (IllegalAccessException e) {
                logger.error(String.format("Get value of field named %s for DataQuery %s error", field.getName(), dataQuery.getClass()), e);
                throw new RuntimeException(e);
            }
            if (value == null) {
                if (!annotation.nullAble()) {
                    throw new IllegalArgumentException(String.format("field %s of %s can not be null", field.getName(), dataQuery.getClass()));
                }
                if (queryType != QueryType.IS_NULL && queryType != QueryType.IS_NOT_NULL) {
                    continue;
                }
            }

            predicates.add(generatePredicate(value, queryField, queryType, root, criteriaBuilder));
        }
        if (predicates.size() == 0) {
            return criteriaBuilder.and();
        }
        Predicate[] result = predicates.toArray(new Predicate[predicates.size()]);
        return criteriaBuilder.and(result);
    }

    /**
     * 判断注解中 的条件类型
     * @param value
     * @param queryField
     * @param queryType
     * @param root
     * @param criteriaBuilder
     * @return
     */
    private Predicate generatePredicate(Object value, String queryField, QueryType queryType, Root<T> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;
        /**
         * 判断注解中 的条件类型
         */
        switch (queryType) {
            case EQUAL:
                Path<Object> equal = getPathByQueryField(queryField, root);
                predicate = criteriaBuilder.equal(equal, value);
                break;
            case NOT_EQUAL:
                Path<Object> notEqual = getPathByQueryField(queryField, root);
                predicate = criteriaBuilder.notEqual(notEqual, value);
                break;
            case BETWEEN:
                Path<Comparable> between = getPathByQueryField(queryField, root);
                checkType(value, QueryBetween.class, queryType);
                QueryBetween qb = (QueryBetween) value;
                predicate = criteriaBuilder.between(between, qb.getAfter(), qb.getBefore());
                break;
            case LESS_THAN:
                Path<Comparable> lessThan = getPathByQueryField(queryField, root);
                checkType(value, Comparable.class, queryType);
                predicate = criteriaBuilder.lessThan(lessThan, (Comparable) value);
                break;
            case LESS_THAN_EQUAL:
                Path<Comparable> lessThanEqual = getPathByQueryField(queryField, root);
                checkType(value, Comparable.class, queryType);
                predicate = criteriaBuilder.lessThanOrEqualTo(lessThanEqual, (Comparable) value);
                break;
            case GREATER_THAN:
                Path<Comparable> greaterThan = getPathByQueryField(queryField, root);
                checkType(value, Comparable.class, queryType);
                predicate = criteriaBuilder.greaterThan(greaterThan, (Comparable) value);
                break;
            case GREATER_THAN_EQUAL:
                Path<Comparable> greaterThanEqual = getPathByQueryField(queryField, root);
                checkType(value, Comparable.class, queryType);
                predicate = criteriaBuilder.greaterThanOrEqualTo(greaterThanEqual, (Comparable) value);
                break;
            case IS_NULL:
                Path<Object> isNull = getPathByQueryField(queryField, root);
                predicate = criteriaBuilder.isNull(isNull);
                break;
            case IS_NOT_NULL:
                Path<Object> isNotNull = getPathByQueryField(queryField, root);
                predicate = criteriaBuilder.isNotNull(isNotNull);
                break;
            case LEFT_LIKE:
                Path<String> leftLike = getPathByQueryField(queryField, root);
                checkType(value, String.class, queryType);
                predicate =criteriaBuilder.like(leftLike, "%" + value);
                break;
            case RIGHT_LIKE:
                Path<String> rightLike = getPathByQueryField(queryField, root);
                checkType(value, String.class, queryType);
                predicate =criteriaBuilder.like(rightLike, value + "%");
                break;
            case FULL_LIKE:
                Path<String> fullLike = getPathByQueryField(queryField, root);
                checkType(value, String.class, queryType);
                predicate =criteriaBuilder.like(fullLike, "%" + value + "%");
                break;
            case DEFAULT_LIKE:
                Path<String> defaultLike = getPathByQueryField(queryField, root);
                checkType(value, String.class, queryType);
                predicate =criteriaBuilder.like(defaultLike, value.toString());
                break;
            case NOT_LIKE:
                Path<String> notLike = getPathByQueryField(queryField, root);
                checkType(value, String.class, queryType);
                predicate =criteriaBuilder.notLike(notLike, value.toString());
                break;
            case IN:
                Path<Object> in = getPathByQueryField(queryField, root);
                CriteriaBuilder.In<Object> ins = criteriaBuilder.in(in);
                if (!(value instanceof Iterable) && !(value instanceof Object[])) {
                    throw new IllegalArgumentException(String.format("%s must act on %s or Array", queryType, Iterable.class));
                }
                if (value instanceof Iterable) {
                    ((Iterable) value).forEach(ins::value);
                }
                if (value instanceof Object[]) {
                    Arrays.stream((Object[]) value).forEach(ins::value);
                }
                break;
            default:
                break;
        }
        return predicate;
    }

    private void checkType(Object value, Class clazz, QueryType queryType) {
        if (!clazz.isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException(String.format("%s must act on %s", queryType, clazz));
        }
    }

    private <Y> Path<Y> getPathByQueryField(String queryField, Path<T> path) {
        if (!queryField.contains(".")) {
            return path.get(queryField);
        }
        return getPathByQueryField(queryField.substring(queryField.indexOf(".") + 1), path.get(queryField.substring(0, queryField.indexOf("."))));
    }
}
