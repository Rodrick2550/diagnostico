package com.example.diagnostico.persitance.repositories;

import com.example.diagnostico.persitance.entities.Student;
import com.example.diagnostico.persitance.entities.pivots.StudentSubject;
import com.example.diagnostico.persitance.entities.projections.StudentProjection;
import com.example.diagnostico.persitance.entities.projections.SubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {




}
