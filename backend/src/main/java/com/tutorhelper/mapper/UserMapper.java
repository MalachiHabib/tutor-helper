package com.tutorhelper.mapper;

import com.tutorhelper.dto.UserDTO;
import com.tutorhelper.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

}