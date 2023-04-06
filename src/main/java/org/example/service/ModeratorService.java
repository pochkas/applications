package org.example.service;

import org.example.MessageStatus;
import org.example.entity.Application;

import java.util.List;

public interface ModeratorService {

    public List<Application> getApplicationsByStatus(MessageStatus messageStatus,  int page, int size, String[] sort);

    public List<Application> getApplicationsByUsername(String username, MessageStatus messageStatus,  int page, int size, String[] sort);

    public List<Application> getApplicationsByPrefixStartsWith(String prefix, MessageStatus messageStatus,  int page, int size, String[] sort);

    public void acceptApplication(Long id);

    public void rejectApplication(Long id);
}
