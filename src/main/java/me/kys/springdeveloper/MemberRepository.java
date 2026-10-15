package me.kys.springdeveloper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // findAll() : select * from member;
    // findById() : select * from member where id = ?
    // deleteAll() : delete from member;
    //deleteById() : delete from member where id = ?
    // save() : insert into member(id, name) values (?, ?), update member set ... where id = ?

    // 예시: 이런걸 실행하고싶으면?
    // select * from member where name = ?
    // 답: findByName() : select * from member where name = ?


    // 이 2개쿼리를 작성하려면? 메소드를 만들어야됨
    // select * from member where email = ?
    // findByEmail() : select * from member where email = ?
    //public Optional<Member> findByEmail(String email);

    // select * from member where name = ? and email = ?
    // findByNameAndEmail() : select * from member where name = ? and email = ?
    // public Optional<Member> findByNameAndEmail(String name, String email);

}
