package com.eve.config;

import com.eve.util.UserDetailsServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("com.eve.util")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("ADMIN");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers( "/public/**").permitAll()
                .antMatchers( "/private/**").authenticated()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/home/**","/").permitAll()//.hasAnyRole("ADMIN","USER")
                .antMatchers("/registration/**").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/home/**","/").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/login").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/errorPage")
                .and()
                .csrf().disable();

    }


    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsServiceUtil userDetailsService;

    @Autowired
    public void configurateGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}