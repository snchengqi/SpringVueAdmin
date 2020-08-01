package com.cq.vsa.controller.permission;

import com.cq.vsa.domain.permission.UpBackendApi;
import com.cq.vsa.domain.permission.UpMenu;
import com.cq.vsa.service.permission.IBackendApiManagerService;
import com.cq.vsa.service.permission.IMenuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 9:08 2019/4/12
 */
@RestController
@RequestMapping("/menuManager")
public class MenuManagerController {

    private IMenuManagerService service;

    private IBackendApiManagerService backendApiService;

    @Autowired
    public void setService(IMenuManagerService service) {
        this.service = service;
    }

    @Autowired
    public void setBackendApiService(IBackendApiManagerService backendApiService) {
        this.backendApiService = backendApiService;
    }

    @GetMapping("getMenuTree")
    public List<UpMenu> getMenuTree() {
        return service.getMenuTree();
    }

    @PostMapping("addMenu")
    public String addMenu(@Valid UpMenu menu) {
        service.save(menu);
        return "OK";
    }

    @PostMapping("editMenu")
    public String editMenu(@Valid UpMenu menu) {
        service.update(menu);
        return "OK";
    }

    @PostMapping("deleteMenu")
    public String deleteMenu(@NotNull Integer id) {
        service.deleteById(id);
        return "OK";
    }

    @GetMapping("queryAllBackendApi")
    public List<UpBackendApi> getAllBackendApi() {
        return backendApiService.queryAll();
    }
}
