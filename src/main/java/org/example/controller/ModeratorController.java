package org.example.controller;

import org.example.MessageStatus;
import org.example.entity.Application;
import org.example.exception.ApplicationException;
import org.example.service.ApplicationService;
import org.example.service.ModeratorService;
import org.example.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/moderator")
@PreAuthorize("hasAuthority('MODERATOR')")
public class ModeratorController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ModeratorService moderatorService;

    //Просматривать все отправленные на рассмотрение  заявки
    @GetMapping(value = "/")
    public List<Application> getAllApplications(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "dateTime,asc") String[] sort) {
        return moderatorService.getApplicationsByStatus(MessageStatus.SENT, page, size, sort);
    }

    //Просматривать отправленные заявки только конкретного пользователя
    @GetMapping(value = "/byUser/{username}")
    public List<Application> getAllUserApplications(@PathVariable String username, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "dateTime,asc") String[] sort) {
        return moderatorService.getApplicationsByUsername(username, MessageStatus.SENT, page, size, sort);
    }

    //Просматривать отправленные заявки только конкретного пользователя по части имени
    @GetMapping(value = "/byPrefix/{prefix}")
    public List<Application> getAllApplicationsByPrefix(@PathVariable String prefix, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "dateTime,asc") String[] sort) {
        return moderatorService.getApplicationsByPrefixStartsWith(prefix, MessageStatus.SENT, page, size, sort);
    }

    // Принимать заявки
    @PatchMapping(value = "/accept/{applicationId}")
    public ResponseEntity<String> acceptApplication(@PathVariable Long applicationId) {
        try {
            moderatorService.acceptApplication(applicationId);
            return ResponseEntity.ok("Application was accepted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Application was not accepted");
        }
    }

    // Отклонять заявки
    @PatchMapping(value = "/reject/{applicationId}")
    public ResponseEntity<String> rejectApplication(@PathVariable Long applicationId) {
        try {
            moderatorService.rejectApplication(applicationId);
            return ResponseEntity.ok("Application was rejected");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error. Application was not rejected");
        }
    }
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity handleException(ApplicationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
