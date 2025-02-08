package com.ascodev.newshubbackend.service;

import com.ascodev.newshubbackend.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List <CommentDTO> getCommentsForNews(Long newsId);

    CommentDTO getComment(Long newsId, CommentDTO commentDTO);

    void deleteComment(Long commentId);

}
