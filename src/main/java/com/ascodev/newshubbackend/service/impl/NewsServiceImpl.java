package com.ascodev.newshubbackend.service.impl;

import com.ascodev.newshubbackend.dto.NewsDTO;
import com.ascodev.newshubbackend.entity.News;
import com.ascodev.newshubbackend.entity.User;
import com.ascodev.newshubbackend.exception.ResourceNotFoundException;
import com.ascodev.newshubbackend.mapper.NewsMapper;
import com.ascodev.newshubbackend.repository.NewsRepository;
import com.ascodev.newshubbackend.repository.UserRepository;
import com.ascodev.newshubbackend.service.CommentService;
import com.ascodev.newshubbackend.service.LikeService;
import com.ascodev.newshubbackend.service.NewsService;
import com.ascodev.newshubbackend.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final UserRepository userRepository;

    @Override
    public List<NewsDTO> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream().map(newsMapper::mapToNewsDTO).collect(Collectors.toList());
    }

    @Override
    public NewsDTO getNewsByTitle(String title) {
        News news = newsRepository.findByTitle(title);

        return newsMapper.mapToNewsDTO(news);
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = newsMapper.mapToNews(newsDTO);

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByUsername(username);

        news.setImage(newsDTO.getImage());
        news.setStatus(News.NewsStatus.APPROVED);
        news.setCreatedDate(LocalDateTime.now());
        news.setCreatedBy(user);

        news = newsRepository.save(news);

        return newsMapper.mapToNewsDTO(news);
    }

    @Override
    public NewsDTO updateNews(Long Id, NewsDTO newsUpdateRequest) {
        News news = newsRepository.findById(Id)
            .orElseThrow(() -> new ResourceNotFoundException("News not found"));

        if (newsUpdateRequest.getTitle() != null) {
            news.setTitle(newsUpdateRequest.getTitle());
        }
        if (newsUpdateRequest.getContent() != null) {
            news.setContent(newsUpdateRequest.getContent());
        }
        if (newsUpdateRequest.getImage() != null) {
            news.setImage(newsUpdateRequest.getImage());
        }

        News updatedNews = newsRepository.save(news);

        return newsMapper.mapToNewsDTO(updatedNews);
    }

    @Override
    public void deleteNews(Long Id) {
        newsRepository.deleteById(Id);
    }

}