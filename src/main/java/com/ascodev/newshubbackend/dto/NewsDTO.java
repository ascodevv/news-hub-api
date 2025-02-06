package com.ascodev.newshubbackend.dto;

import com.ascodev.newshubbackend.entity.Comment;
import com.ascodev.newshubbackend.entity.Like;
import com.ascodev.newshubbackend.entity.News;
import com.ascodev.newshubbackend.entity.User;
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

    @NonNull
    Long id;

    @NonNull
    User createdBy;

    @NonNull
    String title;

    @NonNull
    String content;

    @NonNull
    News.NewsStatus status;

    @JsonProperty("created_date")
    @NonNull
    LocalDateTime createdDate;

    @NonNull
    List<Comment> comments = new ArrayList<>();

    @NonNull
    List<Like> likes = new ArrayList<>();

}