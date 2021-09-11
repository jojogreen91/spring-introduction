package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 스프링컨테이너에 스프링 빈으로 등록되게 하는 이 객체의 생성자, 자동이던 수동이던, 생성자 주입 방식
    @Autowired // Service 에 대한 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
