package com.ascodev.newshubbackend.service.impl;

import com.ascodev.newshubbackend.dto.CommentDTO;
import com.ascodev.newshubbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Override
    public List<CommentDTO> getCommentsForNews(Long newsId) {
        return List.of();
    }

    @Override
    public CommentDTO getComment(Long newsId, CommentDTO commentDTO) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }
}
