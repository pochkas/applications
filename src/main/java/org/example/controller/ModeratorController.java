package org.example.controller;

import org.example.MessageStatus;
import org.example.entity.Application;
import org.example.entity.User;
import org.example.service.ApplicationService;
import org.example.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moderator")
@PreAuthorize("hasAuthority('MODERATOR')")
public class ModeratorController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ApplicationService applicationService;

    //Просматривать все отправленные на рассмотрение  заявки
    @GetMapping(value = "/")
    public List<Application> getAllApplications(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "date,asc") String[] sort) {
        applicationService.messageForModerator(MessageStatus.SENT);
        return applicationService.getAllApplications(page, size, sort, MessageStatus.fromString("Sent"));
    }

    //Просматривать отправленные заявки только конкретного пользователя
    @GetMapping(value = "/byUser")
    public List<Application> getAllUserApplications(@PathVariable String username, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "date,asc") String[] sort) {
        applicationService.messageForModerator(MessageStatus.SENT);
        return applicationService.getAllUserApplications(username, page, size, sort, MessageStatus.fromString("Sent"));
    }

    //Просматривать отправленные заявки только конкретного пользователя по части имени
    @GetMapping(value = "/byPrefix")
    public List<Application> getAllApplicationsByPrefix(@PathVariable String prefix, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "date,asc") String[] sort) {
        applicationService.messageForModerator(MessageStatus.SENT);
        return applicationService.getAllApplicationsByPrefix(prefix, page, size, sort, MessageStatus.SENT);
    }

    // Принимать заявки
    @PatchMapping(value = "/{applicationId}/accept")
    public ResponseEntity<String> acceptApplication(@PathVariable Long applicationId) {
        try {
            applicationService.messageForModerator(MessageStatus.SENT);
            applicationService.acceptApplication(applicationId);
            return ResponseEntity.ok("Application was accepted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Application was not accepted");
        }
    }

    // Отклонять заявки
    @PatchMapping(value = "/{applicationId}/reject")
    public ResponseEntity<String> rejectApplication(@PathVariable Long applicationId) {
        try {
            applicationService.messageForModerator(MessageStatus.SENT);
            applicationService.rejectApplication(applicationId);
            return ResponseEntity.ok("Application was rejected");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Application was not rejected");
        }
    }



}
