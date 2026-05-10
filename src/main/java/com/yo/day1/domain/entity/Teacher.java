package com.yo.day1.domain.entity;

import com.yo.day1.domain.AuditableEntity;
import com.yo.day1.domain.enums.TeacherRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class Teacher extends AuditableEntity {
    
    @Column(columnDefinition = "varchar(20)")
    private String teacherCode;
    
    @Column(columnDefinition = "varchar(100)")
    private String fullName;

    @Column(columnDefinition = "varchar(100)")
    private String email;

    @Column(columnDefinition = "varchar(20)")
    private String phone;

    @Enumerated(EnumType.STRING)
    private TeacherRole teacherRole;

    private byte isActive;
}
