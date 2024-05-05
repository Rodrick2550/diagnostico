package com.example.diagnostico.services.interfaces;

import com.example.diagnostico.persitance.entities.Student;
import com.example.diagnostico.persitance.entities.Subject;
import com.example.diagnostico.persitance.entities.pivots.StudentSubject;
import com.example.diagnostico.web.dtos.responses.BaseResponse;

public interface IStudentSubjectService {

    StudentSubject create(Student student, Subject subject);
    BaseResponse listAllSubjectsByStudentId(Long studentId);
    BaseResponse listAllStudentsBySubjectId(Long subjectId);

    void deleteByStudentId(Long studentId);
}
