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

    @NonNull
    Long id;

    @NonNull
    String username;

    @NonNull
    String password;

    @NonNull
    String email;

    @NonNull
    User.Role role;

    @NonNull
    User.UserStatus status;

}
