package com.github.marcoscouto.instapetzup.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marcoscouto.instapetzup.dto.LoginDTO;
import com.github.marcoscouto.instapetzup.security.exceptions.JwtAuthenticationFailureHandler;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        LoginDTO login = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String username = ((User) authResult.getPrincipal()).getUsername();
        String token = jwtUtils.generateToken(username);
        response.addHeader("Authorization", token);
    }

}
