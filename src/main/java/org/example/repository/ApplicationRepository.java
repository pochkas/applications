package org.example.repository;

import org.example.MessageStatus;
import org.example.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {


    List<Application> findByStatusAndUsername(String username, MessageStatus messageStatus, Pageable pageable);

    List<Application> findByStatusAndUsernameStartsWith(String prefix, MessageStatus messageStatus, Pageable pageable);

    List<Application> findByStatus(MessageStatus status, Pageable pageable);

    @Override
    List<Application> findAllById(Iterable<Long> iterable);

    @Override
    Application getOne(Long id);

    @Override
    Page<Application> findAll(Pageable pageable);

    Application findApplicationByStatus(MessageStatus messageStatus);
}
