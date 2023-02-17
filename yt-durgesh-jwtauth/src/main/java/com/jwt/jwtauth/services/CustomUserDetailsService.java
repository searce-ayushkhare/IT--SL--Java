package com.jwt.jwtauth.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    // This method will return a User
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("dummyMan")) {
            return new User("dummyMan", "dummyPasswd", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
