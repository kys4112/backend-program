package me.kys.springdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    @ResponseBody //이렇게 ResponseBody를 붙이면 @RestController(@Controller+@ResponseBody)랑 같은 의미가 됨
    public String thymeleaf() {
        return "example"; // 뷰 이름이 아니라 데이터로 처리됨 → 타임리프를 거치지 않고 브라우저에 글자 "example"이 그대로 출력됨
    }

    @GetMapping("/thymeleaf/example2")
    public String thymeleaf2(Model model) {
        Person person = new Person();
        person.setName("홍길동");
        person.setAge(24);
        model.addAttribute("person", person);
        return "example"; // 타임리프가 resources/templates/example.html을 찾아 렌더링
    }
}

@Getter
@Setter
class Person {
    private String name;
    private int age;
}
