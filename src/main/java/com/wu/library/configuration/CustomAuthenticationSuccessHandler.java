package com.wu.library.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException , ServletException {

        //String redirectURI = (String)httpServletRequest.getSession().getAttribute("REDIRECT_URI"); get URI from EntryPoint which already set
       // Set session time
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setMaxInactiveInterval(1000);

        System.out.println("authentication"+authentication.getAuthorities());
        System.out.println(authentication.getName());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getPrincipal());
        for (GrantedAuthority grantedAuthority: authentication.getAuthorities()){
            System.out.println("============="+grantedAuthority.getAuthority());
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                httpServletResponse.sendRedirect("/admin/book/all");
                break;
            }
            else if(grantedAuthority.getAuthority().equals("ROLE_DBA")){
                httpServletResponse.sendRedirect(("/admin/return"));
                break;
            }
            else if(grantedAuthority.getAuthority().equals("ROLE_USER")){
                httpServletResponse.sendRedirect(("/user"));
                break;
            }
            else {
                System.out.println("============heelo============");
                httpServletResponse.sendRedirect(("/error"));
            }
        }


    }
}
