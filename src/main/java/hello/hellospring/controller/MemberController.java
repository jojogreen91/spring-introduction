package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 스프링컨테이너에 스프링 빈으로 등록되게 하는 이 객체의 생성자, 자동이던 수동이던, 생성자 주입 방식
    @Autowired // Service 에 대한 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 새로운 멤버 정보를 받기 위해서 createMembersForm 화면을 요청 & 출력
    @GetMapping("/members/new")
    public String createForm () {
        return "members/createMembersForm";
    }

    // createMembersForm 화면에서 입력된 정보가, 매개변수로 받는 MemberForm 객체의 매핑된 필드에 대입 & 생성(post) 돠고
    // 생성된 memberForm 이 매개변수로 들어오고 create 메서드가 실행됨.
    @PostMapping("/members/new")
    public String create (MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        // 등록 종료 후 다시 home 화면으로 복귀
        return "redirect:/";
    }
}
