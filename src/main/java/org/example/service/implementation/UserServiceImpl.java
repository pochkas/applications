package org.example.service.implementation;

import org.example.RoleName;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.exception.ApplicationException;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByName(String username) {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new ApplicationException("User was not found!");
        }
        return userRepository.findByUsername(username);
    }

    public List<User> getByStartName(String prefix) {
        return userRepository.findByUsernameStartsWith(prefix);
    }

    public void updatePermission(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<Role> userRole = user.getRoles();
        Set<Role> roles = new HashSet<>();
        Role role = new Role(RoleName.MODERATOR);
        roles.add(role);
        roles.addAll(userRole);
        user.setRoles(roles);
    }
}
