package com.example.register_login_jwt.repository;

import com.example.register_login_jwt.config.UuidTypeHandler;
import com.example.register_login_jwt.model.entity.Room;
import com.example.register_login_jwt.model.request.RoomRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;
@Mapper
public interface RoomRepository {
    @Results(id = "roomMapper",
            value = {
                    @Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "created_by", property = "createdBy"),
                    @Result(column = "updated_at", property = "updatedAt"),
                    @Result(column = "updated_by", property = "updatedBy"),
                    @Result(column = "deleted_at", property = "deletedAt"),
                    @Result(column = "deleted_by", property = "deletedBy"),
                    @Result(column = "organization_id", property = "organizationId"),
                    @Result(column = "created_by_username", property = "createdByUsername"),
                    @Result(column = "organization_name", property = "organizationName")
            })

    @Select("""
            select r.*, ua.name created_by_username
            from room r
            inner join user_acc ua on ua.id = r.created_by
            where r.id = #{id} and r.deleted_at is null
            """)
//    @ResultMap("roomMapper")
    Room getRoomById(@Param("id") UUID roomId);

    @Select("""
            insert into room(name, type, floor, description, image, organization_id, created_by)
            values(#{req.name}, #{req.type}, #{req.floor},
            #{req.description}, #{req.image}, #{req.organizationId}, #{getCurrentUser})
            returning id
            """)
    UUID addRoom(@Param("req") RoomRequest roomRequest, UUID getCurrentUser);

    @Select("""
            update room set name = #{req.name}, type = #{req.type}, floor = #{req.floor},
            description = #{req.description}, image = #{req.image}, 
            updated_at = CURRENT_TIMESTAMP, updated_by = #{getCurrentUserId}
            where id = #{roomId} returning id
            """)
    UUID updateRoom(@Param("req") RoomRequest roomRequest, UUID roomId, UUID getCurrentUserId);

    @Select("""
            update room
            set deleted_at = CURRENT_TIMESTAMP, deleted_by = #{getCurrentUserId}
            where id = #{id} returning id;
            """)
    UUID deleteRoom(@Param("id") UUID roomId, UUID getCurrentUserId);


    @Select("""
            select r.*, ua.name created_by_username
            from room r
            inner join user_acc ua on ua.id = r.created_by
            where r.deleted_at is null
            and r.organization_id = #{orgId}
            """)
    @RequestMapping("roomMapper")
    List<Room> getAllRooms(UUID orgId);
}
