package com.yo.day1.dto.student;

import com.yo.day1.domain.enums.Gender;
import com.yo.day1.domain.enums.StudentStatus;
import com.yo.day1.dto.parent.ParentResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private Long id;

    private String studentCode;

    private String fullName;

    private LocalDate dateOfBirth;

    private Gender gender = Gender.OTHER;

    private String gradeLevel;

    private String schoolName;

    private String phone;

    private ParentResponse parent;

    private StudentStatus status = StudentStatus.ACTIVE;

    private BigDecimal latestScore = BigDecimal.ZERO;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
