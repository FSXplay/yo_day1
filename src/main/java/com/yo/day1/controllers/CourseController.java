package com.yo.day1.controllers;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.domain.entity.Course;
import com.yo.day1.service.CourseService;
// import jakarta.websocket.server.PathParam; // TODO: @PathParam vs @PathVariable?
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getCourses() {
        return ResponseEntity.ok(ApiResponse.success(courseService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable("id") Long id) {

        return courseService.findById(id).map(value ->
                ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

//        Optional<Course> course = courseService.findById(id);
//        if (course.isPresent()) {
//            return ResponseEntity.ok(ApiResponse.success(course.get()));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }   

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        return ResponseEntity.ok(ApiResponse.success(courseService.save(course)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Course>> update(
            @PathVariable("id") Long id, 
            @RequestBody Course updatedCourse) {
        return courseService.updateById(id, updatedCourse).map(value -> 
            ResponseEntity.ok(ApiResponse.success(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable("id") Long id) {
        return courseService.deleteById(id).map(value ->
                ResponseEntity.ok(ApiResponse.success(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
