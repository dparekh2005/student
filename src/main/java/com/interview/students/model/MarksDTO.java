package com.interview.students.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarksDTO {
    private Integer markId;
    private Integer studentId;
    private Integer subjectId;
    private Date date;
    private Integer mark;
}
