package com.yo.day1.dto.student;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentResponse(
    Long id,
    String studentCode,
    String fullName,
    LocalDate dateOfBirth,
    String gender,
    String gradeLevel,
    String schoolName,
    String phone,
    Long parentId,
    String parentName,
    String status,
    BigDecimal latestScore,
    String note,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
