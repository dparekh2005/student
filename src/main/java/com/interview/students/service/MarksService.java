package com.interview.students.service;

import com.interview.students.domain.Marks;
import com.interview.students.domain.Student;
import com.interview.students.exception.NoDataFoundException;
import com.interview.students.payload.response.GetAllMarksResponse;
import com.interview.students.repo.MarksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.interview.students.mapper.MarksMapper.map;

@Slf4j
@RequiredArgsConstructor
@Service
public class MarksService {
    private final MarksRepository repository;

    public GetAllMarksResponse getMarks() {
        log.info("Get Marks");
        return new GetAllMarksResponse(repository.count(), map(repository.findAll()));
    }

    public Marks getMarksById(Integer id) {
        log.info("Get Marks by Id {}", id);
        Marks marks = null;
        try {
            marks = repository.findById(id).get() ;
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        return marks;
    }

    public Marks createMarks(Marks mark) {
        log.info("Create Marks");
        Marks savedMarks = repository.save(mark);
        return savedMarks;
    }

    public Marks updateMarks(Marks updateMarks, Integer id) {
        log.info("Update Marks");
        Marks marks = null;
        try {
            marks = repository.findById(id).get() ;
            marks.setDate(updateMarks.getDate());
            marks.setMark(updateMarks.getMark());
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        Marks savedMark = repository.save(marks);
        return savedMark;
    }

    public void deleteMarks(Integer id) {
        log.info("Remove Marks");
        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new NoDataFoundException();
        }
    }

    public Marks getMarksByStudent(Student student) {
        log.info("Get Marks by Student Id");
        Marks marks = null;
        try {
            marks = repository.findMarksByStudent(student) ;
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        return marks;
    }

    public List<Marks> getMarksListByStudent(Student student) {
        List<Marks> marksList = new ArrayList<>();
        for (Marks mark: repository.findAll()) {
            if(mark.getStudent().getStudentId().equals(student.getStudentId())){
                marksList.add(mark);
            }
        }
        return marksList;
    }
}
