package org.example.service.implementation;

import org.example.MessageStatus;
import org.example.entity.Application;
import org.example.exception.ApplicationException;
import org.example.repository.ApplicationRepository;
import org.example.repository.UserRepository;
import org.example.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class ApplicationServiceImpl implements ApplicationService {

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


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @Transactional
    @Override
    public List<Application> getAllApplications(int page, int size, String[] sort, MessageStatus messageStatus) {

        PageRequest pr = PageRequest.of(page, size);
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return applicationRepository.findByStatus(messageStatus, pr);
    }

    @Transactional
    @Override
    public List<Application> getAllUserApplications(String username, int page, int size, String[] sort, MessageStatus messageStatus) {

        PageRequest pr = PageRequest.of(page, size);
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return applicationRepository.findByStatusAndUsername(username, messageStatus, pr);

    }

    public List<Application> getAllApplicationsByPrefix(String prefix, int page, int size, String[] sort, MessageStatus messageStatus) {

        PageRequest pr = PageRequest.of(page, size);
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        return applicationRepository.findByStatusAndUsernameStartsWith(prefix, MessageStatus.SENT, pr);

    }

    @Override
    public Application getApplication(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (!application.isPresent()) {
            throw new ApplicationException("Application was not found!");
        }
        return application.get();
    }
    @Override
    public void acceptApplication(Long id) {
        Optional<Application> application = applicationRepository.findById(id);

        if (!application.isPresent()) {
            throw new ApplicationException("Application was not found!");
        } else if (application.get().getStatus().equals(MessageStatus.SENT)) {
            application.get().setStatus(MessageStatus.ACCEPTED);
            applicationRepository.save(application.get());

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
        }
    }
    @Override
    public void correctDraftApplication(Long id, String newMessage) {
        Optional<Application> application = applicationRepository.findById(id);
        if (!application.isPresent()) {
            throw new ApplicationException("Application was not found!");
        }
        Application application1 = application.get();
        if (application1.getStatus().equals(MessageStatus.DRAFT)) {
            application1.setMessage(newMessage);
            applicationRepository.save(application1);
        }

    }

    @Override
    public void sendApplication(Long id) {

        Application application = applicationRepository.getOne(id);
        application.setStatus(MessageStatus.SENT);
        applicationRepository.save(application);

    }

    @Override
    public String messageForModerator(MessageStatus messageStatus) {
        Application application = applicationRepository.findApplicationByStatus(messageStatus);
        char[] array = application.getMessage().toCharArray();
        StringBuilder builder = new StringBuilder();
        for (char c : array) {
            builder.append(c).append("-");
        }
        application.setMessage(builder.toString());
        applicationRepository.save(application);
        return builder.toString();
    }



}
