package com.yo.day1.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yo.day1.common.ApiResponse;
import com.yo.day1.dto.room.RoomResponse;
import com.yo.day1.dto.room.RoomUpsertRequest;
import com.yo.day1.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ApiResponse<List<RoomResponse>> findAll() {
        return ApiResponse.success(roomService.findAll());
    }

    @GetMapping("{id}")
    public ApiResponse<RoomResponse> findById(@PathVariable Long id) {
        return roomService.findById(id)
                .map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.error("Not found", new RoomResponse()));
    }

    @PostMapping
    public ApiResponse<RoomResponse> save(@RequestBody RoomUpsertRequest req) {
        return ApiResponse.success(roomService.save(req));
    }

    @PutMapping("{id}")
    public ApiResponse<RoomResponse> save(@PathVariable Long id, @RequestBody RoomUpsertRequest req) {
        return roomService.save(id, req)
                .map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.error("Not found", new RoomResponse()));
    } 
}
