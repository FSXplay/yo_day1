package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Course;
import com.yo.day1.repository.CourseRepository;
import com.yo.day1.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course updateById(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setCourseCode(updatedCourse.getCourseCode());
                    existingCourse.setName(updatedCourse.getName());
                    existingCourse.setDescription(updatedCourse.getDescription());
                    existingCourse.setTuitionFee(updatedCourse.getTuitionFee());
                    existingCourse.setTotalSessions(updatedCourse.getTotalSessions());
                    existingCourse.setIsActive(updatedCourse.getIsActive());
                    return courseRepository.save(existingCourse);
                })
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Optional<Course> deleteById(Long id) {
        // TODO: Need to discuss on whether to soft or hard delete
        return courseRepository.findById(id).map(course -> {
            course.setIsActive((byte) 0);
            return courseRepository.save(course);
        });
    }
}
