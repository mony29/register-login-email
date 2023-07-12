package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.OrganizationDTO;
import com.example.register_login_jwt.model.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);
    OrganizationDTO toOrganizationDto (Organization organization);
    List<OrganizationDTO> toOrganizationDto (List<Organization> organizations);
}
