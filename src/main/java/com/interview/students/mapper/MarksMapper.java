package com.interview.students.mapper;

import com.interview.students.domain.Marks;
import com.interview.students.model.MarksDTO;
import com.interview.students.utility.Util;
import org.modelmapper.ModelMapper;

import java.util.List;

public final class MarksMapper {
    public static List<MarksDTO> map(List<Marks> marksList) {
        return Util.mapList(marksList,MarksDTO.class);
    }

    public static MarksDTO map(Marks marks) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(marks,MarksDTO.class);
    }

    public static Marks map(MarksDTO marksDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(marksDTO, Marks.class);
    }
}
