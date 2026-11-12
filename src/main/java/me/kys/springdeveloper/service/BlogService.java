package me.kys.springdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.kys.springdeveloper.dao.Article;
import me.kys.springdeveloper.dto.ArticleCreationRequest;
import me.kys.springdeveloper.dto.ArticleUpdateRequest;
import me.kys.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(ArticleCreationRequest articleCreationRequest) {
        return blogRepository.save(articleCreationRequest.toEntity());
    }
    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    public Article getById(Long id) {
        return blogRepository.findById(id).get();
    }
    public Article deleteById(Long id) {
        blogRepository.deleteById(id);
        return null;
    }
//    public Article updateArticle(Long id, ArticleUpdateRequest req) {
//        Article article = Article.builder().title(req.getTitle()).content(req.getContent()).build();
//    }
    @Transactional
    public Article updateArticle(Long id, ArticleUpdateRequest req) {
        Article article = blogRepository.findById(id).get();
        article.updateArticle(req.getTitle(), req.getContent());
        return article;
    }


}
