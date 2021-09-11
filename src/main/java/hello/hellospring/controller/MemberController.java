package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // Service 에 대한 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
