package com.interview.students.repo;

import com.interview.students.domain.Marks;
import com.interview.students.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {

    Marks findMarksByStudent(Student student);
}