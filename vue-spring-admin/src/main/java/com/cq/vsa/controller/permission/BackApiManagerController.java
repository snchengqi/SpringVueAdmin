package com.cq.vsa.controller.permission;

import com.cq.vsa.domain.permission.UpBackendApi;
import com.cq.vsa.service.permission.IBackendApiManagerService;
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
 * @Date: Created in 12:11 2019/4/13
 */
@RestController
@RequestMapping("/backAPiManager")
public class BackApiManagerController {

    private IBackendApiManagerService service;

    @Autowired
    public void setService(IBackendApiManagerService service) {
        this.service = service;
    }

    @PostMapping("queryBackApis")
    public Page<UpBackendApi> queryBackApis(UpBackendApi backendApi, @PageableDefault(sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.query(backendApi, pageable);
    }

    @PostMapping("addBackendApi")
    public String addBackendApi(@Valid UpBackendApi backendApi) {
        service.save(backendApi);
        return "OK";
    }

    @PostMapping("editBackendApi")
    public String editBackendApi(@Valid UpBackendApi backendApi) {
        service.update(backendApi);
        return "OK";
    }

    @PostMapping("deleteBackendApis")
    public String deleteBackendApis(@RequestBody List<UpBackendApi> backendApis) {
        service.deleteAll(backendApis);
        return "OK";
    }
}
