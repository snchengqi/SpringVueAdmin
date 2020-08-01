package com.cq.vsa.dao.repository.test;

import com.cq.vsa.jpa.annotation.SecurityFilter;
import com.cq.vsa.jpa.domain.DataQuery;
import com.cq.vsa.jpa.repository.BaseJpaRepository;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 9:09 2019/4/22
 */
public interface TestBeanRepository extends BaseJpaRepository<TestBean, Long> {

    @SecurityFilter
    @Override
    List<TestBean> findAll(DataQuery dataQuery);

    @SecurityFilter
    @Override
    List<TestBean> findAll();
}
