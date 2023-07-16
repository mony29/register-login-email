package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.UserAppDTO;
import com.example.register_login_jwt.model.entity.UserApp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T14:56:39+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class UserAppMapperImpl implements UserAppMapper {

    @Override
    public UserAppDTO toUserAppDto(UserApp userApp) {
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

    @Override
    public List<UserAppDTO> toUserAppDtos(List<UserApp> list) {
        if ( list == null ) {
            return null;
        }

        List<UserAppDTO> list1 = new ArrayList<UserAppDTO>( list.size() );
        for ( UserApp userApp : list ) {
            list1.add( toUserAppDto( userApp ) );
        }

        return list1;
    }
}
