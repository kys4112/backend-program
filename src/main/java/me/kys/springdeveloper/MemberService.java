package me.kys.springdeveloper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // 멤버 테이블에 있는 모든 레코드들을 읽어서 반환
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // 멤버 저장
    public Member saveMember(Member member) {
        return memberRepository.save(member); //insert into member value(...);

    }
}
