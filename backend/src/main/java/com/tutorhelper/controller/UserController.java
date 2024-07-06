package com.tutorhelper.controller;

import com.tutorhelper.dtos.UserDto;
import com.tutorhelper.entity.User;
import com.tutorhelper.repository.UserRepository;
import com.tutorhelper.response.PagedResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/user"})

public class UserController {
    private final UserRepository userRepository;

    @GetMapping({"/all"})
    public PagedResponse<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return new PagedResponse(users, users.size());
    }

    @PostMapping
    public Long createUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        this.userRepository.save(user);
        return user.getId();
    }

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
