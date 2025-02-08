package com.ascodev.newshubbackend.service.impl;

import com.ascodev.newshubbackend.dto.LikeDTO;
import com.ascodev.newshubbackend.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    @Override
    public List<LikeDTO> getLikesForNews(Long newsId) {
        return List.of();
    }

    @Override
    public LikeDTO addLike(Long newsId, LikeDTO likeDTO) {
        return null;
    }

    @Override
    public void deleteLike(Long likeId) {

    }

}
