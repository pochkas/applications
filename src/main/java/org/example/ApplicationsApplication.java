package org.example;

import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.RoleRepository;
import org.example.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ApplicationsApplication  implements CommandLineRunner{

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
        user1.setUsername("user");
        user1.setRoles(roles);
        user1.setId(1L);
        user1.setPassword("user");
        user1.setRoles(Collections.singleton(new Role(1L, RoleName.USER)));

        userServiceImpl.saveUser(user1);

        Role roleuser2 = new Role(2L, RoleName.ADMIN);
        roleRepository.save(roleuser2);
        User user2 = new User();
        HashSet<Role> roles2 = new HashSet<>();
        roles2.add(roleuser2);
        user2.setUsername("admin");
        user2.setRoles(roles2);
        user2.setId(2L);
        user2.setPassword("admin");
       // user2.setRoles(Collections.singleton(new Role(2L, RoleName.ADMIN)));
        userServiceImpl.saveUser(user2);

        Role roleuser3 = new Role(3L, RoleName.MODERATOR);
        roleRepository.save(roleuser3);
        User user3 = new User();
        HashSet<Role> roles3 = new HashSet<>();
        roles3.add(roleuser3);
        user3.setUsername("moderator");
        user3.setRoles(roles3);
        user3.setId(3L);
        user3.setPassword("moderator");
        //user3.setRoles(Collections.singleton(new Role(3L, RoleName.MODERATOR)));
        userServiceImpl.saveUser(user3);

        Role roleuser4 = new Role(4L, RoleName.MODERATOR);
        roleRepository.save(roleuser4);
        User user4 = new User();
        HashSet<Role> roles4 = new HashSet<>();
        roles4.add(roleuser4);
        roles4.add(roleuser2);

        user4.setUsername("new");
        user4.setRoles(roles4);
        user4.setId(4L);
        user4.setPassword("new");


        userServiceImpl.saveUser(user4);

    }
}
