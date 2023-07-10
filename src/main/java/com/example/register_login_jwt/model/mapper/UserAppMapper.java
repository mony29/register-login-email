package com.example.register_login_jwt.model.mapper;

import com.example.register_login_jwt.model.dto.UserAppDTO;
import com.example.register_login_jwt.model.entity.UserApp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAppMapper {
    UserAppMapper INSTANCE = Mappers.getMapper(UserAppMapper.class);

    UserAppDTO toUserAppDto(UserApp userApp);

    List<UserAppDTO> toUserAppDtos(List<UserApp> userApps);
}
