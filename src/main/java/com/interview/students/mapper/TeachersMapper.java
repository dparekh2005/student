package com.interview.students.mapper;

import com.interview.students.domain.Teacher;
import com.interview.students.model.TeacherDTO;
import com.interview.students.utility.Util;
import org.modelmapper.ModelMapper;

import java.util.List;

public final class TeachersMapper {

    public static List<TeacherDTO> map(List<Teacher> teachers) {
        return Util.mapList(teachers,TeacherDTO.class);
    }

    public static TeacherDTO map(Teacher teacher) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(teacher,TeacherDTO.class);
    }

    public static Teacher map(TeacherDTO teacherDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(teacherDTO, Teacher.class);
    }

}
