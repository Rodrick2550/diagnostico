package com.example.diagnostico.persitance.repositories;

import com.example.diagnostico.persitance.entities.pivots.StudentSubject;
import com.example.diagnostico.persitance.entities.projections.StudentProjection;
import com.example.diagnostico.persitance.entities.projections.SubjectProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentSubjectRepository extends JpaRepository<StudentSubject, Long>{
    Boolean existsByStudentIdAndSubjectId(Long studentId, Long subjectId);

    StudentSubject findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    List<StudentSubject> findByStudentId(Long studentId);

    @Query(value = "SELECT subjects.* FROM student_subjects " +
            "INNER JOIN subjects ON student_subjects.subject_id = subjects.id " +
            "WHERE student_subjects.student_id = :studentId", nativeQuery = true)
    List<SubjectProjection> listAllSubjectsByStudentId(Long studentId);

    @Query(value = "SELECT students.* FROM student_subjects " +
            "INNER JOIN students ON student_subjects.student_id = students.id " +
            "WHERE student_subjects.subject_id = :subjectId", nativeQuery = true)
    List<StudentProjection> listAllStudentsBySubjectId(Long subjectId);
}
