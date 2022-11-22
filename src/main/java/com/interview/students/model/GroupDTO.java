package com.interview.students.model;

import com.interview.students.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDTO {
    private Integer groupId;
    private String groupName;
    private List<Teacher> teachers;
}
