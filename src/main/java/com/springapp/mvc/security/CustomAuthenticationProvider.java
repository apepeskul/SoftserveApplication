package com.springapp.mvc.security;

import java.util.Collection;
import java.util.List;

import com.springapp.mvc.repositories.UserRepository;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.11.13
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        List<User> users = userRepository.findByLogin(username);
        if(users.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = users.get(0);
        if(!encoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Wrong password.");
        }/*
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }*/

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}