package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컴포넌트 스캔 기능이 발동하는 @Component 계열의 Annotation
@Controller
public class MemberController {

    private final MemberService memberService;

    // 스프링컨테이너에 스프링 빈으로 등록되게 하는 이 객체의 생성자, 자동이던 수동이던, 생성자 주입 방식
    @Autowired // 자동으로 Service 에 대한 의존성 주입
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

    // 모든 멤버의 리스트를 조회하기 위해 서비스 로직으로 멤버 리스트를 찾아서 memberList 에 템플릿 엔진을 이용해 입력 & 출력한다.
    @GetMapping("/members")
    public String list (Model model) {
        List<Member> members = memberService.findMembers();

        // 모델 객체를 사용하고 타임리프 템플릿 엔진을 통해 memberList 뷰에다가 member 객체들을 넣어준다.
        model.addAttribute("members", members);

        return "members/memberList";
    }
}