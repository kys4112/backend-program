package me.kys.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 필요한생성자(주석처리한거)를 자동으로 만들어준다.
public class ArticleUpdateRequest {
    private String title;
    private String content;

//    public ArticleUpdateRequest(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }
}
