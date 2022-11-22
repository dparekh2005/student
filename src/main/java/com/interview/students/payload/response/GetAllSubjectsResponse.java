package com.interview.students.payload.response;

import com.interview.students.model.SubjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllSubjectsResponse {
    private long total;
    private List<SubjectDTO> subjects;
}