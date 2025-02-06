package com.ascodev.newshubbackend.mapper;

import com.ascodev.newshubbackend.dto.UserDTO;
import com.ascodev.newshubbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    User mapToUser(UserDTO userDTO);

    @Mapping(source = "id", target = "id")
    UserDTO mapToUserDTO(User user);
}
