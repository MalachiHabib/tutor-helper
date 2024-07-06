package com.tutorhelper.service;

import com.tutorhelper.config.ErrorMessages;
import com.tutorhelper.dto.UserDTO;
import com.tutorhelper.entity.User;
import com.tutorhelper.mapper.UserMapper;
import com.tutorhelper.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long createUser(UserDTO userDto) {
        User user = userMapper.userDTOToUser(userDto);
        userRepository.save(user);
        return user.getId();
    }

    public UserDTO get(Long id) {
        return userRepository.findById(id)
            .map(userMapper::userToUserDTO)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format(ErrorMessages.USER_NOT_FOUND, id)
            ));
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = userRepository.findAll()
            .stream()
            .map(userMapper::userToUserDTO)
            .toList();
        return userDTOS;
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(ErrorMessages.USER_NOT_FOUND, id));
        }
        userRepository.deleteById(id);
    }

}
