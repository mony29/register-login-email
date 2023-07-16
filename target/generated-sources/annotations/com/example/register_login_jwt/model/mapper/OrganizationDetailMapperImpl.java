package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.OrganizationDetailDTO;
import com.example.register_login_jwt.model.entity.OrganizationDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T14:24:33+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class OrganizationDetailMapperImpl implements OrganizationDetailMapper {

    @Override
    public OrganizationDetailDTO toOrganizationDetailDto(OrganizationDetail organizationDetail) {
        if ( organizationDetail == null ) {
            return null;
        }

        OrganizationDetailDTO organizationDetailDTO = new OrganizationDetailDTO();

        return organizationDetailDTO;
    }

    @Override
    public List<OrganizationDetailDTO> toOrganizationDetailDtos(List<OrganizationDetail> organizationDetails) {
        if ( organizationDetails == null ) {
            return null;
        }

        List<OrganizationDetailDTO> list = new ArrayList<OrganizationDetailDTO>( organizationDetails.size() );
        for ( OrganizationDetail organizationDetail : organizationDetails ) {
            list.add( toOrganizationDetailDto( organizationDetail ) );
        }

        return list;
    }
}
