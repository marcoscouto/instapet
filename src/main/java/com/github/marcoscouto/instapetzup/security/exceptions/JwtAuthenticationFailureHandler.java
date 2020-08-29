package com.github.marcoscouto.instapetzup.security.exceptions;

import com.github.marcoscouto.instapetzup.exceptions.StandardError;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setStatus(403);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(getJsonResponse());
    }

    private String getJsonResponse() {
        StandardError standardError = new StandardError("Erro de autenticação - Acesso Negado", Arrays.asList("Email e/ou senha inválido(s)"), LocalDateTime.now(ZoneId.of("UTC")));
        return new JSONObject(standardError).toString(4);
    }
}
