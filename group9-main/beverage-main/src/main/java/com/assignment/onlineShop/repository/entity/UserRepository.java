package com.assignment.onlineShop.repository.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends CrudRepository<WebUser, Long> {

    WebUser findUserByUsername(String username) throws UsernameNotFoundException;
}
