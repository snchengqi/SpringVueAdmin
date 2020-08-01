package com.cq.vsa.component.permission;

import com.cq.vsa.common.ErrorMsg;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chengqi
 * @Date: Created in 16:26 2019/4/8
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            ErrorMsg.USERNAME_OR_PASSWORD_ERROR.renderError(response);
        } else if (exception instanceof LockedException) {
            ErrorMsg.ACCOUNT_LOCK.renderError(response);
        } else if (exception instanceof DisabledException) {
            ErrorMsg.ACCOUNT_DISABLE.renderError(response);
        } else if (exception instanceof AccountExpiredException) {
            ErrorMsg.ACCOUNT_EXPIRED.renderError(response);
        } else if (exception instanceof CredentialsExpiredException) {
            ErrorMsg.CREDENTIALS_EXPIRED.renderError(response);
        } else {
            ErrorMsg.LOGIN_FAILURE.renderError(response);
        }
    }
}
