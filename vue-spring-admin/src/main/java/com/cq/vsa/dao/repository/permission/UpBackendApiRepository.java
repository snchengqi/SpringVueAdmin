package com.cq.vsa.dao.repository.permission;

import com.cq.vsa.attribute.CacheNames;
import com.cq.vsa.domain.permission.UpBackendApi;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 13:42 2019/4/8
 */
@CacheConfig(cacheNames = CacheNames.USERS)
public interface UpBackendApiRepository extends JpaRepository<UpBackendApi, Integer> {

    /**
     * 缓存配置
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpBackendApi> S save(S entity);

    /**
     * 缓存配置
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpBackendApi> S saveAndFlush(S entity);

    /**
     * 缓存配置
     * @param entities
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpBackendApi> List<S> saveAll(Iterable<S> entities);

    /**
     * 缓存配置
     * @param integer
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteById(Integer integer);

    /**
     * 缓存配置
     * @param entity
     */
    @CacheEvict(allEntries = true)
    @Override
    void delete(UpBackendApi entity);

    /**
     * 缓存配置
     * @param entities
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteAll(Iterable<? extends UpBackendApi> entities);

    /**
     * 缓存配置
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteAll();

    /**
     * 通过请求路径和方法查询接口
     * @param path
     * @param method
     * @return
     */
    UpBackendApi findByPathAndMethod(String path, int method);

    /**
     * 是否存在指定请求路径和方法的接口
     * @param path
     * @param method
     * @return
     */
    boolean existsByPathAndMethod(String path, int method);

    /**
     * 指定id的是否存在
     * @param ids
     * @return
     */
    boolean existsAllByIdIn(List<Integer> ids);
}
