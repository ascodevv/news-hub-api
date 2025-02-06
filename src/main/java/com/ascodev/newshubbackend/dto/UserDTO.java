package com.ascodev.newshubbackend.dto;
import com.ascodev.newshubbackend.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    User.Role role;

    User.UserStatus status;

}
