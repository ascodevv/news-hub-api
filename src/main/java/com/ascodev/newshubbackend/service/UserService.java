package com.ascodev.newshubbackend.service;

import com.ascodev.newshubbackend.dto.UserDTO;
import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long Id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long Id, UserDTO userUpdateRequest);

    UserDTO banUser(Long Id);

    void deleteUser(Long Id);

}
