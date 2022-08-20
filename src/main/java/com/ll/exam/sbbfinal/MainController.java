package com.ll.exam.sbbfinal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private int i = 0;
    @RequestMapping("/sbb")
    // 아래 함수의 리턴값을 그대로 브라우저에 표시
    // 아래 함수의 리턴값을 문자열화 해서 브라우저 응답의 바디에 담는다.
    @ResponseBody
    public String index() {
        // 서버에서 출력
        System.out.println("Hello");
        // 먼 미래에 브라우저에서 보여짐
        return "안녕하세요.";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이" />
                    <input type="submit" value="page2로 POST 방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("plus")
    @ResponseBody
    public int plus(int a, int b){
    return a+b;
    }

    @GetMapping("/plus2")
    @ResponseBody
    public void showPlus2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));

        response.getWriter().append(a+b+"");
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(Integer dan, Integer limit){
        if(limit == null){
            limit = 9;
        }
        if(dan == null){
            dan = 9;
        }
        Integer finalDan=dan;
        return IntStream.rangeClosed(1,limit)
                .mapToObj(i->"%d*%d=%d".formatted(finalDan,i,finalDan*i))
                .collect(Collectors.joining("<br>"));
    }

    @GetMapping("/mbti/{name}")
    @ResponseBody
    public String showMbti(@PathVariable String name){
        String rs = switch (name){
            case "홍길동" -> "INFT";
            case "홍길순" -> "INFJ";
            case "임꺽정" -> "ENFJ";
            case "이지현","제제" -> "ENFP";
            default -> "CUTE";
        };
    return rs;
    }
//    return switch(name){
//        case "홍길동" -> "INFT";
//        case "홍길순" -> "INFJ";
//        case "임꺽정" -> "ENFJ";
//        case "이지현","제제" -> "ENFP";
//        default -> "CUTE";
//    };         이렇게 바로 리턴 뒤에 작성하는것도 가능
}