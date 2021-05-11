package com.reading.readingisgood.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reading.readingisgood.exception.Error;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class ErrorResponseUtil {
    public static void flushError(HttpServletResponse httpServletResponse, Error error) throws IOException {
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        mapper.writeValue((OutputStream) servletOutputStream, error);
        servletOutputStream.flush();
    }
}
