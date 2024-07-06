package com.tutorhelper.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tutorhelper.dto.UserDTO;
import com.tutorhelper.entity.User;
import com.tutorhelper.mapper.UserMapper;
import com.tutorhelper.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");

        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userMapper.userDTOToUser(any(UserDTO.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        Long userId = userService.createUser(userDTO);

        // Then
        assertThat(userId).isEqualTo(1L);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        // Given
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setName("John Doe");

        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(userMapper.userToUserDTO(user1)).thenReturn(userDTO1);

        // When
        Optional<UserDTO> user = userService.getById(user1.getId());

        // Then
        assertThat(user).isPresent();
        assertThat(user.get()).isEqualTo(userDTO1);
    }

    @Test
    void testGetUserByIdNotFound() {
        // Given
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When
        Optional<UserDTO> user = userService.getById(userId);

        // Then
        assertThat(user).isEmpty();
        verify(userRepository).findById(userId);
    }

    @Test
    void testGetAllUsers() {
        // Given
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Jane Doe");

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setName("John Doe");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setName("Jane Doe");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.userToUserDTO(user1)).thenReturn(userDTO1);
        when(userMapper.userToUserDTO(user2)).thenReturn(userDTO2);

        // When
        List<UserDTO> userDTOs = userService.getAllUsers();

        // Then
        assertThat(userDTOs).hasSize(2);
        assertThat(userDTOs.get(0).getName()).isEqualTo("John Doe");
        assertThat(userDTOs.get(1).getName()).isEqualTo("Jane Doe");
    }
}
