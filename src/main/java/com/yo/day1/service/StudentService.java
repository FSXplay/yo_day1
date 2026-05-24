package com.yo.day1.service;

import com.yo.day1.domain.entity.Student;
import com.yo.day1.dto.student.StudentResponse;
import com.yo.day1.dto.student.StudentUpsertRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    public Page<StudentResponse> findAll(Pageable pageable);

    public StudentResponse findById(Long id);

    public StudentResponse create(StudentUpsertRequest request);

    public StudentResponse update(Long id, StudentUpsertRequest request);

    public void delete(Long id);

    public List<StudentResponse> findByParentId(Long parentId);

    public Student getStudentForParent(Long studentId, Long parentId);

    public Student getStudent(Long id);
}
