package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
//    정적 컨텐츠    
//    Spring이 data를 직접 받는 경우
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

//    외부에서 name을 받아오는 형태 (Parameter로 넘어온 것을 받는다.)
//    localhost:8080/hello-mvc?name=(원하는 입력값)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

// ResponseBody : HTTP에서 header와 body 중에 응답 body부에 이 내용을 직접 넣어준다는 의미 - String Converter
// View 없이 문자가(data) 그대로 내려간다 (html 파일을 사용하지 않는다.)
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

// api 방식 - 객체를 생성해서 넘겨준다.
// ResponseBody : Json 방식 - "key" : "value", HTML을 열고 닫을 필요가 없다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체로 넘겨준다 (default : Json 방식으로 data를 만들어서 반환한다. JsonConverter)
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name; //Java Bean, Property 접근 방식

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
