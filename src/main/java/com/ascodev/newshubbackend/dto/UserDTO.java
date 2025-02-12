package com.ascodev.newshubbackend.dto;
import com.ascodev.newshubbackend.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {

    Long id;

    String username;

    String password;

    String email;

    List<User.Role> role;

    List<User.UserStatus> status;

}
