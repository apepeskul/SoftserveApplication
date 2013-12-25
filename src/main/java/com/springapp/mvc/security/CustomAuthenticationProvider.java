package com.springapp.mvc.security;

import java.util.Collection;

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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = userRepository.findByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        if(!encoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Wrong password.");
        }

//        if(!user.isEnabled()){
//            throw new BadCredentialsException("User not authenticated");
//        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}