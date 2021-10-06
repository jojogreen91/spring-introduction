package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // http://localhost:8080/hello 로 request
    public String hello (Model model) {
        model.addAttribute("data", "Hello!!");

        // 타임리프 탬플릿 엔진을 설정 해놨기 때문에 resources/templates 의 hello.html 파일을 화면으로 출력한다.(response)
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc (@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);

        return "hello-templates"; // 타임리프 엔진을 이용해서 page view 자체를 결과로 보낸다.
    }

    @GetMapping("hello-string")
    @ResponseBody // API 방식, http 의 body 부분에 return 값을 그대로 보낸다.
    public String helloString (@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // API 방식, http 의 body 부분에 return 값을 그대로 보낸다.
    public Hello helloApi (@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
