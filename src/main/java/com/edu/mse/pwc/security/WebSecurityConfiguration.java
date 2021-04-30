package com.edu.mse.pwc.security;

import com.edu.mse.pwc.utils.P;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  http.csrf().disable();


        P.syso("HTTP");
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/oauth/token", "/oauth/authorize**")
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        P.syso("WEB");
        //web.ignoring().antMatchers("/h2-console/**");
    }
}
