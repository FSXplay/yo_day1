package com.yo.day1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.repository.TeacherRepository;
import com.yo.day1.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Optional<Teacher> updateById(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id)
                .map(existingTeacher -> {
                    existingTeacher.setTeacherCode(updatedTeacher.getTeacherCode());
                    existingTeacher.setFullName(updatedTeacher.getFullName());
                    existingTeacher.setEmail(updatedTeacher.getEmail());
                    existingTeacher.setPhone(updatedTeacher.getPhone());
                    existingTeacher.setTeacherRole(updatedTeacher.getTeacherRole());
                    existingTeacher.setIsActive(updatedTeacher.getIsActive());
                    return teacherRepository.save(existingTeacher);
                });
    }

    public Optional<Teacher> deleteById(Long id) {
        if (teacherRepository.existsLinkedEntities(id) == 1) {
            throw new DataIntegrityViolationException("Cannot delete teacher with linked entities");
        }
        Optional<Teacher> teacherToDelete = teacherRepository.findById(id);
        teacherRepository.delete(teacherToDelete.get());
        return teacherToDelete;
    }
}
