package org.example.service.implementation;

import org.example.MessageStatus;
import org.example.entity.Application;
import org.example.exception.ApplicationException;
import org.example.repository.ApplicationRepository;
import org.example.repository.UserRepository;
import org.example.service.ApplicationService;
import org.example.service.PageCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ApplicationServiceImpl implements ApplicationService, PageCreation {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Application addApplication(Application application) {
        application.setStatus(MessageStatus.DRAFT);
        application.setDateTime(LocalDateTime.now());
        return applicationRepository.save(application);
    }


    @Override
    public List<Application> getAllApplicationsForUser(String username, int page, int size, String[] sort) {
        PageRequest pr = pageRequest(page, size, sort);
        return applicationRepository.findByUsername(username, pr);
    }

    @Override
    public void correctDraftApplication(Long id, String username, String newMessage) {
        Optional<Application> application = applicationRepository.findById(id);
        if (!application.isPresent() || (!(application.get().getUsername().equals(username)))){
            throw new ApplicationException("Application was not found!");
        }
        Application application1 = application.get();
        if (application1.getStatus().equals(MessageStatus.DRAFT)) {
            application1.setMessage(newMessage);
            applicationRepository.save(application1);
        } else {
            throw new ApplicationException("Application is not draft.");
        }
    }

    @Override
    public void sendApplication(Long id, String username) {
        Application application = applicationRepository.getOne(id);
        if(!application.getUsername().equals(username)){
            throw new ApplicationException("Error. Application was not found.");
        }
        if(application.getStatus().equals(MessageStatus.DRAFT)) {
            application.setStatus(MessageStatus.SENT);
            applicationRepository.save(application);
        } else {
            throw new ApplicationException("Application is not draft.");
        }
    }
}
