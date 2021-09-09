package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    // 각각 테스트 메서드를 실행하기전에 실행되어서 각 테스트 케이스를 실행 할 때 새로운 memberService 인스턴스를 생성해서 테스트 한다.
    @BeforeEach
    public void beforeEach () {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    // 테스트 메서드 종료 후 memoryMemberRepository 초기화
    @AfterEach
    public void afterEach () {
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입 () {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외 () {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // 예외가 제대로 발생하는지 확인하는 assertThrows 메서드
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*
        // when
        memberService.join(member1);
        try {
            memberService.join(member2);
        }
        // then
        catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
 */
    }

    @Test
    void findMembers () {
    }

    @Test
    void findOne () {
    }
}