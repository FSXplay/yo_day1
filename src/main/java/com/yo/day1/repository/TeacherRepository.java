package com.yo.day1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yo.day1.domain.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
