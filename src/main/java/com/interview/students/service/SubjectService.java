package com.interview.students.service;

import com.interview.students.domain.Subject;
import com.interview.students.domain.Teacher;
import com.interview.students.exception.NoDataFoundException;
import com.interview.students.payload.response.GetAllSubjectsResponse;
import com.interview.students.repo.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.interview.students.mapper.SubjectsMapper.map;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository repository;
    private final TeacherService teacherService;

    public GetAllSubjectsResponse getSubjects() {
        log.info("Get Subjects");
        return new GetAllSubjectsResponse(repository.count(), map(repository.findAll()));
    }

    public Subject getSubjectById(Integer id) {
        log.info("Get Subject by Id {}", id);
        Subject subject = null;
        try {
            subject = repository.findById(id).get() ;
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        return subject;
    }

    public Subject createSubject(Subject subject) {
        log.info("Create Subject");
        for (Teacher teacher: subject.getTeachers()) {
            validateTeacher(teacher.getTeacherId());
        }
        Subject savedSubject = repository.save(subject);
        return savedSubject;
    }

    public Subject updateSubject(Subject updateSubject, Integer id) {
        log.info("Update Subject");
        Subject subject = null;
        try {
            for (Teacher updateTeacher: updateSubject.getTeachers()) {
                teacherService.updateTeacher(updateTeacher,updateTeacher.getTeacherId());
            }
            subject = repository.findById(id).get() ;
            subject.setTitle(updateSubject.getTitle());
            subject.setTeachers(updateSubject.getTeachers());
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        Subject savedSubject = repository.save(subject);
        return savedSubject;
    }

    public void deleteSubject(Integer id) {
        log.info("Remove Subject");
        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new NoDataFoundException();
        }
    }

    private Boolean validateTeacher(Integer id) {
        Teacher teacher = teacherService.getTeacherById(id);
        if(teacher==null) {
            throw new NoDataFoundException();
        }
        return true;
    }
}
