package com.yo.day1.dto.room;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RoomResponse {
    private Long id;

    private String roomCode;

    private String name;
    
    private int capacity;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
