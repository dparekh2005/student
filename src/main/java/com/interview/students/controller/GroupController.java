package com.interview.students.controller;

import com.interview.students.mapper.GroupsMapper;
import com.interview.students.model.GroupDTO;
import com.interview.students.payload.response.GetAllGroupsResponse;
import com.interview.students.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static com.interview.students.mapper.GroupsMapper.map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<GetAllGroupsResponse> getGroups() {
        return ResponseEntity.ok(groupService.getGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupsById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        return new ResponseEntity(groupService.createGroup(map(groupDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDTO,
                                                    @PathVariable(name = "id") Integer id) {
        return new ResponseEntity(groupService.updateGroup(GroupsMapper.map(groupDTO), id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable(name = "id") Integer id) {
        groupService.deleteGroup(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
