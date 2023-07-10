package com.example.register_login_jwt.repository;

import com.example.register_login_jwt.config.UuidTypeHandler;
import com.example.register_login_jwt.model.entity.EmailVerification;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;
@Mapper
public interface EmailVerificationRepository {
    @Select("""
                insert into email_verification (user_id, code)
                values (#{user_id}, #{code})
            """)
    void insertEmailVerification(UUID user_id, String code);
    
    @Results( id = "emailVerificationMap",
            value = {
                    @Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "is_verified", property = "isVerified"),
                    @Result(column = "expire_at", property = "expireAt"),
                    @Result(column = "created_at", property = "createdAt"),
            })
    @Select("""
                select * from email_verification where code = #{code}
            """)
    EmailVerification getEmailVerificationByCode(@Param("code") String code);

    @Update("""
                update email_verification set is_verified = true where code = #{code}
            """)
    void updateIsVerified(String code);
}
