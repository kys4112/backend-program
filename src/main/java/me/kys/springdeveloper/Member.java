package me.kys.springdeveloper;

import jakarta.persistence.*; // 혹은 javax.persistence.* (스프링 부트 버전에 따라 다름)
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <-- 이 줄이 꼭 있어야 자동으로 ID가 1, 2, 3... 증가합니다!
    private Long id;

    private String name;
    private String email;
}