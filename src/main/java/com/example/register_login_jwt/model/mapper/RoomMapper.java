package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.RoomDTO;
import com.example.register_login_jwt.model.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    RoomDTO toRoomDto(Room room);
    List<RoomDTO> toRoomDtos(List<Room> rooms);
}
