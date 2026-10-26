package me.kys.springdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kys.springdeveloper.dao.Article;
import me.kys.springdeveloper.dto.ArticleCreationRequest;
import me.kys.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(ArticleCreationRequest articleCreationRequest) {
        return blogRepository.save(articleCreationRequest.toEntity());
    }
}
