package me.kys.springdeveloper;


import com.fasterxml.jackson.databind.ObjectMapper;
import me.kys.springdeveloper.dao.Article;
import me.kys.springdeveloper.dto.ArticleCreationRequest;
import me.kys.springdeveloper.dto.ArticleUpdateRequest;
import me.kys.springdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;

//    정리코드
    @AfterEach
    public void cleanUp() {
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle : 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        // 준비 given : 저장할 데이터를 준비, 요청 URL 준비
        String title = "JUnit 테스트를 통한 블로그 글 생성";
        String content = "수업 종료 10분전, 이 테스트 성공 후 점심 식사한다!!!";
        String url = "/api/articles";
        ArticleCreationRequest req = new ArticleCreationRequest(title, content);
        String articlesStr = objectMapper.writeValueAsString(req);

        // 실행 when
        ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(articlesStr));

        // 검증 then : DB에서 인출해보겠다.
        List<Article> articles = blogRepository.findAll();
        assertThat(articles.size()).isEqualTo(1);
        Article article = articles.get(0);
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContent()).isEqualTo(content);
    }

//    이건 DATA JPA가 아니기 때문에 저장되어있음. 그래서 오류가 나고, 위에 정리코드를 추가해줘야된다.
    @DisplayName("모든 블로그글 조회에 성공한다.")
    @Test
    public void getAllArticles() throws Exception {
        // 준비 given : url, 데이터
        String url = "/api/articles";
        String title = "11월 5일 두번째 시간";
        String content = "모든 블로그글 조회 테스트 코드 작성";
        blogRepository.save(Article.builder().content(content).title(title).build());

        // 실행 when
        ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // 검증 then : DB에서 인출해보겠다.
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));
    }
//    야자시간에 했던거
    @DisplayName("지정 블로그글 수정에 성공한다.")
    @Test
    public void updateArticle() throws Exception {
        // 준비 given : url, 기존 데이터, 수정할 데이터
        String url = "/api/articles/{id}";
        Article saved = blogRepository.save(Article.builder().title("야자시간").content("4시까지").build());
        String newTitle = "home";
        String newContent = "집에 가고 싶다";
        ArticleUpdateRequest request = new ArticleUpdateRequest(newTitle, newContent);
        // 실행 when
        ResultActions resultActions = mockMvc.perform(put(url, saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));
        // 검증 then
        resultActions.andExpect(status().isOk());
        Article article = blogRepository.findById(saved.getId()).orElseThrow();
        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }
//    과제: CRUD 기능에 대한 JUnit 테스트를 구현하라 (추가필요)
    @DisplayName("블로그글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception {
        // 준비 given

        // 실행 when

        // 검증 then

    }

}
