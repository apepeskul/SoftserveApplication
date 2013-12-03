package com.springapp.mvc.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException,
                                                       ServletException {
        String requestURL = request.getServletPath();
        response.sendRedirect("/403");
        String message = getAccessDeniedMessage(requestURL);
        request.getSession().setAttribute("message", message);

    }

    private String getAccessDeniedMessage(String requestURL){
        String[] adminPages = {"/add", "/edit", "/users", "/delete"};
        String[] merchandiserPages = {"/rest/item/", "/rest/items"};
        String result = "This page is only for ";

        if(containsStartsWith(adminPages, requestURL)){
            result += "admin";
        } else if(containsStartsWith(merchandiserPages, requestURL)){
            result += "merchandiser";
        } else {
            result = "Просто access denied";
        }
        return result;
    }

    private boolean containsStartsWith(String[] source, String pat){
        for(String s : source){
            if(pat.startsWith(s)){
                return true;
            }
        }
        return false;
    }
}
