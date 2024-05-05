package com.example.diagnostico.web.controllers;

import com.example.diagnostico.services.interfaces.ISubjectService;
import com.example.diagnostico.web.dtos.requests.CreateSubjectRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateSubjectRequest request) {
        BaseResponse response = subjectService.create(request);
        return response.apply();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse response = subjectService.get(id);
        return response.apply();
    }
}
