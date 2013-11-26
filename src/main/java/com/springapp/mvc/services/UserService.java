package com.springapp.mvc.services;

import com.springapp.mvc.model.User;
import com.springapp.mvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.11.13
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        List<User> users = userRepository.findByLogin(login);
        if(users.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = users.get(0);
        return user;
    }
}
