package com.example.diagnostico.services.implementation;

import com.example.diagnostico.persitance.entities.Student;
import com.example.diagnostico.persitance.entities.Subject;
import com.example.diagnostico.persitance.entities.pivots.StudentSubject;
import com.example.diagnostico.persitance.repositories.IStudentRepository;
import com.example.diagnostico.services.interfaces.IStudentService;
import com.example.diagnostico.services.interfaces.IStudentSubjectService;
import com.example.diagnostico.services.interfaces.ISubjectService;
import com.example.diagnostico.services.interfaces.ITeacherService;
import com.example.diagnostico.web.dtos.requests.CreateStudentRequest;
import com.example.diagnostico.web.dtos.requests.UpdateStudentRequest;
import com.example.diagnostico.web.dtos.responses.BaseResponse;
import com.example.diagnostico.web.mappers.StudentMapper;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentMapper studentMapper;
    private final IStudentRepository repository;
    private final ITeacherService teacherService;
    private final ISubjectService subjectService;
    private final IStudentSubjectService studentSubjectService;

    public StudentServiceImpl(StudentMapper studentMapper, IStudentRepository repository, ITeacherService teacherService, ISubjectService subjectService, IStudentSubjectService studentSubjectService) {
        this.studentMapper = studentMapper;
        this.repository = repository;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.studentSubjectService = studentSubjectService;
    }

    @Override
    public BaseResponse create(CreateStudentRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setTeacher(teacherService.findAndEnsureExists(request.getTeacherId()));

        repository.save(student);

        if(request.getSubjectIds() != null) {
            setStudentSubjectsListToUser(request.getSubjectIds(), student);
        }

        return BaseResponse.builder()
                .data(studentMapper.toCreateStudentResponse(student))
                .message("Student created successfully")
                .success(true)
                .httpStatus(HttpStatus.CREATED)
                .build();

    }
    @Override
    public BaseResponse get(Long id) {
        Student student = this.findAndEnsureExists(id);

        return BaseResponse.builder()
                .data(studentMapper.toCreateStudentResponse(student))
                .message("Student retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse get() {
        List<Student> students = repository.findAll();

        return BaseResponse.builder()
                .data(students.stream().map(studentMapper::toCreateStudentResponse).toList())
                .message("Students retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();

    }

    @Override
    public BaseResponse update(Long id, UpdateStudentRequest request) {
        Student student = this.findAndEnsureExists(id);

        student.setName(request.getName());
        student.setTeacher(teacherService.findAndEnsureExists(request.getTeacherId()));

        repository.save(student);

        if(request.getSubjectIds() != null) {
            setStudentSubjectsListToUser(request.getSubjectIds(), student);
        }

        return BaseResponse.builder()
                .data(studentMapper.toCreateStudentResponse(student))
                .message("Student updated successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public List<Student> getStudentsByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    private Student findAndEnsureExists(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    private void setStudentSubjectsListToUser(@NotNull List<Long> studentSubjectsIds, Student student) {

        studentSubjectService.deleteByStudentId(student.getId());


        for (Long subjectId : studentSubjectsIds) {
            Subject subject = subjectService.findAndEnsureExists(subjectId);
            StudentSubject newStudentSubject = studentSubjectService.create(student, subject);
            student.getStudentSubjects().add(newStudentSubject);
        }

        repository.save(student);
    }
}
