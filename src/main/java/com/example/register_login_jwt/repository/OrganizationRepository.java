package com.example.register_login_jwt.repository;

import com.example.register_login_jwt.config.UuidTypeHandler;
import com.example.register_login_jwt.model.entity.Organization;
import com.example.register_login_jwt.model.request.OrganizationRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface OrganizationRepository {
    @Results(id = "organizationMapper",
            value = {
                    @Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, typeHandler = UuidTypeHandler.class),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "created_by", property = "createdBy"),
                    @Result(column = "updated_at", property = "updatedAt"),
                    @Result(column = "updated_by", property = "updatedBy"),
                    @Result(column = "deleted_at", property = "deletedAt"),
                    @Result(column = "deleted_by", property = "deletedBy"),
                    @Result(column = "role_name", property = "roleName"),
                    @Result(column = "is_active", property = "isActive"),
                    @Result(column = "is_member", property = "isMember"),
                    @Result(column = "is_active", property = "isActive"),
                    @Result(column = "total_user", property = "totalUser"),
//                    @Result(column = "id", property = "users",
//                            many = @Many(select = "com.kshrd.asset_tracer_api.repository.UserAppRepository.getAllUsersByOrganizationId")
//                    ),
            })
    @Select("""
            select id from organization
            where code = #{code} and deleted_at is null
            """)
    Organization getOrganizationByCode(String code);

    @Select("""
            insert into organization(name, code, address, logo, created_by)
            values(#{req.name}, #{code}, #{req.address}, #{req.logo}, #{currentUser})
            returning id
            """)
    UUID createOrganization(@Param("req") OrganizationRequest organizationRequest, String code, UUID currentUser);


    @Select("""
            insert into organization_detail(user_id, organization_id, is_active, role_id, created_by, status)
            values(#{userId}, #{organizationId}, true, (select id from role where name = 'ADMIN'), #{getCurrentUserId}, 'is_owner')
            returning organization_id
            """)
    UUID addOrganizationDetail(UUID userId, UUID organizationId, UUID getCurrentUserId);
    @ResultMap("organizationMapper")
    @Select("""
            select id, name, code, address, logo, created_at
            from organization
            where id = #{organizationId} and deleted_at is null
            """)
    Organization getOrganizationById(UUID organizationId);
}
