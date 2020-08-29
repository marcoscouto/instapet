package com.github.marcoscouto.instapetzup.config;

import com.github.marcoscouto.instapetzup.models.enums.Permission;
import com.github.marcoscouto.instapetzup.security.JwtAuthenticationFilter;
import com.github.marcoscouto.instapetzup.security.JwtAuthorizationFilter;
import com.github.marcoscouto.instapetzup.security.JwtUtils;
import com.github.marcoscouto.instapetzup.services.impl.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    private final String[] PUBLIC_POST_MATCHERS = {"/pets"};

    private final String[] USER_MATCHERS = {"/pets/**", "/follow/**", "/posts/**", "/comments"};

    private final String[] ADMIN_MATCHERS = {"/admin/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_POST_MATCHERS).permitAll()
                .antMatchers(USER_MATCHERS).hasAnyRole(Permission.USER.toString(), Permission.ADMIN.toString())
                .antMatchers(ADMIN_MATCHERS).hasRole(Permission.ADMIN.toString())
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtils))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtils, userDetailsService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/h2-console/**",
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
