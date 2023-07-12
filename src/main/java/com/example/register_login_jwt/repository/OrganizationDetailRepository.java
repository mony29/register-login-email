package com.example.register_login_jwt.repository;

import com.example.register_login_jwt.model.entity.OrganizationDetail;
import com.example.register_login_jwt.model.request.OrganizationDetailRequest;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface OrganizationDetailRepository {
    @Results(
            id = "organizationDetailMapper",
            value = {
                    @Result(property = "userId", column = "user_id"),
                    @Result(property = "organizationID", column = "organization_id"),
                    @Result(property = "isActive", column = "is_active"),
                    @Result(property = "createdAt", column = "created_at"),
                    @Result(property = "createdBy", column = "created_by"),
                    @Result(property = "organization", column = "organization_id",
                            one = @One(select = "com.kshrd.asset_tracer_api.repository.OrganizationRepository.getOrganizationById")),
                    @Result(property = "role", column = "role_id",
                            one = @One(select = "com.kshrd.asset_tracer_api.repository.RoleRepository.getRoleById"))})
    @Select("""
            insert into organization_detail(user_id, organization_id, role_id)
            values(#{userId}, #{organizationId}, (select id from role where name = 'ADMIN'))
            returning *
            """)
    OrganizationDetail userAddNewOrganizationDetail(UUID userId, UUID organizationId);

    @Select("""
            insert into organization(name, code, address, logo)
            values (#{req.organizationName}, #{code}, #{req.organizationAddress}, #{req.organizationLogo})
            returning id
            """)
    UUID addOrganization(@Param("req") OrganizationDetailRequest organizationDetailRequest, String code);

    @Select("""
            select r.name from organization_detail od
            inner join role r
            on r.id = od.role_id
            where od.user_id = #{userId}
            and od.organization_id = #{orgId}
            and od.deleted_at is null
            """)
    String getExistRoleInOrganization(UUID userId, UUID orgId);
}
