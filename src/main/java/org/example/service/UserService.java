package org.example.service;


import org.example.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    public boolean saveUser(User user);

    public UserDetails loadUserByUsername(String username);

    public List<User> getAll();

    public void updatePermission(String username);
}
