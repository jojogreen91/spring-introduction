package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // 메서드 형태로 객체를 생성하여 스프링 컨테이너에 스프링 빈으로 등록한다.
    public MemberRepository memberRepository () {
        return new MemoryMemberRepository();
    }

    @Bean // 메서드 형태로 객체를 생성하여 스프링 컨테이너에 스프링 빈으로 등록한다.
    public MemberService memberService () {
        return new MemberService(memberRepository());
    }
}
