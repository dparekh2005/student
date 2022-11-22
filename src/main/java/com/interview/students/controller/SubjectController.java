package com.interview.students.controller;

import com.interview.students.model.SubjectDTO;
import com.interview.students.service.SubjectService;
import com.interview.students.payload.response.GetAllSubjectsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.interview.students.mapper.SubjectsMapper.map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;
    
    @GetMapping
    public ResponseEntity<GetAllSubjectsResponse> getSubjects() {
        return ResponseEntity.ok(subjectService.getSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectsById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(map(subjectService.getSubjectById(id)));
    }

    @PostMapping()
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO) {
        return new ResponseEntity(map(subjectService.createSubject(map(subjectDTO))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO,
                                                    @PathVariable(name = "id") Integer id) {
        return new ResponseEntity(map(subjectService.updateSubject(map(subjectDTO), id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable(name = "id") Integer id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
