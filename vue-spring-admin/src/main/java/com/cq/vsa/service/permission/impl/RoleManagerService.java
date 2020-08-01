package com.cq.vsa.service.permission.impl;

import com.cq.vsa.common.ErrorStatus;
import com.cq.vsa.dao.repository.permission.UpRoleRepository;
import com.cq.vsa.domain.permission.UpRole;
import com.cq.vsa.service.permission.IRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chengqi
 * @Date: Created in 17:18 2019/4/11
 */
@Service
public class RoleManagerService implements IRoleManagerService {

    private UpRoleRepository repository;

    @Autowired
    public void setRepository(UpRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UpRole> query(UpRole role, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("roleName", ExampleMatcher.GenericPropertyMatcher::contains).
                withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains);
        Example<UpRole> example = Example.of(role, exampleMatcher);
        return repository.findAll(example, pageable);
    }

    @Override
    public List<UpRole> queryAll() {
        return null;
    }

    @Override
    public void save(UpRole role) {
        if (role.getId() != null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        if (repository.existsByRoleName(role.getRoleName())) {
            ErrorStatus.ROLE_EXIST.throwEx();
        }
        repository.saveAndFlush(role);
    }

    @Override
    public void update(UpRole role) {
        if (role.getId() == null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        if (!repository.existsById(role.getId())) {
            ErrorStatus.ROLE_NOT_EXIST.throwEx();
        }
        repository.saveAndFlush(role);
    }

    @Override
    public void delete(UpRole role) {

    }

    @Override
    public void deleteAll(List<UpRole> roles) {
        List<Integer> roleIds = roles.stream().map(UpRole::getId).collect(Collectors.toList());
        if (!repository.existsAllByIdIn(roleIds)) {
            ErrorStatus.ROLE_NOT_EXIST.throwEx();
        }
        repository.deleteAll(roles);
    }

    @Override
    public void deleteById(Integer id) {

    }
}
