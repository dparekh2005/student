package com.interview.students.service;

import com.interview.students.domain.JwtBlacklist;
import com.interview.students.repo.JwtBlacklistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class JwtBlacklistService {

    @Autowired
    private JwtBlacklistRepository repository;
    public JwtBlacklist addJwttoBlacklist(String token) {
        JwtBlacklist userBlacklist = new JwtBlacklist();
        userBlacklist.setToken(token);
        repository.save(userBlacklist);
        return userBlacklist;
    }

    public JwtBlacklist findJwtBlacklisted(String token) {
        JwtBlacklist jwtBlacklist = null;
        try {
            jwtBlacklist = repository.findJwtBlacklistByToken(token).get();
        } catch (NoSuchElementException ex) {
            log.info("Token is not blacklisted");
        }
        return jwtBlacklist;
    }
}
