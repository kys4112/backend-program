package me.kys.springdeveloper;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    private String name;
    private String email;

    // ai가 추가한것
    public Member(String name) {
        this.name = name;
    }
}