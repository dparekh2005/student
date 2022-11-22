package com.interview.students.payload.response;

import com.interview.students.model.MarksDTO;
import com.interview.students.model.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMarksResponse {
    private long total;
    private List<MarksDTO> marksList;
}