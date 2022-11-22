package com.interview.students.payload.response;

import com.interview.students.model.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTeachersResponse {
    private long total;
    private List<TeacherDTO> teachers;
}