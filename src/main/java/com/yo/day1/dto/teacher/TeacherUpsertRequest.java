package com.yo.day1.dto.teacher;

import com.yo.day1.domain.enums.TeacherRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TeacherUpsertRequest(
    @NotBlank @Size(max = 20) String teacherCode,
    @NotBlank @Size(max = 100) String fullName,
    @NotBlank @Size(max = 20) String phone,
    @Size(max = 100) String email,
    @Size(max = 255) String address,
    @Size(max = 255) String description,
    @Size(max = 100) String workUnit,
    @Size(max = 500) String experience,
    @Size(max = 500) String achievement,
    @NotNull TeacherRole teacherRole,
    Boolean isActive
) {}
