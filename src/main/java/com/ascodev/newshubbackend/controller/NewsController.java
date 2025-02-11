package com.ascodev.newshubbackend.controller;

import com.ascodev.newshubbackend.dto.NewsDTO;
import com.ascodev.newshubbackend.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    private final NewsServiceImpl newsService;

    @GetMapping
    ResponseEntity<Iterable<NewsDTO>> getAllNews() {
        List<NewsDTO> newsDTOList = newsService.getAllNews();
        return ResponseEntity.status(HttpStatus.OK).body(newsDTOList);
    }

    @PostMapping
    ResponseEntity<NewsDTO> createNews(
            @RequestPart NewsDTO newsDTO,
            @RequestPart MultipartFile image) throws IOException {

        byte[] imageBytes = image.getBytes();
        newsDTO.setImage(imageBytes);

        NewsDTO createdNews = newsService.createNews(newsDTO);
        return ResponseEntity.ok(createdNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsDTO> deleteNews(@PathVariable long id) {
        newsService.deleteNews(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
