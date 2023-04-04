package org.example;

import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.RoleRepository;
import org.example.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ApplicationsApplication implements CommandLineRunner {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Role roleuser = new Role(1L, RoleName.USER);
        roleRepository.save(roleuser);
        User user1 = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(roleuser);
        user1.setUsername("1");
        user1.setRoles(roles);
        user1.setId(1L);
        user1.setPassword("1");
        userServiceImpl.saveUser(user1);

        Role roleuser2 = new Role(2L, RoleName.ADMIN);
        roleRepository.save(roleuser2);
        User user2 = new User();
        Set<Role> roles2 = new HashSet<>();
        roles.add(roleuser2);
        user1.setUsername("2");
        user1.setRoles(roles2);
        user1.setId(2L);
        user1.setPassword("2");
        userServiceImpl.saveUser(user2);

    }
}
