package com.cq.vsa.dao.repository.permission;

import com.cq.vsa.attribute.CacheNames;
import com.cq.vsa.domain.permission.UpMenu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 13:41 2019/4/8
 */
@CacheConfig(cacheNames = CacheNames.USERS)
public interface UpMenuRepository extends JpaRepository<UpMenu, Integer> {

    /**
     * 缓存配置
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpMenu> S save(S entity);

    /**
     * 缓存配置
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpMenu> S saveAndFlush(S entity);

    /**
     * 缓存配置
     * @param entities
     * @param <S>
     * @return
     */
    @CacheEvict(allEntries = true)
    @Override
    <S extends UpMenu> List<S> saveAll(Iterable<S> entities);

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
    void delete(UpMenu entity);

    /**
     * 缓存配置
     * @param entities
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteAll(Iterable<? extends UpMenu> entities);

    /**
     * 缓存配置
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteAll();

    /**
     * 通过层级查询菜单
     * @param level
     * @return
     */
    List<UpMenu> findByLevel(int level);

    /**
     * 数据库是否存在指定名称的菜单
     * @param name
     * @return
     */
    boolean existsByName(String name);
}
