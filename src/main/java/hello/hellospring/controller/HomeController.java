package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈화면 만들기, 템플릿 엔진을 이용한 템플릿 view html 파일을 받기 위한 메서드의 리턴값의 타입은 String 이다.
    @GetMapping("/")
    public String home () {
        // 타임리프 템플릿 엔진에 의해 소스의 템플릿 폴더의 home.html 파일을 보내준다.
        return "home";
    }
}
