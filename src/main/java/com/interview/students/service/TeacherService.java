package com.interview.students.service;

import com.interview.students.domain.Teacher;
import com.interview.students.exception.NoDataFoundException;
import com.interview.students.payload.response.GetAllTeachersResponse;
import com.interview.students.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.interview.students.mapper.TeachersMapper.map;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepository repository;

    public GetAllTeachersResponse getTeachers() {
        log.info("Get Teachers");
        return new GetAllTeachersResponse(repository.count(), map(repository.findAll()));
    }

    public Teacher getTeacherById(Integer id) {
        log.info("Get Teacher by Id {}", id);
        Teacher teacher = null;
        try {
            teacher = repository.findById(id).get() ;
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        return teacher;
    }

    public Teacher createTeacher(Teacher teacher) {
        log.info("Create Teacher");
        return repository.save(teacher);
    }

    public void createTeachers(List<Teacher> teachers) {
        log.info("Create Teachers");
        repository.saveAll(teachers);
    }

    public Teacher updateTeacher(Teacher updateTeacher, Integer id) {
        log.info("Update Teacher");
        Teacher teacher = null;
        try {
            teacher = repository.findById(id).get() ;
            teacher.setTeacherName(updateTeacher.getTeacherName());

        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        Teacher savedTeacher = repository.save(teacher);
        return savedTeacher;
    }

    public void deleteTeacher(Integer id) {
        log.info("Remove Teacher");
        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new NoDataFoundException();
        }
    }
}
