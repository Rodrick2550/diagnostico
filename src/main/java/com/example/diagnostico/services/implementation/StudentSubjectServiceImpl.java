package com.example.diagnostico.services.implementation;

import com.example.diagnostico.persitance.entities.Student;
import com.example.diagnostico.persitance.entities.Subject;
import com.example.diagnostico.persitance.entities.pivots.StudentSubject;
import com.example.diagnostico.persitance.entities.projections.StudentProjection;
import com.example.diagnostico.persitance.entities.projections.SubjectProjection;
import com.example.diagnostico.persitance.repositories.IStudentSubjectRepository;
import com.example.diagnostico.services.interfaces.IStudentSubjectService;
import com.example.diagnostico.web.dtos.responses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSubjectServiceImpl implements IStudentSubjectService {

    private final IStudentSubjectRepository repository;

    public StudentSubjectServiceImpl(IStudentSubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public StudentSubject create(Student student, Subject subject) {
        if(repository.existsByStudentIdAndSubjectId(student.getId(), subject.getId())) {
            return repository.findByStudentIdAndSubjectId(student.getId(), subject.getId());
        }

        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setStudent(student);
        studentSubject.setSubject(subject);
        return repository.save(studentSubject);
    }

    @Override
    public BaseResponse listAllSubjectsByStudentId(Long studentId) {
        List<SubjectProjection> subjectProjections = repository.listAllSubjectsByStudentId(studentId);
        List<Subject> subjects = subjectProjections.stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(subjects)
                .message("Subjects retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse listAllStudentsBySubjectId(Long subjectId) {
        List<StudentProjection> studentProjections = repository.listAllStudentsBySubjectId(subjectId);
        List<Student> students = studentProjections.stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(students)
                .message("Students retrieved successfully")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void deleteByStudentId(Long studentId) {
        List<StudentSubject> studentSubjects = repository.findByStudentId(studentId);
        repository.deleteAll(studentSubjects);
    }

    private Subject from(SubjectProjection projection) {
        Subject subject = new Subject();
        subject.setId(projection.getId());
        subject.setName(projection.getName());
        return subject;
    }

    private Student from(StudentProjection projection) {
        Student student = new Student();
        student.setId(projection.getId());
        student.setName(projection.getName());
        return student;
    }


}
