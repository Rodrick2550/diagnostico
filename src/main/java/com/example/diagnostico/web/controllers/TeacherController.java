package com.example.diagnostico.web.controllers;

import com.example.diagnostico.services.interfaces.ITeacherService;
import com.example.diagnostico.web.dtos.requests.CreateTeacherRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final ITeacherService teacherService;

    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateTeacherRequest request) {
        BaseResponse response = teacherService.create(request);
        return response.apply();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        BaseResponse response = teacherService.get(id);
        return response.apply();
    }

    @GetMapping
    public ResponseEntity<BaseResponse> get() {
        BaseResponse response = teacherService.get();
        return response.apply();
    }
}
