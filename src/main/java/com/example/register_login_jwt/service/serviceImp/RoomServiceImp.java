package com.example.register_login_jwt.service.serviceImp;

import com.example.register_login_jwt.exception.ForbiddenExceptionHandler;
import com.example.register_login_jwt.exception.NotFoundExceptionHandler;
import com.example.register_login_jwt.exception.UnauthorizedExceptionHandler;
import com.example.register_login_jwt.model.dto.RoomDTO;
import com.example.register_login_jwt.model.entity.Room;
import com.example.register_login_jwt.model.entity.UserApp;
import com.example.register_login_jwt.model.mapper.RoomMapper;
import com.example.register_login_jwt.model.request.RoomRequest;
import com.example.register_login_jwt.repository.OrganizationDetailRepository;
import com.example.register_login_jwt.repository.RoomRepository;
import com.example.register_login_jwt.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {
    private Room room = new Room();
    List<Room> rooms;
    private RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final OrganizationDetailRepository organizationDetailRepository;

    private UUID getCurrentUser() {

        Object getContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (getContext.equals("anonymousUser")) {
            throw new UnauthorizedExceptionHandler("Unauthorized");
        }

        UserApp user = (UserApp) getContext;
        return user.getId();
    }

    @Override
    public RoomDTO getRoomById(UUID roomId) {

        if(getCurrentUser()==null){
            throw new ForbiddenExceptionHandler("Access Denied, Please log in");
        }

        room = roomRepository.getRoomById(roomId);
        if (room == null) {
            throw new NotFoundExceptionHandler("Room not found");
        }

        return roomMapper.INSTANCE.toRoomDto(room);
    }

    @Override
    public UUID addRoom(RoomRequest roomRequest) {

        if(getCurrentUser()==null){
            throw new ForbiddenExceptionHandler("Access Denied, Please log in");
        }

        var organizationId = roomRequest.getOrganizationId();

        if(organizationId == null) {
            throw new NotFoundExceptionHandler("Organization not found");
        }

        String roleName = organizationDetailRepository.getExistRoleInOrganization(getCurrentUser(), organizationId);

        if (roleName == null || !roleName.equals("ADMIN")) {
            throw new ForbiddenExceptionHandler("Access Denied, Please check organization id");
        }

        return roomRepository.addRoom(roomRequest, getCurrentUser());

    }

    @Override
    public UUID updateRoom(RoomRequest roomRequest, UUID roomId) {

        if(getCurrentUser()==null){
            throw new ForbiddenExceptionHandler("Access Denied, Please log in");
        }

        var organizationId = roomRequest.getOrganizationId();

        if(organizationId == null) {
            throw new NotFoundExceptionHandler("Organization not found");
        }

        String roleName = organizationDetailRepository.getExistRoleInOrganization(getCurrentUser(), organizationId);

        if (roleName == null || !roleName.equals("ADMIN")) {
            throw new ForbiddenExceptionHandler("Access Denied, Please check organization id");
        }
        else if (roomRepository.getRoomById(roomId) == null) {
            throw new NotFoundExceptionHandler("Room not found");
        }

        return roomRepository.updateRoom(roomRequest, roomId, getCurrentUser());
    }

    @Override
    public UUID deleteRoom(UUID roomId) {

        if(getCurrentUser()==null){
            throw new ForbiddenExceptionHandler("Access Denied, Please log in");
        }
        else if(roomRepository.getRoomById(roomId) == null){
            throw new NotFoundExceptionHandler("Room not found");
        }

        return roomRepository.deleteRoom(roomId, getCurrentUser());
    }

    @Override
    public List<RoomDTO> getAllRooms(UUID orgId) {

        rooms = roomRepository.getAllRooms(orgId);

        if (getCurrentUser() == null){
            throw new ForbiddenExceptionHandler("Access Denied, Please login");
        }

        if(rooms.isEmpty()) {
            throw new NotFoundExceptionHandler("Room is empty");
        }

        return roomMapper.INSTANCE.toRoomDtos(rooms);
    }

}

