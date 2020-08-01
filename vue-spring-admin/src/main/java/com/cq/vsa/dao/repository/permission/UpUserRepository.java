package com.cq.vsa.dao.repository.permission;

import com.cq.vsa.attribute.CacheNames;
import com.cq.vsa.domain.permission.UpUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: chengqi
 * @Date: Created in 13:09 2019/4/8
 */
@CacheConfig(cacheNames = CacheNames.USERS)
public interface UpUserRepository extends JpaRepository<UpUser, Integer> {

    /**
     * 通过用户名查询用户
     * @param userName
     * @return
     */
    @Cacheable(key = "#p0")
    UpUser findByUserName(String userName);

    /**
     * 添加缓存处理
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(key = "#p0.userName")
    @Override
    <S extends UpUser> S saveAndFlush(S entity);

    /**
     * 添加缓存配置
     * @param entity
     * @param <S>
     * @return
     */
    @CacheEvict(key = "#p0.userName")
    @Override
    <S extends UpUser> S save(S entity);

    /**
     * 缓存配置
     * @param entities
     */
    @CacheEvict(allEntries = true)
    @Override
    void deleteAll(Iterable<? extends UpUser> entities);

    /**
     * 是否存在用户名
     * @param userName
     * @return
     */
    boolean existsByUserName(String userName);

    /**
     * 是否存在所有的用户名
     * @param userNames
     * @return
     */
    boolean existsAllByUserNameIn(List<String> userNames);
}
