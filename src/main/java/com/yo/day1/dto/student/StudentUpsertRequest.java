package com.yo.day1.dto.student;

import com.yo.day1.domain.enums.Gender;
import com.yo.day1.domain.enums.StudentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record StudentUpsertRequest(
    @NotBlank @Size(max = 20) String studentCode,
    @NotBlank @Size(max = 100) String fullName,
    LocalDate dateOfBirth,
    @NotNull Gender gender,
    @Size(max = 30) String gradeLevel,
    @Size(max = 100) String schoolName,
    @Size(max = 20) String phone,
    Long parentId,
    @NotNull StudentStatus status,
    @NotNull @DecimalMin("0.0") BigDecimal latestScore,
    @Size(max = 255) String note
) {}
