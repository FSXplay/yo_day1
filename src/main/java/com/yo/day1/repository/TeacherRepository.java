package com.yo.day1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import com.yo.day1.domain.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @NativeQuery(value = "SELECT (EXISTS (SELECT 1 FROM users u WHERE u.teacher_id = :teacherId) " +
                   "OR EXISTS (SELECT 1 FROM users u WHERE u.parent_id = :teacherId))")
    int existsLinkedEntities(@Param("teacherId") Long teacherId);
}
