package com.ascodev.newshubbackend.service.impl;

import com.ascodev.newshubbackend.dto.UserDTO;
import com.ascodev.newshubbackend.entity.User;
import com.ascodev.newshubbackend.mapper.UserMapper;
import com.ascodev.newshubbackend.repository.UserRepository;
import com.ascodev.newshubbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = userMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);

        return userMapper.mapToUserDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        //TODO
        return List.of();
    }

    @Override
    public UserDTO getUserById(Long Id) {
        //TODO
        return null;
    }

    @Override
    public UserDTO updateUser(Long Id, UserDTO userUpdateRequest) {
        //TODO
        return null;
    }

    @Override
    public UserDTO banUser(Long Id) {
        //TODO
        return null;
    }

    @Override
    public void deleteUser(Long Id) {
        //TODO
    }

}
