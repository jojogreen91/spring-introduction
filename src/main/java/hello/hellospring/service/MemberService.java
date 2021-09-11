package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // MemberRepository 멤버변수를 매개변수로 받아 설정하는 생성자
    //@Autowired // Repository 에 대한 의존성 주입
    // 스프링컨테이너에 스프링 빈으로 등록되게 하는 이 객체의 생성자, 자동이던 수동이던, 생성자 주입 방식
    public MemberService (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join (Member member) {
        // 같은 이름 회원 중복 가입 방지
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
   private void validateDuplicateMember (Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다.");}
            );
    }

    // 전체 회원 조회
    public List<Member> findMembers () {
        return memberRepository.findAll();
    }

    // 부여된 아이디로 회원 찾기
    public Optional<Member> findOne (Long memberId) {
        return memberRepository.findById(memberId);
    }
}