package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // jpa 를 사용할 때 데이터를 저장하거나 변경 할 때는 항상 transaction 을 해줘야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    // @Autowired -> 자동으로 Repository 에 대한 의존성 주입
    // MemberRepository 멤버변수를 매개변수로 받아 설정하는 생성자
    // 스프링컨테이너에 스프링 빈으로 등록되게 하는 이 객체의 생성자, 자동이던 수동이던, 생성자 주입 방식
    public MemberService (MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join (Member member) {
        double startTime = System.currentTimeMillis();

        try {
            // 같은 이름 회원 중복 가입 방지
            validateDuplicateMember(member);

            memberRepository.save(member);
            return member.getId();
        }
        finally {
            double endTime = System.currentTimeMillis();
            // 메서드 동작 소요시간 측정 로그, AOP 기술을 안쓰면 이렇게 메서드마다 코딩을 해줘야 한다.
            System.out.println("소요시간 : " + (endTime-startTime) + " ms");
        }
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
