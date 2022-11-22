package com.interview.students.controller;

import com.interview.students.model.StudentDTO;
import com.interview.students.payload.response.GetAllStudentsResponse;
import com.interview.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.interview.students.mapper.StudentsMapper.map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<GetAllStudentsResponse> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentsById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(map(studentService.getStudentById(id)));
    }

    @PostMapping()
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return new ResponseEntity(map(studentService.createStudent(map(studentDTO))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO teacherDTO,
                                                    @PathVariable(name = "id") Integer id) {
        return new ResponseEntity(map(studentService.updateStudent(map(teacherDTO), id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudent(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(params = {"teacher"})
    public ResponseEntity<List<StudentDTO>> getStudentsByTeacherId(@RequestParam("teacher") Integer id) {
        return ResponseEntity.ok(map(studentService.getStudentByTeacherId(id)));
    }

}
