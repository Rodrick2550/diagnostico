package com.example.diagnostico.services.interfaces;

import com.example.diagnostico.persitance.entities.Student;
import com.example.diagnostico.web.dtos.requests.CreateStudentRequest;
import com.example.diagnostico.web.dtos.requests.UpdateStudentRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;

import java.util.List;

public interface IStudentService {

    BaseResponse create(CreateStudentRequest request);
    BaseResponse get(Long id);
    BaseResponse get();
    BaseResponse update(Long id, UpdateStudentRequest request);
    List<Student> getStudentsByIds(List<Long> ids);
}
