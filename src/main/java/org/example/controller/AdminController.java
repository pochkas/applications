package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
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
    private UserService userService;

    //смотреть список пользователей
    @GetMapping(value = "/")
    public List<User> userList(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return userService.getAll();
    }

    //искать конкретного пользователя по его имени
    @GetMapping(value = "/{username}")
    public User getByName(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    //искать конкретного пользователя по части имени
    @GetMapping(value = "/byPrefix/{prefix}")
    public List<User> getByStartName(@PathVariable String prefix) {
        return userService.getByStartName(prefix);
    }

    //назначать пользователям права оператора
    @PatchMapping(value = "/update/{username}")
    public void updateUserPermission(@PathVariable String username) {
        userService.updatePermission(username);
    }
}
