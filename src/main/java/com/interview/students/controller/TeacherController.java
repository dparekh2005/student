package com.interview.students.controller;

import com.interview.students.mapper.TeachersMapper;
import com.interview.students.model.TeacherDTO;
import com.interview.students.payload.response.GetAllTeachersResponse;
import com.interview.students.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.interview.students.mapper.TeachersMapper.map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<GetAllTeachersResponse> getTeachers() {
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeachersById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(map(teacherService.getTeacherById(id)));
    }

    @PostMapping()
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) {
        return new ResponseEntity(map(teacherService.createTeacher(map(teacherDTO))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO,
                                                    @PathVariable(name = "id") Integer id) {
        return new ResponseEntity(map(teacherService.updateTeacher(map(teacherDTO), id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable(name = "id") Integer id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
