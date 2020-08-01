package com.cq.vsa.service.permission.impl;

import com.cq.vsa.dao.repository.permission.UpMenuRepository;
import com.cq.vsa.dao.repository.permission.UpUserRepository;
import com.cq.vsa.domain.permission.UpMenu;
import com.cq.vsa.domain.permission.UpRole;
import com.cq.vsa.domain.permission.UpUser;
import com.cq.vsa.enumobj.permission.MenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 13:53 2019/4/8
 */
@Service
@Qualifier("VSAUserDetailsService")
public class VSAUserDetailsService implements UserDetailsService {

    private UpUserRepository repository;

    private UpMenuRepository menuRepository;

    @Autowired
    public void setRepository(UpUserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMenuRepository(UpMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UpUser user = repository.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("userName is not exist");
        }

        for (UpRole role: user.getRoles()) {
            if (role.getMenus() != null && role.getMenus().size() > 0) {
                if (role.getMenus().get(0).getLevel() == MenuType.SECOND_LEVEL.getLevel()) {
                    rebuildRole(role);
                }
            }
        }
        return user;
    }

    private void rebuildRole(UpRole role) {
        List<UpMenu> menuList = new ArrayList<>();
        List<UpMenu> firstMenus = menuRepository.findByLevel(MenuType.FIRST_LEVEL.getLevel());
        for (UpMenu menu: role.getMenus()) {
            UpMenu pMenu = menuList.stream().filter(item -> item.getId().equals(menu.getParentId())).findFirst().orElse(null);
            if (pMenu == null) {
                pMenu = firstMenus.stream().filter(item -> item.getId().equals(menu.getParentId())).findFirst().orElse(null);
                if (pMenu == null) {
                    continue;
                }
                menuList.add(pMenu);
            }
            pMenu.getChildren().add(menu);
        }
        role.setMenus(menuList);
    }
}
