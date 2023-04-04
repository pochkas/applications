package org.example.service;

import org.example.RoleName;
import org.example.entity.Role;
import org.example.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService extends UserDetailsService {

    public boolean saveUser(User user);

    public UserDetails loadUserByUsername(String username);

    public List<User> getAll();

    public User findUserById(Long id);

    public void updatePermission(String username);

}
