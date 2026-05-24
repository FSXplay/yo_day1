package com.yo.day1.service.impl;

import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.domain.entity.Parent;
import com.yo.day1.domain.entity.Student;
import com.yo.day1.dto.student.StudentResponse;
import com.yo.day1.dto.student.StudentUpsertRequest;
import com.yo.day1.repository.ParentRepository;
import com.yo.day1.repository.StudentRepository;
import com.yo.day1.service.StudentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    @Transactional(readOnly = true)
    public Page<StudentResponse> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public StudentResponse findById(Long id) {
        return toResponse(getStudent(id));
    }

    @Transactional
    public StudentResponse create(StudentUpsertRequest request) {
        Student student = new Student();
        apply(student, request);
        return toResponse(studentRepository.save(student));
    }

    @Transactional
    public StudentResponse update(Long id, StudentUpsertRequest request) {
        Student student = getStudent(id);
        apply(student, request);
        return toResponse(studentRepository.save(student));
    }

    @Transactional
    public void delete(Long id) {
        studentRepository.delete(getStudent(id));
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> findByParentId(Long parentId) {
        return studentRepository
            .findByParentId(parentId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public Student getStudentForParent(Long studentId, Long parentId) {
        Student student = getStudent(studentId);
        if (
            student.getParent() == null ||
            !student.getParent().getId().equals(parentId)
        ) {
            throw new org.springframework.security.access.AccessDeniedException(
                "Student does not belong to current parent account"
            );
        }
        return student;
    }

    public Student getStudent(Long id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() ->
                new NotFoundException("Student not found: " + id)
            );
    }

    private void apply(Student student, StudentUpsertRequest request) {
        student.setStudentCode(request.studentCode());
        student.setFullName(request.fullName());
        student.setDateOfBirth(request.dateOfBirth());
        student.setGender(request.gender());
        student.setGradeLevel(request.gradeLevel());
        student.setSchoolName(request.schoolName());
        student.setPhone(request.phone());
        student.setStatus(request.status());
        student.setLatestScore(request.latestScore());
        student.setNote(request.note());

        Parent parent =
            request.parentId() == null
                ? null
                : parentRepository
                      .findById(request.parentId())
                      .orElseThrow(() ->
                          new NotFoundException(
                              "Parent not found: " + request.parentId()
                          )
                      );
        student.setParent(parent);
    }

    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
            student.getId(),
            student.getStudentCode(),
            student.getFullName(),
            student.getDateOfBirth(),
            student.getGender().name(),
            student.getGradeLevel(),
            student.getSchoolName(),
            student.getPhone(),
            student.getParent() != null ? student.getParent().getId() : null,
            student.getParent() != null
                ? student.getParent().getFullName()
                : null,
            student.getStatus().name(),
            student.getLatestScore(),
            student.getNote(),
            student.getCreatedAt(),
            student.getUpdatedAt()
        );
    }
}
