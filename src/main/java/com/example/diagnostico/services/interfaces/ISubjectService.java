package com.example.diagnostico.services.interfaces;

import com.example.diagnostico.persitance.entities.Subject;
import com.example.diagnostico.web.dtos.requests.CreateSubjectRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;

public interface ISubjectService {

    BaseResponse create(CreateSubjectRequest request);
    BaseResponse get(Long id);

    Subject findAndEnsureExists(Long id);
}
