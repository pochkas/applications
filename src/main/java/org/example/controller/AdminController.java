package org.example.controller;

import org.example.entity.User;
import org.example.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //смотреть список пользователей
    @GetMapping(value = "/")
    public List<User> userList(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAll());
        return userServiceImpl.getAll();
    }

    //искать конкретного пользователя по его имени
    @GetMapping(value = "/{username}")
    public User getByName(@PathVariable String username) {
        return userServiceImpl.getByName(username);
    }

    //искать конкретного пользователя по части имени
    @GetMapping(value = "/byPrefix/{prefix}")
    public List<User> getByStartName(@PathVariable String prefix) {
        return userServiceImpl.getByStartName(prefix);
    }

    //назначать пользователям права оператора
    @PatchMapping(value = "/update/{username}")
    public void updateUserPermission(@PathVariable String username) {
        userServiceImpl.updatePermission(username);
    }
}
