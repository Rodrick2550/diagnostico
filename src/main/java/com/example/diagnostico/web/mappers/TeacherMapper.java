package com.example.diagnostico.web.mappers;

import com.example.diagnostico.persitance.entities.Teacher;
import com.example.diagnostico.web.dtos.responses.CreateTeacherResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TeacherMapper {



    CreateTeacherResponse toCreateTeacherResponse(Teacher teacher);




}
