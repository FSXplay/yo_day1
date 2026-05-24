package com.yo.day1.repository;

import com.yo.day1.domain.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    org.springframework.data.domain.Page<
        Teacher
    > findByFullNameContainingIgnoreCase(
        String name,
        org.springframework.data.domain.Pageable pageable
    );
}
