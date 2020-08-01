package com.cq.vsa.controller.permission;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chengqi
 * @Date: Created in 11:06 2019/4/10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getUserInfo" , method = RequestMethod.GET)
    public UserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }
}
