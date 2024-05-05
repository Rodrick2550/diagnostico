package com.example.diagnostico.web.dtos.responses;

import com.example.diagnostico.persitance.entities.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateSubjectResponse {

    private String name;

    private List<Student> students;
}
