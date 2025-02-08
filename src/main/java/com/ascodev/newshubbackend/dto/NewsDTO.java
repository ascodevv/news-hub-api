package com.ascodev.newshubbackend.dto;

import com.ascodev.newshubbackend.entity.Comment;
import com.ascodev.newshubbackend.entity.Like;
import com.ascodev.newshubbackend.entity.News;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsDTO {

    Long id;

    @JsonProperty("created_by")
    Long userId;

    String title;

    byte[] image;

    String content;

    News.NewsStatus status;

    @JsonProperty("created_date")
    LocalDateTime createdDate;

    List<Comment> comments = new ArrayList<>();

    List<Like> likes = new ArrayList<>();

}