package me.kys.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kys.springdeveloper.dao.Article;
import me.kys.springdeveloper.dto.ArticleCreationRequest;
import me.kys.springdeveloper.dto.ArticleResponse;
import me.kys.springdeveloper.dto.ArticleUpdateRequest;
import me.kys.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor  // final 필드만 모아서 생성자를 만듦
@RestController
public class BlogApiController {
    private final BlogService blogService;
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleCreationRequest articleCreationRequest) {
        // 서비스에게 블로그 글 등록을 의뢰.
//        blogService = new BlogService() XXXXXX 빈이라 이렇게 하지않음!!!
        Article saveArticle = blogService.save(articleCreationRequest);
        // 성공했다면 ResponseEntity 객체를 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(saveArticle);

    }
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        /*
        List<Article> articleList = blogService.findAll();
//        return ResponseEntity.ok().body(articleList); 이러면 안됨 타입이 안같음
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for (Article article : articleList) {
            articleResponseList.add(new ArticleResponse(article));
        }
        */
//        더 줄여서 쓰면 밑에처럼 된다.
//        stream()이 이해가 안된다면 test.java로 가셈
        List<ArticleResponse> articleResponseList = blogService.findAll().stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articleResponseList);
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> getById(@PathVariable Long id) {
        Article article = blogService.getById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateRequest req) {
        Article article = blogService.updateArticle(id, req);
        ArticleResponse articleResponse = new ArticleResponse(article);
        return ResponseEntity.ok().body(articleResponse);
    }
}
