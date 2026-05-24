package com.yo.day1.service;

import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.dto.teacher.TeacherResponse;
import com.yo.day1.dto.teacher.TeacherUpsertRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<TeacherResponse> findAll(String search, Pageable pageable);

    TeacherResponse findById(Long id);

    TeacherResponse create(TeacherUpsertRequest request);

    TeacherResponse update(Long id, TeacherUpsertRequest request);

    void delete(Long id);

    Teacher getTeacher(Long id);
}
