package com.yo.day1.service.impl;

import com.yo.day1.common.exception.NotFoundException;
import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.dto.teacher.TeacherResponse;
import com.yo.day1.dto.teacher.TeacherUpsertRequest;
import com.yo.day1.repository.TeacherRepository;
import com.yo.day1.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Transactional(readOnly = true)
    public Page<TeacherResponse> findAll(String search, Pageable pageable) {
        if (search != null && !search.isBlank()) {
            return teacherRepository
                .findByFullNameContainingIgnoreCase(search, pageable)
                .map(this::toResponse);
        }
        return teacherRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public TeacherResponse findById(Long id) {
        return toResponse(getTeacher(id));
    }

    @Transactional
    public TeacherResponse create(TeacherUpsertRequest request) {
        Teacher teacher = new Teacher();
        apply(teacher, request);
        return toResponse(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherResponse update(Long id, TeacherUpsertRequest request) {
        Teacher teacher = getTeacher(id);
        apply(teacher, request);
        return toResponse(teacherRepository.save(teacher));
    }

    @Transactional
    public void delete(Long id) {
        teacherRepository.delete(getTeacher(id));
    }

    public Teacher getTeacher(Long id) {
        return teacherRepository
            .findById(id)
            .orElseThrow(() ->
                new NotFoundException("Teacher not found: " + id)
            );
    }

    private void apply(Teacher teacher, TeacherUpsertRequest request) {
        teacher.setTeacherCode(request.teacherCode());
        teacher.setFullName(request.fullName());
        teacher.setPhone(request.phone());
        teacher.setEmail(request.email());
        teacher.setAddress(request.address());
        teacher.setDescription(request.description());
        teacher.setWorkUnit(request.workUnit());
        teacher.setExperience(request.experience());
        teacher.setAchievement(request.achievement());
        teacher.setTeacherRole(request.teacherRole());
        teacher.setIsActive(
            request.isActive() == null ? true : request.isActive()
        );
    }

    private TeacherResponse toResponse(Teacher teacher) {
        return new TeacherResponse(
            teacher.getId(),
            teacher.getTeacherCode(),
            teacher.getFullName(),
            teacher.getPhone(),
            teacher.getEmail(),
            teacher.getAddress(),
            teacher.getDescription(),
            teacher.getWorkUnit(),
            teacher.getExperience(),
            teacher.getAchievement(),
            teacher.getTeacherRole(),
            teacher.getIsActive(),
            teacher.getCreatedAt(),
            teacher.getUpdatedAt()
        );
    }
}
