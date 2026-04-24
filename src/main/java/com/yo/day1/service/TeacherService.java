package com.yo.day1.service;

import com.yo.day1.domain.entity.Teacher;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();

    Optional<Teacher> findById(Long id);
}
