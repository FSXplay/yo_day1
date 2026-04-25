package com.yo.day1.service.impl;

import com.yo.day1.domain.entity.Course;
import com.yo.day1.repository.CourseRepository;
import com.yo.day1.service.CourseService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
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

    public Optional<Course> updateById(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setCourseCode(updatedCourse.getCourseCode());
                    existingCourse.setName(updatedCourse.getName());
                    existingCourse.setDescription(updatedCourse.getDescription());
                    existingCourse.setTuitionFee(updatedCourse.getTuitionFee());
                    existingCourse.setTotalSessions(updatedCourse.getTotalSessions());
                    existingCourse.setIsActive(updatedCourse.getIsActive());
                    return courseRepository.save(existingCourse);
                });
    }

    public Optional<Course> deleteById(Long id) {
        if (courseRepository.countClassesByCourseId(id) > 0) {
            throw new DataIntegrityViolationException("Cannot delete course with available classes");
        }
        Optional<Course> courseToDelete = courseRepository.findById(id);
        courseRepository.delete(courseToDelete.get());
        return courseToDelete;
    }
}
