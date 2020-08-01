package com.cq.vsa.controller.permission;

import com.cq.vsa.domain.permission.UpRole;
import com.cq.vsa.domain.permission.UpUser;
import com.cq.vsa.service.permission.IUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 15:33 2019/4/10
 */
@RestController
@RequestMapping("/userManager")
public class UserManagerController {

    private IUserManagerService service;

    @Autowired
    public void setService(IUserManagerService service) {
        this.service = service;
    }

    @RequestMapping(value = "queryUsers", method = RequestMethod.POST)
    public Page<UpUser> queryUsers(UpUser user, @PageableDefault(sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.query(user, pageable);
    }

    @GetMapping("queryAllRoles")
    public List<UpRole> queryAllRoles() {
        return service.queryAllRoles();
    }

    @PostMapping("addUser")
    public String addUser(@Valid UpUser user) {
        service.save(user);
        return "OK";
    }

    @PostMapping("editUser")
    public String editUser(@Valid UpUser user) {
        service.update(user);
        return "OK";
    }

    @PostMapping("deleteUsers")
    public String deleteUsers(@RequestBody List<UpUser> users) {
        service.deleteAll(users);
        return "OK";
    }
}
