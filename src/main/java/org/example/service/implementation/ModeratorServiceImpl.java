package org.example.service.implementation;

import org.example.MessageStatus;
import org.example.entity.Application;
import org.example.exception.ApplicationException;
import org.example.repository.ApplicationRepository;
import org.example.repository.UserRepository;
import org.example.service.ModeratorService;
import org.example.service.PageCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ModeratorServiceImpl implements ModeratorService, PageCreation {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private UserRepository userRepository;
    private List<Application> messageForModerator(List<Application> applicationList) {
        return applicationList.stream().peek(app-> {
            char[] array = app.getMessage().toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : array) {
                builder.append(c).append("-");
            }
            app.setMessage(builder.toString());
        }).collect(Collectors.toList());
    }

    @Override
    public List<Application> getApplicationsByStatus(MessageStatus messageStatus, int page, int size, String[] sort) {
        PageRequest pr = pageRequest(page, size, sort);
        return messageForModerator(applicationRepository.findByStatus(MessageStatus.SENT, pr));
    }
    @Override
    public List<Application> getApplicationsByUsername(String username, MessageStatus messageStatus, int page, int size, String[] sort) {
        PageRequest pr = pageRequest(page, size, sort);
        return messageForModerator(applicationRepository.findByUsernameAndStatus(username, MessageStatus.SENT, pr));
    }
    @Override
    public List<Application> getApplicationsByPrefixStartsWith(String prefix, MessageStatus messageStatus, int page, int size, String[] sort) {
        PageRequest pr = pageRequest(page, size, sort);
        return messageForModerator(applicationRepository.findByUsernameStartsWithAndStatus(prefix, MessageStatus.SENT, pr));
    }
    @Override
    public void acceptApplication(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (!application.isPresent()) {
            throw new ApplicationException("Application was not found!");
        } else if (application.get().getStatus().equals(MessageStatus.SENT)) {
            application.get().setStatus(MessageStatus.ACCEPTED);
            applicationRepository.save(application.get());
        } else {
            throw new ApplicationException("Error. Application has wrong status.");
        }
    }

    @Override
    public void rejectApplication(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (!application.isPresent()) {
            throw new ApplicationException("Application was not found!");
        } else if (application.get().getStatus().equals(MessageStatus.SENT)) {
            application.get().setStatus(MessageStatus.REJECTED);
            applicationRepository.save(application.get());
        } else {
            throw new ApplicationException("Error. Application has wrong status.");
        }
    }
}
