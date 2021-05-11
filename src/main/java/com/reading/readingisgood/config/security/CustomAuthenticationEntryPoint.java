package com.reading.readingisgood.config.security;

import com.reading.readingisgood.constants.ErrorCodes;
import com.reading.readingisgood.exception.Error;
import com.reading.readingisgood.util.ErrorResponseUtil;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        Error error = Error.builder().code(ErrorCodes.AUTH_ERROR).message("Authentication error!").timestamp((new Date()).getTime()).build();
        ErrorResponseUtil.flushError(httpServletResponse, error);
    }
}