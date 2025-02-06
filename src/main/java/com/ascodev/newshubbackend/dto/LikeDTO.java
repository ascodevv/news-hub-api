package com.ascodev.newshubbackend.dto;

import com.ascodev.newshubbackend.entity.News;
import com.ascodev.newshubbackend.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeDTO {

    Long id;

    User user;

    News news;

}
