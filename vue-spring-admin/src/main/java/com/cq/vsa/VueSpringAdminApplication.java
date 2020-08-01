package com.cq.vsa;

import com.cq.vsa.jpa.bean.BaseJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author idea
 */
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "VSAAuditorAware")
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseJpaRepositoryFactoryBean.class)
@SpringBootApplication
public class VueSpringAdminApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(VueSpringAdminApplication.class, args);
    }

}
