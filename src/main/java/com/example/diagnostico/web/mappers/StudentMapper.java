package com.example.diagnostico.web.mappers;

import com.example.diagnostico.persitance.entities.Student;
import com.example.diagnostico.persitance.entities.Subject;
import com.example.diagnostico.persitance.entities.pivots.StudentSubject;
import com.example.diagnostico.web.dtos.responses.CreateStudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "teacher.name", target = "teacherName")
    @Mapping(source = "studentSubjects", target = "subjects")
    CreateStudentResponse toCreateStudentResponse(Student student);

    default List<String> mapStudentSubjectsToSubjectNames(List<StudentSubject> studentSubjects) {
        return studentSubjects.stream()
                .map(StudentSubject::getSubject)
                .map(Subject::getName)
                .collect(Collectors.toList());
    }
}
