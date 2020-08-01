package com.cq.vsa.service.permission.impl;

import com.cq.vsa.common.ErrorStatus;
import com.cq.vsa.dao.repository.permission.UpMenuRepository;
import com.cq.vsa.domain.permission.UpMenu;
import com.cq.vsa.enumobj.permission.MenuType;
import com.cq.vsa.service.permission.IMenuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chengqi
 * @Date: Created in 9:11 2019/4/12
 */
@Service
public class MenuManagerService implements IMenuManagerService {

    private UpMenuRepository repository;

    @Autowired
    public void setRepository(UpMenuRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UpMenu> getMenuTree() {
        List<UpMenu> allMenus = repository.findAll();
        //过滤出一级菜单
        List<UpMenu> firstMenus = allMenus.stream().filter(item -> item.getLevel() == MenuType.FIRST_LEVEL.getLevel()).
                sorted(Comparator.comparingInt(UpMenu::getOrderNo)).
                collect(Collectors.toList());
        for (UpMenu firstMenu: firstMenus) {
            List<UpMenu> secondMenus = allMenus.stream().filter(item -> firstMenu.getId().equals(item.getParentId()) && item.getLevel() == MenuType.SECOND_LEVEL.getLevel()).
                    sorted(Comparator.comparingInt(UpMenu::getOrderNo)).
                    collect(Collectors.toList());
            firstMenu.setChildren(secondMenus);
        }
        return firstMenus;
    }

    @Override
    public void save(UpMenu menu) {
        if (menu.getId() != null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        if (repository.existsByName(menu.getName())) {
            ErrorStatus.MENU_EXIST.throwEx();
        }
        switch (menu.getLevel()) {
            case 1:
                menu.setHidden(-1);
                menu.setNoCache(-1);
                menu.setParentId(-1);
                break;
            case 2:
                menu.setAlwaysShow(-1);
                break;
            default:
        }
        repository.saveAndFlush(menu);
    }

    @Override
    public void update(UpMenu menu) {
        if (menu.getId() == null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        if (!repository.existsById(menu.getId())) {
            ErrorStatus.MENU_NOT_EXIST.throwEx();
        }
        repository.saveAndFlush(menu);
    }

    @Override
    public void delete(UpMenu menu) {

    }

    @Override
    public void deleteAll(List<UpMenu> list) {

    }

    @Override
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            ErrorStatus.MENU_NOT_EXIST.throwEx();
        }
        repository.deleteById(id);
    }
}
