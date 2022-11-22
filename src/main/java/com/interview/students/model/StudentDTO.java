package com.interview.students.model;

import com.interview.students.domain.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Group group;
    private List<MarksDTO> marksList;
}
