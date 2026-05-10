package com.yo.day1.service;

import java.util.List;
import java.util.Optional;

import com.yo.day1.dto.room.RoomResponse;
import com.yo.day1.dto.room.RoomUpsertRequest;

public interface RoomService {
    public List<RoomResponse> findAll();
    
    public Optional<RoomResponse> findById(Long id);

    public RoomResponse save(RoomUpsertRequest req);

    public Optional<RoomResponse> save(Long id, RoomUpsertRequest req);
}
