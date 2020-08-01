package com.cq.vsa.component.global;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: chengqi
 * @Date: Created in 10:57 2019/4/15
 */
@Component("VSAAuditorAware")
public class VSAAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return Optional.empty();
        }
        if (context.getAuthentication() == null || context.getAuthentication().getPrincipal() == null) {
            return Optional.empty();
        }
        Object principal = context.getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return Optional.of(((UserDetails) principal).getUsername());
        }
        return Optional.empty();
    }
}
