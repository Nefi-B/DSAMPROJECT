package com.assignment.onlineShop.service;

import com.assignment.onlineShop.repository.entity.UserRepository;
import com.assignment.onlineShop.repository.entity.WebUser;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebUser webUser = userRepo.findUserByUsername(username);

        if (webUser != null) {
            return webUser;
        }

        throw new UsernameNotFoundException("User '" + username + "' not found!");
    }
}
