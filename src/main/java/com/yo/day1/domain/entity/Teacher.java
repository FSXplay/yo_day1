package com.yo.day1.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teachers")
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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

    public enum TeacherRole {
        TEACHER,
        ASSISTANT,
        BOTH
    }
}
