package com.example.register_login_jwt.service;

import com.example.register_login_jwt.model.dto.RoomDTO;
import com.example.register_login_jwt.model.request.RoomRequest;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    RoomDTO getRoomById(UUID roomId);
    UUID addRoom(RoomRequest roomRequest);
    UUID updateRoom(RoomRequest roomRequest, UUID roomId);
    UUID deleteRoom(UUID roomId);

    List<RoomDTO> getAllRooms(UUID orgId);
}
