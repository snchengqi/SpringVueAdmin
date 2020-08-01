package com.cq.vsa.service.permission;

import com.cq.vsa.domain.permission.UpMenu;
import com.cq.vsa.service.ICUDService;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 9:10 2019/4/12
 */
public interface IMenuManagerService extends ICUDService<UpMenu, Integer> {

    /**
     * 获取菜单树
     * @return
     */
    List<UpMenu> getMenuTree();
}
