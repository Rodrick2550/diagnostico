package com.example.diagnostico.services.implementation;

import com.example.diagnostico.persitance.entities.Teacher;
import com.example.diagnostico.persitance.repositories.ITeacherRepository;
import com.example.diagnostico.services.interfaces.ITeacherService;
import com.example.diagnostico.web.dtos.requests.CreateTeacherRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;
import com.example.diagnostico.web.mappers.TeacherMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherMapper teacherMapper;
    private final ITeacherRepository repository;

    public TeacherServiceImpl(TeacherMapper teacherMapper, ITeacherRepository repository) {
        this.teacherMapper = teacherMapper;
        this.repository = repository;
    }

    @Override
    public BaseResponse create(CreateTeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setName(request.getName());

        repository.save(teacher);

        return BaseResponse.builder()
                .data(teacherMapper.toCreateTeacherResponse(teacher))
                .message("Tutor created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse get(Long id) {
        Teacher teacher = this.findAndEnsureExists(id);

        return BaseResponse.builder()
                .data(teacherMapper.toCreateTeacherResponse(teacher))
                .message("Tutor retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse get() {
        List<Teacher> tutors = repository.findAll();

        return BaseResponse.builder()
                .data(tutors.stream().map(teacherMapper::toCreateTeacherResponse).toList())
                .message("Teacher retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public Teacher findAndEnsureExists(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }
}
