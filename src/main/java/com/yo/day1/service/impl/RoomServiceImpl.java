package com.yo.day1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yo.day1.domain.entity.Room;
import com.yo.day1.dto.room.RoomResponse;
import com.yo.day1.dto.room.RoomUpsertRequest;
import com.yo.day1.repository.RoomRepository;
import com.yo.day1.service.RoomService;

import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ModelMapper mapper;
    
    // Service methods

    public List<RoomResponse> findAll() {
        return roomRepository.findAll().stream()
                .map(r -> map(r))
                .toList();
    }

    public Optional<RoomResponse> findById(Long id) {
        return roomRepository.findById(id).map(this::map);
    }

    public RoomResponse save(RoomUpsertRequest req) {
        Room room = mapper.map(req, Room.class);
        Room response = roomRepository.save(room);
        return map(response);
    }

    public Optional<RoomResponse> save(Long id, RoomUpsertRequest req) {
        return roomRepository.findById(id)
            .map(existingRoom -> {
                mapper.map(req, existingRoom);
                return map(roomRepository.save(existingRoom));
            });
    }

    // Helper methods

    private RoomResponse map(Room room) {
        return mapper.map(room, RoomResponse.class);
    }
}
