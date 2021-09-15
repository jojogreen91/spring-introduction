package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // JpaRepository<> 덕분에 MemberRepository 를 따로 등록해 줄 필요는 없지만 MemberService 등록시 필요하기 때문에 이렇게 선언
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Bean // 메서드 형태로 객체를 생성하여 스프링 컨테이너에 스프링 빈으로 등록한다.
//    public MemberRepository memberRepository () {
//        // return new MemoryMemberRepository();
//        // return new JdbcMemberRepository(dataSource);
//        // return new JdbcTemplateMemberRepository(dataSource);
//        // return new JpaMemberRepository(em);
//    }

    @Bean // 메서드 형태로 객체를 생성하여 스프링 컨테이너에 스프링 빈으로 등록한다.
    public MemberService memberService () {
        return new MemberService(memberRepository);
    }
}
