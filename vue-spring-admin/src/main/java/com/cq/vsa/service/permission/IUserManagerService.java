package com.cq.vsa.service.permission;

import com.cq.vsa.domain.permission.UpRole;
import com.cq.vsa.domain.permission.UpUser;
import com.cq.vsa.service.ICommonService;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 17:03 2019/4/10
 */
public interface IUserManagerService extends ICommonService<UpUser, Integer> {

    /**
     * 查询所有的角色
     * @return
     */
    List<UpRole> queryAllRoles();
}
