package com.example.diagnostico.services.implementation;

import com.example.diagnostico.persitance.entities.Subject;
import com.example.diagnostico.persitance.repositories.ISubjectRepository;
import com.example.diagnostico.services.interfaces.ISubjectService;
import com.example.diagnostico.web.dtos.requests.CreateSubjectRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;
import com.example.diagnostico.web.mappers.SubjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements ISubjectService {

    private final ISubjectRepository repository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(ISubjectRepository repository, SubjectMapper subjectMapper) {
        this.repository = repository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public BaseResponse create(CreateSubjectRequest request) {
        Subject subject = new Subject();
        subject.setName(request.getName());

        repository.save(subject);

        return BaseResponse.builder()
                .data(subjectMapper.toCreateSubjectResponse(subject))
                .message("Subject created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse get(Long id) {
        Subject subject = this.findAndEnsureExists(id);

        return BaseResponse.builder()
                .data(subjectMapper.toCreateSubjectResponse(subject))
                .message("Subject retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public Subject findAndEnsureExists(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }
}
