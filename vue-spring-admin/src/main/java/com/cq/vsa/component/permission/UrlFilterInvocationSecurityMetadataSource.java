package com.cq.vsa.component.permission;

import com.cq.vsa.attribute.UrlAttribute;
import com.cq.vsa.dao.repository.permission.UpBackendApiRepository;
import com.cq.vsa.dao.repository.permission.UpRoleRepository;
import com.cq.vsa.domain.permission.UpBackendApi;
import com.cq.vsa.domain.permission.UpRole;
import com.cq.vsa.enumobj.permission.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 14:32 2019/4/8
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private AntPathMatcher matcher = new AntPathMatcher();

    private UpBackendApiRepository repository;

    private UpRoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(UpRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setRepository(UpBackendApiRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) o;
        //获取请求url
        String requestMethod = invocation.getRequest().getMethod();
        String requestUrl = invocation.getRequestUrl();
        //如果是登录请求，返回null
        UrlAttribute.Request login = UrlAttribute.Permission.LOGIN;
        if (login.url.equals(requestUrl) && login.method.equalsIgnoreCase(requestMethod)) {
            return null;
        }
        List<UpRole> needRoles = getNeedRoles(requestUrl, requestMethod);
        if (needRoles != null) {
            return SecurityConfig.createList(needRoles.stream().map(UpRole::getRoleName).toArray(String[]::new));
        }
        return null;
    }

    private List<UpRole> getNeedRoles(String url, String method) {
        //删除参数
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        UpBackendApi api = repository.findByPathAndMethod(url, HttpMethod.getInstance(method).getCode());
        if (api == null) {
            api = loadFromSimilarApi(url, method);
        }
        if (api != null) {
            List<UpRole> needRoleList = roleRepository.findByMenus_BackendApis_Id(api.getId());
            /*List<UpRole> needRoleList = new ArrayList<>();
            for (UpMenu menu: api.getMenus()) {
                for (UpRole role: menu.getRoles()) {
                    if (needRoleList.stream().noneMatch(item -> role.getId().equals(item.getId()))) {
                        needRoleList.add(role);
                    }
                }
            }*/
            return needRoleList;
        }
        return null;
    }

    /**
     * 处理rest风格的请求
     * @param url
     * @param method
     * @return
     */
    private UpBackendApi loadFromSimilarApi(String url, String method) {
        UpBackendApi api = null;
        while (api == null) {
            if (url.contains("/")) {
                url = url.substring(0, url.lastIndexOf("/"));
                api = repository.findByPathAndMethod(url, HttpMethod.getInstance(method).getCode());
            } else {
                break;
            }
        }
        return api;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
