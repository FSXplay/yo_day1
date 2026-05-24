package com.yo.day1.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Teacher;
import com.yo.day1.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Teacher>>> getTeachers() {
        return ResponseEntity.ok(ApiResponse.success(teacherService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Teacher>> getTeacherById(@PathVariable("id") Long id) {
        return teacherService.findById(id).map(value -> 
            ResponseEntity.ok(ApiResponse.success(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Teacher>> create(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(ApiResponse.success(teacherService.save(teacher)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Teacher>> update(
        @PathVariable("id") Long id,
        @RequestBody Teacher updatedTeacher
    ) {
        return teacherService.updateById(id, updatedTeacher).map(value -> 
            ResponseEntity.ok(ApiResponse.success(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Teacher>> delete(@PathVariable Long id) {
        return teacherService.deleteById(id).map(value -> 
            ResponseEntity.ok(ApiResponse.success(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
