package com.example.diagnostico.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateStudentRequest {

    private String name;

    private Long teacherId;

    private List<Long> subjectIds;
}
