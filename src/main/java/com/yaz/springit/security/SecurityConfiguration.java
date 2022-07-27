package com.yaz.springit.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImp userDetailsServiceImp;

    public SecurityConfiguration(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .requestMatchers(EndpointRequest.to("info")).permitAll()
                    .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                    .antMatchers("/actuator/").hasRole("ADMIN")
                    .antMatchers("/").permitAll()
                    .antMatchers("/link/submit").hasRole("USER")
                    .antMatchers("/h2-console/**").permitAll()
                .and().formLogin()
                    .loginPage("/login") // 1
                    .permitAll()
                    .usernameParameter("email")
                .and().logout()
                .and().rememberMe().tokenValiditySeconds(86400) // 2
                .and()
                    .csrf().disable()
                    .headers().frameOptions().disable();;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp);
    }
}
