package com.yo.day1.controllers;

import java.util.List;

import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Parent;
import com.yo.day1.service.ParentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/parents")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Parent>>> getParents() {
        return ResponseEntity.ok(ApiResponse.success(parentService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Parent>> getParentById(@PathVariable("id") Long id) {
        return parentService.findById(id).map(value ->
            ResponseEntity.ok(ApiResponse.success(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Parent>> create(@RequestBody Parent parent) {
        return ResponseEntity.ok(ApiResponse.success(parentService.save(parent)));
    }
}
