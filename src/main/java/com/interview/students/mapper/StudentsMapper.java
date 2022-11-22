package com.interview.students.mapper;

import com.interview.students.domain.Student;
import com.interview.students.model.StudentDTO;
import com.interview.students.utility.Util;
import org.modelmapper.ModelMapper;

import java.util.List;

public final class StudentsMapper {

    public static List<StudentDTO> map(List<Student> students) {
        return Util.mapList(students,StudentDTO.class);
    }

    public static StudentDTO map(Student student) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(student,StudentDTO.class);
    }

    public static Student map(StudentDTO studentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(studentDTO, Student.class);
    }

}
