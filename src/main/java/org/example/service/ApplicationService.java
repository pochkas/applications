package org.example.service;

import org.example.MessageStatus;
import org.example.entity.Application;

import java.util.List;


public interface ApplicationService {

    public Application addApplication(Application application);

    public List<Application> getAllApplicationsForUser(String username, int page, int size, String[] sort);

    public void correctDraftApplication(Long id, String username, String newMessage);

    public void sendApplication(Long id, String username);
}
