package com.employees.employeesservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

@EnableWebSecurity(debug = true)
public class  SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    public void init(){
        System.out.println("Metod INIT");
        Assert.notNull(userDetailsService, "UserDetailsService is NULL");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("Metod passwordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        System.out.println("Metod configure AuthentificationManager");
                authenticationManagerBuilder.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Metod configureHttpSecure");
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/registration/**").hasRole("admin")
                .antMatchers("/employees/**").hasAnyRole("admin", "user")
                .anyRequest().authenticated()
                .and().httpBasic();

    }
}
