package com.ascodev.newshubbackend.service.impl;

import com.ascodev.newshubbackend.dto.UserDTO;
import com.ascodev.newshubbackend.entity.User;
import com.ascodev.newshubbackend.exception.ResourceNotFoundException;
import com.ascodev.newshubbackend.mapper.UserMapper;
import com.ascodev.newshubbackend.repository.UserRepository;
import com.ascodev.newshubbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::mapToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long Id) {
        User user = userRepository.findById(Id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format("User with id %s not found", Id))
                );
        return userMapper.mapToUserDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("User with this username already exists");
        }

        User user = userMapper.mapToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(List.of(User.Role.USER));
        user.setStatus(List.of(User.UserStatus.ACTIVE));

        User savedUser = userRepository.save(user);

        return userMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long Id, UserDTO userUpdateRequest) {
        User user = userRepository.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User with id %s not found", Id))
        );

        if (user == null) {
            return null;
        }

        if (userUpdateRequest.getUsername() != null) {
            user.setUsername(userUpdateRequest.getUsername());
        }
        if (userUpdateRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        }
        if (userUpdateRequest.getEmail() != null) {
            user.setEmail(userUpdateRequest.getEmail());
        }

        User userUpdateRequestObj = userRepository.save(user);

        return userMapper.mapToUserDTO(userUpdateRequestObj);
    }

    @Override
    public UserDTO banUser(Long Id) {
        User user = userRepository.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User with id %s not found", Id))
        );

        user.setStatus(List.of(User.UserStatus.BANNED));

        userRepository.save(user);

        return userMapper.mapToUserDTO(user);
    }

    @Override
    public void deleteUser(Long Id) {
        User user = userRepository.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("User with id %s not found", Id))
        );
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException(
            String.format("User with name %s not found", username)
        ));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.getRole());
    }


    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}