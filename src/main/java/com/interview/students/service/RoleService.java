package com.interview.students.service;

import com.interview.students.domain.Role;
import com.interview.students.model.ERole;
import com.interview.students.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Optional<Role> findByName(ERole name) {
        return repository.findByName(name);
    }
}
