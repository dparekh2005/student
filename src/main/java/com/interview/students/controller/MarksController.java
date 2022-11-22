package com.interview.students.controller;

import com.interview.students.domain.Marks;
import com.interview.students.domain.Student;
import com.interview.students.domain.Subject;
import com.interview.students.model.MarksDTO;
import com.interview.students.payload.response.GetAllMarksResponse;
import com.interview.students.service.MarksService;
import com.interview.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.interview.students.mapper.MarksMapper.map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/marks")
public class MarksController {
    private final MarksService marksService;
    private final StudentService studentService;
    
    @GetMapping
    public ResponseEntity<GetAllMarksResponse> getMarks() {
        return ResponseEntity.ok(marksService.getMarks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarksDTO> getMarksById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(map(marksService.getMarksById(id)));
    }

    @PostMapping()
    public ResponseEntity<MarksDTO> createMarks(@RequestBody MarksDTO marksDTO) {
        return new ResponseEntity(map(marksService.createMarks(map(marksDTO))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarksDTO> updateMarks(@RequestBody MarksDTO marksDTO,
                                                    @PathVariable(name = "id") Integer id) {
        return new ResponseEntity(map(marksService.updateMarks(map(marksDTO), id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarks(@PathVariable(name = "id") Integer id) {
        marksService.deleteMarks(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/mark")
    public ResponseEntity<MarksDTO> getMarksByStuden(@RequestParam("student") Integer id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(map(marksService.getMarksByStudent(student)));
    }

    @GetMapping(params = {"student"})
    public ResponseEntity<List<MarksDTO>> getMarksListByStudent(@RequestParam("student") Integer id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(map(marksService.getMarksListByStudent(student)));
    }
}
