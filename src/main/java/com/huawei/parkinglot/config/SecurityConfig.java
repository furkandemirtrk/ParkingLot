package com.huawei.parkinglot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  private static final String[] AUTH_WHITELIST = {
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**",
      "/console/**",
      "/h2-console/**",
      "/api/parkingArea/**",
      "/api/park/**"
  };


  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable().
        authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.headers().frameOptions().sameOrigin();
  }

}
