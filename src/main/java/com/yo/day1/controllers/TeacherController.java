package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.dto.teacher.TeacherResponse;
import com.yo.day1.dto.teacher.TeacherUpsertRequest;
import com.yo.day1.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<Page<TeacherResponse>> findAll(
        @RequestParam(required = false) String search,
        Pageable pageable
    ) {
        return ApiResponse.success(teacherService.findAll(search, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<TeacherResponse> findById(@PathVariable Long id) {
        return ApiResponse.success(teacherService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<TeacherResponse> create(
        @Valid @RequestBody TeacherUpsertRequest request
    ) {
        return ApiResponse.success(
            "Teacher created",
            teacherService.create(request)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ACADEMIC_STAFF')")
    public ApiResponse<TeacherResponse> update(
        @PathVariable Long id,
        @Valid @RequestBody TeacherUpsertRequest request
    ) {
        return ApiResponse.success(
            "Teacher updated",
            teacherService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ApiResponse.successMessage("Teacher deleted");
    }
}
