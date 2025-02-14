package com.ascodev.newshubbackend.controller;

import com.ascodev.newshubbackend.dto.NewsDTO;
import com.ascodev.newshubbackend.entity.User;
import com.ascodev.newshubbackend.repository.UserRepository;
import com.ascodev.newshubbackend.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    private final NewsServiceImpl newsService;
    private final UserRepository userRepository;

    @GetMapping
    ResponseEntity<Iterable<NewsDTO>> getAllNews() {
        List<NewsDTO> newsDTOList = newsService.getAllNews();
        return ResponseEntity.status(HttpStatus.OK).body(newsDTOList);
    }

    @PostMapping
    ResponseEntity<NewsDTO> createNews(
            @RequestPart NewsDTO newsDTO,
            @RequestPart MultipartFile image) throws IOException {
            Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userRepository.findByUsername(principal.getName());

        byte[] imageBytes = image.getBytes();
        newsDTO.setImage(imageBytes);
        newsDTO.setUserId(user);
        NewsDTO createdNews = newsService.createNews(newsDTO);
        return ResponseEntity.ok(createdNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NewsDTO> deleteNews(@PathVariable long id) {
        newsService.deleteNews(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
