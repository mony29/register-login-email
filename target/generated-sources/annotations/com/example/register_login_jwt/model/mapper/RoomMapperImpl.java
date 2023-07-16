package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.RoomDTO;
import com.example.register_login_jwt.model.entity.Room;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-16T08:16:07+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDTO toRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDTO.RoomDTOBuilder roomDTO = RoomDTO.builder();

        roomDTO.id( room.getId() );
        roomDTO.name( room.getName() );
        roomDTO.type( room.getType() );
        roomDTO.floor( room.getFloor() );
        roomDTO.description( room.getDescription() );
        roomDTO.image( room.getImage() );
        roomDTO.createdAt( room.getCreatedAt() );
        roomDTO.createdBy( room.getCreatedBy() );
        roomDTO.organization( room.getOrganization() );
        roomDTO.organizationId( room.getOrganizationId() );
        roomDTO.organizationName( room.getOrganizationName() );
        roomDTO.createdByUsername( room.getCreatedByUsername() );
        roomDTO.usage( room.getUsage() );

        return roomDTO.build();
    }

    @Override
    public List<RoomDTO> toRoomDtos(List<Room> rooms) {
        if ( rooms == null ) {
            return null;
        }

        List<RoomDTO> list = new ArrayList<RoomDTO>( rooms.size() );
        for ( Room room : rooms ) {
            list.add( toRoomDto( room ) );
        }

        return list;
    }
}
