package com.cq.vsa.service;

import java.io.Serializable;
import java.util.List;

/**
 * service通用增删改接口
 * @Author: chengqi
 * @Date: Created in 16:21 2019/4/12
 */
public interface ICUDService<E, I extends Serializable> extends IService<E, I> {

    /**
     * 保存
     * @param e
     */
    void save(E e);

    /**
     * 更新
     * @param e
     */
    void update(E e);

    /**
     * 删除
     * @param e
     */
    void delete(E e);

    /**
     * 删除指定的所有数据
     * @param list
     */
    void deleteAll(List<E> list);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(I id);
}
