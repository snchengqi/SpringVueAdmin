package com.cq.vsa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @Author: chengqi
 * @Date: Created in 17:32 2019/4/19
 */
@NoRepositoryBean
@Transactional(readOnly = true, rollbackFor = Exception.class)
public interface BaseJpaRepository<T, ID extends Serializable> extends DynamicQueryRepository<T, ID>, JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
