package com.cq.vsa.service.permission.impl;

import com.cq.vsa.common.ErrorStatus;
import com.cq.vsa.dao.repository.permission.UpRoleRepository;
import com.cq.vsa.dao.repository.permission.UpUserRepository;
import com.cq.vsa.domain.permission.UpRole;
import com.cq.vsa.domain.permission.UpUser;
import com.cq.vsa.service.permission.IUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: chengqi
 * @Date: Created in 16:53 2019/4/10
 */
@Service
public class UserManagerService implements IUserManagerService {

    private UpUserRepository repository;
    private UpRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRepository(UpUserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setRoleRepository(UpRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UpUser> query(UpUser user, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("userName", ExampleMatcher.GenericPropertyMatcher::contains).
                withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()).
                withIgnorePaths("password");
        Example<UpUser> example = Example.of(user, exampleMatcher);
        return repository.findAll(example, pageable);
    }

    @Override
    public List<UpUser> queryAll() {
        return null;
    }

    @Override
    public List<UpRole> queryAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void save(UpUser user) {
        if (user.getId() != null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        if (repository.existsByUserName(user.getUserName())) {
            ErrorStatus.USER_EXIST.throwEx();
        }
        //生成经过散列之后的密码
        user.setPassword(passwordEncoder.encode(user.getUserName()));
        repository.saveAndFlush(user);
    }

    @Override
    public void update(UpUser user) {
        if (user.getId() == null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        Optional<UpUser> optional = repository.findById(user.getId());
        if (!optional.isPresent()) {
            ErrorStatus.USER_EXIST.throwEx();
        }
        UpUser dbUser = optional.get();
        user.setUserName(dbUser.getUserName());
        user.setPassword(dbUser.getPassword());
        repository.saveAndFlush(user);
    }

    @Override
    public void delete(UpUser user) {

    }

    @Override
    public void deleteAll(List<UpUser> upUsers) {
        List<String> userNames = upUsers.stream().map(UpUser::getUserName).collect(Collectors.toList());
        if (!repository.existsAllByUserNameIn(userNames)) {
            ErrorStatus.USER_NOT_EXIST.throwEx();
        }
        repository.deleteAll(upUsers);
    }

    @Override
    public void deleteById(Integer id) {

    }
}
