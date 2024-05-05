package com.example.diagnostico.services.interfaces;

import com.example.diagnostico.persitance.entities.Teacher;
import com.example.diagnostico.web.dtos.requests.CreateTeacherRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;

public interface ITeacherService {

    BaseResponse create(CreateTeacherRequest request);
    BaseResponse get(Long id);
    BaseResponse get();
    Teacher findAndEnsureExists(Long id);
}
