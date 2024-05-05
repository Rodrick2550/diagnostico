package com.example.diagnostico.web.controllers;

import com.example.diagnostico.services.interfaces.IStudentSubjectService;
import com.example.diagnostico.web.dtos.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-subject")
public class StudentSubjectController {

    private final IStudentSubjectService studentSubjectsService;

    public StudentSubjectController(IStudentSubjectService studentSubjectsService) {
        this.studentSubjectsService = studentSubjectsService;
    }

    @GetMapping("/subject/student/{studentId}")
    public ResponseEntity<BaseResponse> listAllSubjectsByStudentId(@PathVariable Long studentId) {
        BaseResponse response = studentSubjectsService.listAllSubjectsByStudentId(studentId);
        return response.apply();
    }

    @GetMapping("/student/subject/{subjectId}")
    public ResponseEntity<BaseResponse> listAllStudentsBySubjectId(@PathVariable Long subjectId) {
        BaseResponse response = studentSubjectsService.listAllStudentsBySubjectId(subjectId);
        return response.apply();
    }
}
