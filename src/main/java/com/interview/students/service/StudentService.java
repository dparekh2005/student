package com.interview.students.service;

import com.interview.students.domain.Marks;
import com.interview.students.domain.Student;
import com.interview.students.domain.Teacher;
import com.interview.students.exception.NoDataFoundException;
import com.interview.students.payload.response.GetAllStudentsResponse;
import com.interview.students.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.interview.students.mapper.StudentsMapper.map;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository repository;
    private final MarksService marksService;

    public GetAllStudentsResponse getStudents() {
        log.info("Get Students");
        return new GetAllStudentsResponse(repository.count(), map(repository.findAll()));
    }

    public Student getStudentById(Integer id) {
        log.info("Get Student by Id {}", id);
        Student student = null;
        try {
            student = repository.findById(id).get() ;
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        return student;
    }

    public Student createStudent(Student student) {
        log.info("Create Student");
        Student savedStudent = repository.save(student);
        for (Marks marks: student.getMarksList()) {
            marks.setStudent(student);
            marksService.createMarks(marks);
        }
        return savedStudent;
    }

    public Student updateStudent(Student updateStudent, Integer id) {
        log.info("Update Student");
        Student student = null;
        try {
            student = repository.findById(id).get() ;
            student.setFirstName(updateStudent.getFirstName());
            student.setLastName(updateStudent.getLastName());
            student.setGroup(updateStudent.getGroup());
            student.setMarksList(updateStudent.getMarksList());
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        Student savedStudent = repository.save(student);
        return savedStudent;
    }

    public void deleteStudent(Integer id) {
        log.info("Remove Student");
        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new NoDataFoundException();
        }
    }

    public List<Student> getStudentByTeacherId(Integer id) {
        log.info("Get Student by Teacher Id {}", id);
        List<Student> students = new ArrayList<>();
        try {
            List<Student> allStudents = repository.findAll();
            for (Student student : allStudents) {
                List<Teacher> teachers = student.getGroup().getTeachers();
                for (Teacher teacher: teachers) {
                    if(teacher.getTeacherId().equals(id)) {
                        students.add(student);
                    }
                }
            }
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        return students;
    }
}