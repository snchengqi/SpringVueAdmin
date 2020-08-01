package com.cq.vsa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * 通用查询接口
 * @Author: chengqi
 * @Date: Created in 16:10 2019/4/12
 */
public interface IQueryService<E, I extends Serializable> extends IService<E, I> {

    /**
     * 动态查询，用于分页
     * @param e
     * @param pageable
     * @return
     */
    Page<E> query(E e, Pageable pageable);

    /**
     * 查询所有数据
     * @return
     */
    List<E> queryAll();
}
