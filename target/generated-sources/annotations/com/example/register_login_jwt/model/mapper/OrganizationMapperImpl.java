package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.OrganizationDTO;
import com.example.register_login_jwt.model.dto.UserAppDTO;
import com.example.register_login_jwt.model.entity.Organization;
import com.example.register_login_jwt.model.entity.UserApp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T14:51:49+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDTO toOrganizationDto(Organization organization) {
        if ( organization == null ) {
            return null;
        }

        OrganizationDTO.OrganizationDTOBuilder organizationDTO = OrganizationDTO.builder();

        organizationDTO.id( organization.getId() );
        organizationDTO.name( organization.getName() );
        organizationDTO.code( organization.getCode() );
        organizationDTO.address( organization.getAddress() );
        organizationDTO.logo( organization.getLogo() );
        organizationDTO.roleName( organization.getRoleName() );
        organizationDTO.isActive( organization.getIsActive() );
        organizationDTO.isMember( organization.getIsMember() );
        organizationDTO.status( organization.getStatus() );
        organizationDTO.createdAt( organization.getCreatedAt() );
        organizationDTO.role( organization.getRole() );
        organizationDTO.users( userAppListToUserAppDTOList( organization.getUsers() ) );
        organizationDTO.userName( organization.getUserName() );
        organizationDTO.userImage( organization.getUserImage() );
        organizationDTO.totalUser( organization.getTotalUser() );

        return organizationDTO.build();
    }

    @Override
    public List<OrganizationDTO> toOrganizationDto(List<Organization> organizations) {
        if ( organizations == null ) {
            return null;
        }

        List<OrganizationDTO> list = new ArrayList<OrganizationDTO>( organizations.size() );
        for ( Organization organization : organizations ) {
            list.add( toOrganizationDto( organization ) );
        }

        return list;
    }

    protected UserAppDTO userAppToUserAppDTO(UserApp userApp) {
        if ( userApp == null ) {
            return null;
        }

        UserAppDTO.UserAppDTOBuilder userAppDTO = UserAppDTO.builder();

        userAppDTO.id( userApp.getId() );
        userAppDTO.email( userApp.getEmail() );
        userAppDTO.name( userApp.getName() );
        userAppDTO.gender( userApp.getGender() );
        userAppDTO.phone( userApp.getPhone() );
        userAppDTO.address( userApp.getAddress() );
        userAppDTO.isEnabled( userApp.getIsEnabled() );
        userAppDTO.isAccountNonLocked( userApp.getIsAccountNonLocked() );
        userAppDTO.isAccountNonExpired( userApp.getIsAccountNonExpired() );
        userAppDTO.createdAt( userApp.getCreatedAt() );

        return userAppDTO.build();
    }

    protected List<UserAppDTO> userAppListToUserAppDTOList(List<UserApp> list) {
        if ( list == null ) {
            return null;
        }

        List<UserAppDTO> list1 = new ArrayList<UserAppDTO>( list.size() );
        for ( UserApp userApp : list ) {
            list1.add( userAppToUserAppDTO( userApp ) );
        }

        return list1;
    }
}
