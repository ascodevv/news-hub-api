package com.ascodev.newshubbackend.service;

import com.ascodev.newshubbackend.dto.NewsDTO;

import java.util.List;

public interface NewsService {

    List<NewsDTO> getAllNews();

    NewsDTO getNewsByTitle(String title);

    NewsDTO createNews(NewsDTO newsDTO);

    NewsDTO updateNews(Long Id, NewsDTO newsUpdateRequest);

    void deleteNews(Long Id);

}
