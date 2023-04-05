package org.example.controller;

import org.example.MessageStatus;
import org.example.dto.ApplicationCreationDTO;
import org.example.entity.Application;
import org.example.entity.User;
import org.example.service.ApplicationService;
import org.example.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserServiceImpl userServiceImpl;


    // создавать заявки
    @PostMapping(value = "/")
    public ResponseEntity addApplication(Authentication authentication, @RequestBody ApplicationCreationDTO applicationCreationDTO) {
        User user = (User) authentication.getPrincipal();
        Application applicationResponse = applicationCreationDTO.toApplication(user.getUsername());
        Long id = applicationService.addApplication(applicationResponse).getId();

        return new ResponseEntity(id, HttpStatus.CREATED);

    }

    //просматривать созданные им заявки с возможностью сортировки
    @GetMapping(value = "/")
    public ResponseEntity getAllApplications(Authentication authentication, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "date,asc") String[] sort) {
        authentication.getPrincipal();
        return ResponseEntity.ok(applicationService.getAllApplications(page, size, sort, MessageStatus.SENT));
    }

    // редактировать созданные им заявки в статусе «черновик»
    @PatchMapping(value = "/{id}")
    public ResponseEntity correctMessage(Authentication authentication, @PathVariable Long id, @RequestBody ApplicationCreationDTO applicationCreationDTO) {
        User user = (User) authentication.getPrincipal();
        applicationService.correctDraftApplication(id, applicationCreationDTO.getMessage());
        return new ResponseEntity(HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}/send")
    public ResponseEntity sendApplication(Authentication authentication, @PathVariable Long id, @RequestBody ApplicationCreationDTO applicationCreationDTO) {
        User user = (User) authentication.getPrincipal();
        applicationService.sendApplication(id);
        return new ResponseEntity(HttpStatus.OK);

    }






}
