package com.cq.vsa.controller.permission;

import com.cq.vsa.domain.permission.UpRole;
import com.cq.vsa.service.permission.IRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 17:17 2019/4/11
 */
@RestController
@RequestMapping("/roleManager")
public class RoleManagerController {

    private IRoleManagerService service;

    @Autowired
    public void setService(IRoleManagerService service) {
        this.service = service;
    }

    @PostMapping("queryRoles")
    public Page<UpRole> queryRoles(UpRole role, @PageableDefault(sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.query(role, pageable);
    }

    @PostMapping("addRole")
    public String addRole(@Valid UpRole role) {
        service.save(role);
        return "OK";
    }

    @PostMapping("editRole")
    public String editRole(@Valid UpRole role) {
        service.update(role);
        return "OK";
    }

    @PostMapping("deleteRoles")
    public String deleteRoles(@RequestBody List<UpRole> roles) {
        service.deleteAll(roles);
        return "OK";
    }
}
