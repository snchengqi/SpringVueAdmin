package com.cq.vsa.component.permission;

import com.cq.vsa.common.ErrorMsg;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份校验失败处理器，如 token 错误
 * @Author: chengqi
 * @Date: Created in 15:12 2019/4/9
 */
@Component
public class EntryPointAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorMsg.AUTH_FAILURE.renderError(response);
    }
}
