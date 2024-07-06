package com.tutorhelper.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tutorhelper.dto.UserDTO;
import com.tutorhelper.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testUserToUserDTO() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        // When
        UserDTO userDTO = userMapper.userToUserDTO(user);

        // Then
        assertNotNull(userDTO);
        assertEquals("John Doe", userDTO.getName());
    }

    @Test
    void testUserDTOToUser() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");

        // When
        User user = userMapper.userDTOToUser(userDTO);

        // Then
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
    }
}
