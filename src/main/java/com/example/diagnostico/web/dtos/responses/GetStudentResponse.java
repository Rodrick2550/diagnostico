package com.example.diagnostico.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetStudentResponse {

    private String name;

    private String teacherName;

    private List<String> subjects;
}
