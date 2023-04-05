package org.example.service;

import org.example.MessageStatus;
import org.example.entity.Application;

import java.util.List;


public interface ApplicationService {

    public Application addApplication(Application application);

    public List<Application> getAllApplications(int page, int size, String[] sort, MessageStatus messageStatus);
    public List<Application> getAllUserApplications(String username, int page, int size, String[] sort, MessageStatus messageStatus);

    public List<Application> getAllApplicationsByPrefix(String username, int page, int size, String[] sort, MessageStatus messageStatus);

    public Application getApplication(Long id);

  public void acceptApplication(Long id);

    public void rejectApplication(Long id);

    public void correctDraftApplication(Long id, String newMessage);

    public void sendApplication(Long id);



}
