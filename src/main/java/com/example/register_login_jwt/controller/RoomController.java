package com.example.register_login_jwt.controller;

import com.example.register_login_jwt.model.request.RoomRequest;
import com.example.register_login_jwt.model.response.BodyResponse;
import com.example.register_login_jwt.repository.RoomRepository;

import com.example.register_login_jwt.service.RoomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
@SecurityRequirement(name = "bearerAuth")
public class RoomController {
    private final RoomService roomService;
    private final RoomRepository roomRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable("id")UUID roomId){
        return BodyResponse.getBodyResponse(roomService.getRoomById(roomId));
    }

    @PostMapping
    public ResponseEntity<?> addRoom(@RequestBody RoomRequest roomRequest){
        UUID getId = roomService.addRoom(roomRequest);
        return BodyResponse.getBodyResponse(roomService.getRoomById(getId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoom(@RequestBody RoomRequest roomRequest,@PathVariable("id") UUID roomId){
        UUID id = roomService.updateRoom(roomRequest, roomId);
        return BodyResponse.getBodyResponse(roomService.getRoomById(id));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable UUID roomId){
        return BodyResponse.getBodyResponse(roomService.deleteRoom(roomId));
    }


    @GetMapping("/{orgId}/get-all-rooms-in-org")
    public ResponseEntity<?> getAllRooms(@PathVariable("orgId") UUID orgId){
        return BodyResponse.getBodyResponse(roomService.getAllRooms(orgId));
    }

}