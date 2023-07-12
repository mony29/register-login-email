package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.OrganizationDetailDTO;
import com.example.register_login_jwt.model.entity.OrganizationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationDetailMapper {
    OrganizationDetailMapper INSTANCE = Mappers.getMapper(OrganizationDetailMapper.class);

    OrganizationDetailDTO toOrganizationDetailDto(OrganizationDetail organizationDetail);
    List<OrganizationDetailDTO> toOrganizationDetailDtos(List<OrganizationDetail> organizationDetails);
}
