package com.interview.students.mapper;

import com.interview.students.domain.Group;
import com.interview.students.model.GroupDTO;
import com.interview.students.utility.Util;
import org.modelmapper.ModelMapper;

import java.util.List;

public final class GroupsMapper {

    public static List<GroupDTO> map(List<Group> groups) {
        return Util.mapList(groups,GroupDTO.class);
    }

    public static GroupDTO map(Group group) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(group,GroupDTO.class);
    }

    public static Group map(GroupDTO groupDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(groupDTO, Group.class);
    }

}
