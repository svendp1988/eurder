package com.switchfully.api.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOG = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOG.warn("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());
        }

        response.sendError(HttpStatus.FORBIDDEN.value(), "You don't have the rights to do that.");
    }
}