package com.interview.students.service;

import com.interview.students.domain.Group;
import com.interview.students.domain.Teacher;
import com.interview.students.exception.NoDataFoundException;
import com.interview.students.mapper.GroupsMapper;
import com.interview.students.model.GroupDTO;
import com.interview.students.payload.response.GetAllGroupsResponse;
import com.interview.students.repo.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.interview.students.mapper.GroupsMapper.map;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository repository;
    private final TeacherService teacherService;

    public GetAllGroupsResponse getGroups() {
        log.info("Get Group");
        return new GetAllGroupsResponse(repository.count(), map(repository.findAll()));
    }

    public GroupDTO getGroupById(Integer id) {
        log.info("Get Group by Id {}", id);
        Group group = null;
        try {
            group = repository.findById(id).get() ;
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        return GroupsMapper.map(group);
    }

    @Transactional
    public GroupDTO createGroup(Group group) {
        log.info("Create Group");
        teacherService.createTeachers(group.getTeachers());
        Group savedGroup = repository.save(group);
        return map(savedGroup);
    }

    @Transactional
    public GroupDTO updateGroup(Group updateGroup, Integer id) {
        log.info("Update Group");
        Group group = null;
        try {
            for (Teacher updateTeacher: updateGroup.getTeachers()) {
                teacherService.updateTeacher(updateTeacher,updateTeacher.getTeacherId());
            }
            group = repository.findById(id).get();
            group.setGroupName(updateGroup.getGroupName());
            group.setTeachers(updateGroup.getTeachers());
        } catch (NoSuchElementException ex) {
            throw new NoDataFoundException();
        }
        Group savedGroup = repository.save(group);
        return GroupsMapper.map(savedGroup);
    }

    public void deleteGroup(Integer id) {
        log.info("Remove Group");
        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new NoDataFoundException();
        }
    }
}
