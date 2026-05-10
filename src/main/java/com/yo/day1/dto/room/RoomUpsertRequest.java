package com.yo.day1.dto.room;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RoomUpsertRequest {
    @Length(min = 1, max = 20)
    private String roomCode;
    
    @Length(max = 100)    
    private String name;

    @Min(1)
    private int capacity;

    private String description;
}
