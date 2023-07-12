package com.example.register_login_jwt.repository;

import com.example.register_login_jwt.config.UuidTypeHandler;
import com.example.register_login_jwt.model.entity.UserApp;
import com.example.register_login_jwt.model.request.UserAppRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;
@Mapper
public interface UserAppRepository {
    @Results( id = "userMap",
            value = {
                    @Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "is_enabled", property = "isEnabled"),
                    @Result(column = "is_account_non_locked", property = "isAccountNonLocked"),
                    @Result(column = "is_account_non_expired", property = "isAccountNonExpired"),
            })
    @Select("""
            select * from user_acc where email = #{userEmail}
            """)
    UserApp getUserByEmail(String userEmail);

    @Select("""
            select email from user_acc where email = #{userEmail}
            """)
    String getEmail(String userEmail);

    @Select("""
            insert into user_acc(name, email, password)
            values(#{req.name}, #{req.email}, #{req.password})
            returning *;
            """)
    UserApp insertUser(@Param("req") UserAppRequest userAppRequest);

    @Update("""
            update user_acc set is_enabled = true, is_account_non_locked = true, is_account_non_expired = true
            where id = #{userId}
            """)
    void updateUserSetting(UUID userId);

    @Select("""
                select * from user_acc where id = #{userId}
            """)
    @ResultMap("userMap")
    UserApp getUserById(@Param("userId") UUID userId);

}
