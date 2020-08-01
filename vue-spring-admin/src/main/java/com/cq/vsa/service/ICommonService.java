package com.cq.vsa.service;

import java.io.Serializable;

/**
 * service通用增删改查接口
 * @Author: chengqi
 * @Date: Created in 16:51 2019/4/10
 */
public interface ICommonService <E, I extends Serializable> extends IQueryService<E, I>, ICUDService<E, I> {

}
