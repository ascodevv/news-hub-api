package com.ascodev.newshubbackend.service;

import com.ascodev.newshubbackend.dto.UserDTO;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long Id);

    UserDTO updateUser(Long Id, UserDTO userUpdateRequest);

    UserDTO banUser(Long Id);

    void deleteUser(Long Id);

}
