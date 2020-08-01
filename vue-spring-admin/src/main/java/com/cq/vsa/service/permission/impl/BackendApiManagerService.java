package com.cq.vsa.service.permission.impl;

import com.cq.vsa.common.ErrorStatus;
import com.cq.vsa.dao.repository.permission.UpBackendApiRepository;
import com.cq.vsa.domain.permission.UpBackendApi;
import com.cq.vsa.service.permission.IBackendApiManagerService;
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
 * @Date: Created in 16:36 2019/4/12
 */
@Service
public class BackendApiManagerService implements IBackendApiManagerService {

    private UpBackendApiRepository repository;

    @Autowired
    public void setRepository(UpBackendApiRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(UpBackendApi upBackendApi) {
        if (upBackendApi.getId() != null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        if (repository.existsByPathAndMethod(upBackendApi.getPath(), upBackendApi.getMethod())) {
            ErrorStatus.UPBACKENDAPI_EXIST.throwEx();
        }
        repository.saveAndFlush(upBackendApi);
    }

    @Override
    public void update(UpBackendApi upBackendApi) {
        if (upBackendApi.getId() == null) {
            ErrorStatus.BAD_REQUEST.throwEx();
        }
        if (!repository.existsById(upBackendApi.getId())) {
            ErrorStatus.UPBACKENDAPI_EXIST.throwEx();
        }
        repository.saveAndFlush(upBackendApi);
    }

    @Override
    public void delete(UpBackendApi upBackendApi) {

    }

    @Override
    public void deleteAll(List<UpBackendApi> list) {
        List<Integer> idList = list.stream().map(UpBackendApi::getId).collect(Collectors.toList());
        if (!repository.existsAllByIdIn(idList)) {
            ErrorStatus.UPBACKENDAPI_NOT_EXIST.throwEx();
        }
        repository.deleteAll(list);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Page<UpBackendApi> query(UpBackendApi upBackendApi, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains).
                withMatcher("path", ExampleMatcher.GenericPropertyMatcher::contains);
        Example<UpBackendApi> example = Example.of(upBackendApi, exampleMatcher);
        return repository.findAll(example, pageable);
    }

    @Override
    public List<UpBackendApi> queryAll() {
        return repository.findAll();
    }
}
