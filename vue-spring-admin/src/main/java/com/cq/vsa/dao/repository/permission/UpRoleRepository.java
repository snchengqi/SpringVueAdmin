package com.cq.vsa.dao.repository.permission;

import com.cq.vsa.attribute.CacheNames;
import com.cq.vsa.domain.permission.UpRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 13:41 2019/4/8
 */
@CacheConfig(cacheNames = CacheNames.USERS)
public interface UpRoleRepository extends JpaRepository<UpRole, Integer> {

    /**
     * 缓存配置，当角色修改时需要清除用户的缓存配置
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpRole> S save(S entity);

    /**
     * 缓存配置，当角色修改时需要清除用户的缓存配置
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpRole> S saveAndFlush(S entity);

    /**
     * 缓存配置
     * @param entities
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpRole> List<S> saveAll(Iterable<S> entities);

    /**
     * 缓存配置
     * @param integer
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteById(Integer integer);

    /**
     * 缓存配置
     * @param entities
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteAll(Iterable<? extends UpRole> entities);

    /**
     * 缓存配置
     * @param entity
     */
    @CacheEvict(allEntries = true)
    @Override
    void delete(UpRole entity);

    /**
     * 缓存配置
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteAll();

    /**
     * 数据库是否存在指定角色
     * @param roleName
     * @return
     */
    boolean existsByRoleName(String roleName);

    /**
     * 数据库是否存在指定角色
     * @param ids
     * @return
     */
    boolean existsAllByIdIn(List<Integer> ids);

    /**
     * 通过接口id查询具备这个接口的角色
     * @param id
     * @return
     */
    List<UpRole> findByMenus_BackendApis_Id(int id);
}
