package com.ascodev.newshubbackend.service;

import com.ascodev.newshubbackend.dto.LikeDTO;
import com.ascodev.newshubbackend.entity.Like;

import java.util.List;

public interface LikeService {

    List<LikeDTO> getLikesForNews(Long newsId);

    LikeDTO addLike(Long newsId, LikeDTO likeDTO);

    void deleteLike(Long likeId);

}
