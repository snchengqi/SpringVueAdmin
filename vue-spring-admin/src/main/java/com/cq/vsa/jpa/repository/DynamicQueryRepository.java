package com.cq.vsa.jpa.repository;

import com.cq.vsa.jpa.domain.DataQuery;
import com.cq.vsa.jpa.domain.PageAndSortDataQuery;
import com.cq.vsa.jpa.domain.PageableDataQuery;
import com.cq.vsa.jpa.domain.SortableDataQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 14:01 2019/4/23
 */
@NoRepositoryBean
@Transactional(readOnly = true, rollbackFor = Exception.class)
public interface DynamicQueryRepository<T, ID> extends Repository<T, ID> {

    /**
     * common dynamic query
     * @param dataQuery
     * @return
     */
    List<T> findAll(DataQuery dataQuery);

    /**
     * sortable dynamic query
     * @param dataQuery
     * @param sort
     * @return
     */
    List<T> findAll(DataQuery dataQuery, Sort sort);

    /**
     * pageable dynamic query
     * @param dataQuery
     * @param pageable
     * @return
     */
    Page<T> findAll(DataQuery dataQuery, Pageable pageable);

    /**
     * sortable dynamic query
     * @param dataQuery
     * @return
     */
    List<T> findAll(SortableDataQuery dataQuery);

    /**
     * pageable dynamic query
     * @param dataQuery
     * @return
     */
    Page<T> findAll(PageableDataQuery dataQuery);

    /**
     * pageable and sortable dynamic query
     * @param dataQuery
     * @return
     */
    Page<T> findAll(PageAndSortDataQuery dataQuery);
}
