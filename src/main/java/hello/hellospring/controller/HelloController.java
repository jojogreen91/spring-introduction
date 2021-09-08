package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // http://localhost:8080/hello 로 request
    public String hello (Model model) {
        model.addAttribute("data", "Hello!!");

        // 타임리프 탬플릿 엔진을 설정 해놨기 때문에 resources/templates 의 hello.html 파일을 화면으로 출력한다.(response)
        return "Hello";
    }
}
