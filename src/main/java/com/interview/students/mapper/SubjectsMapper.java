package com.interview.students.mapper;

import com.interview.students.domain.Subject;
import com.interview.students.model.SubjectDTO;
import com.interview.students.utility.Util;
import org.modelmapper.ModelMapper;

import java.util.List;

public final class SubjectsMapper {
    public static List<SubjectDTO> map(List<Subject> subjects) {
        return Util.mapList(subjects,SubjectDTO.class);
    }

    public static SubjectDTO map(Subject subject) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subject,SubjectDTO.class);
    }

    public static Subject map(SubjectDTO subjectDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subjectDTO, Subject.class);
    }

}
