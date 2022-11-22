package com.interview.students.payload.response;

import com.interview.students.model.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllStudentsResponse {
    private long total;
    private List<StudentDTO> students;
}