package com.yo.day1.dto.teacher;

import com.yo.day1.domain.enums.TeacherRole;
import java.time.LocalDateTime;

public record TeacherResponse(
    Long id,
    String teacherCode,
    String fullName,
    String phone,
    String email,
    String address,
    String description,
    String workUnit,
    String experience,
    String achievement,
    TeacherRole teacherRole,
    Boolean isActive,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
