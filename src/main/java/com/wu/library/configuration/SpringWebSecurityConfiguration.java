package com.wu.library.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;


    // In memory
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("$2a$10$I8yPr2BeTEzxKW5R5vZt7OoC9Wy/tIHhXFCZp.FDVwsyR5DjyTdoC")
//                .roles("ADMIN","DBA","USER")
//                .and()
//                .withUser("dba").password("{noop}dba")
//                .roles("DBA","USER");
//
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin() // when login
                .successHandler(successHandler)
                .loginPage("/login")
                .failureUrl("/error");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN","DBA");
        http.authorizeRequests().antMatchers("/dba/**").hasAnyRole("ADMIN","DBA");
        http.authorizeRequests().antMatchers("/user/**").hasAnyRole("ADMIN","DBA","USER");


        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint); // after login it run authenticationEntryPoint

        //used to redirect when session timeout
       // http.sessionManagement().invalidSessionUrl("/dba");
    }
}
