package com.interview.students.payload.response;

import com.interview.students.model.GroupDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllGroupsResponse {
    private long total;
    private List<GroupDTO> groups;
}