package com.example.diagnostico.web.mappers;

import com.example.diagnostico.persitance.entities.Subject;
import com.example.diagnostico.web.dtos.responses.CreateSubjectResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SubjectMapper {



    CreateSubjectResponse toCreateSubjectResponse(Subject subject);


}
