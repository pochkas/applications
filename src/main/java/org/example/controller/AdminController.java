package org.example.controller;

import org.example.RoleName;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    //смотреть список пользователей
    @GetMapping("/admin")
    public List<User> userList(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAll());
        return userServiceImpl.getAll();
    }

    //искать конкретного пользователя по его имени
    @GetMapping("/admin/{username}")
    public User getByName(@PathVariable("username") String username, Model model) {
        return userServiceImpl.getByName(username);
    }

    //искать конкретного пользователя по части имени
    @GetMapping("/admin/{prefix}")
    public List<User> getByStartName(@PathVariable("prefix") String prefix, Model model) {
        return userServiceImpl.getByStartName(prefix);
    }

    //назначать пользователям права оператора
    @PatchMapping("/admin/{username}/update")
    public void updateUserPermission(@PathVariable String username) {
        userServiceImpl.updatePermission(username);

    }
}
