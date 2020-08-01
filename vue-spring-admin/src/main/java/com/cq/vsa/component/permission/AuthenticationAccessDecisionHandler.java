package com.cq.vsa.component.permission;

import com.cq.vsa.common.ErrorMsg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chengqi
 * @Date: Created in 16:01 2019/4/8
 */
@Component
public class AuthenticationAccessDecisionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorMsg.FORBIDDEN.renderError(response);
    }
}
