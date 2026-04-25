package com.yo.day1.repository;

import com.yo.day1.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @NativeQuery("SELECT COUNT(*) FROM course_classes WHERE course_id = :courseId")
    int countClassesByCourseId(Long courseId);
}
