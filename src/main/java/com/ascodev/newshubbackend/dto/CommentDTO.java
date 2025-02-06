package com.ascodev.newshubbackend.dto;

import com.ascodev.newshubbackend.entity.News;
import com.ascodev.newshubbackend.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {

    Long id;

    String content;

    News news;

    @JsonProperty("created_by")
    User createdBy;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

}
