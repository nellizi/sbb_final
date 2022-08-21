package com.ll.exam.sbbfinal;

import com.sun.xml.bind.v2.schemagen.xmlschema.NestedParticle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.convert.ThreeTenBackPortConverters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private int i = 0;


    @RequestMapping("/")
    public String root() {
        return "redirect:/question/list";
    }



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
    public int plus(int a, int b) {
        return a + b;
    }

    @GetMapping("/plus2")
    @ResponseBody
    public void showPlus2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));

        response.getWriter().append(a + b + "");
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(Integer dan, Integer limit) {
        if (limit == null) {
            limit = 9;
        }
        if (dan == null) {
            dan = 9;
        }
        Integer finalDan = dan;
        return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> "%d*%d=%d".formatted(finalDan, i, finalDan * i))
                .collect(Collectors.joining("<br>"));
    }

    @GetMapping("/mbti/{name}")
    @ResponseBody
    public String showMbti(@PathVariable String name) {
        String rs = switch (name) {
            case "홍길동" -> "INFT";
            case "홍길순" -> "INFJ";
            case "임꺽정" -> "ENFJ";
            case "이지현", "제제" -> "ENFP";
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

    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession(@PathVariable String name, @PathVariable String value, HttpServletRequest req) {
        HttpSession session = req.getSession(); //req 안에 쿠키가 있다 => req에서 세션 얻기 가능 . 요청 올 때 쿠키가 들어온다. (JSESSIONID)
        session.setAttribute(name, value);
        return "세션변수 %s의 값이 %s로 설정되었습니다.".formatted(name, value);

    }

    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String getSession(@PathVariable String name, HttpSession session) {
        String value = (String) session.getAttribute(name);
        return "세션변수 %s의 값이 %s 입니다.".formatted(name, value);
    }

    private List<Article>articles = new ArrayList<>();
    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {
        int id = 1;
        Article article = new Article( title, body);
        articles.add(article);

        return "%d번 글이 생성되었습니다.".formatted(article.getId());
    }
    @GetMapping("/getArticle/{id}")
    @ResponseBody
    public Article getArticle(@PathVariable int id) {
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        return article;
    }
    @GetMapping("/modifyArticle/{id}")
    @ResponseBody
    public String getArticle(@PathVariable int id, String title, String body) {
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null){
            return "%d번 article은 존재하지 않습니다.".formatted(id);
        }
        article.setTitle(title);
        article.setBody(body);

        return "%d번 article을 수정하였습니다.".formatted(id);
    }
    @GetMapping("/deleteArticle/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id) {
        Article article = articles
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (article == null){
            return "%d번 article은 존재하지 않습니다.".formatted(id);
        }
        articles.remove(article);

        return "%d번 article을 삭제하였습니다.".formatted(id);
    }
    @GetMapping("/addPerson")
    @ResponseBody
    public Person addperson(int id, int age, String name) {
        Person person = new Person(id,age,name);
        return person;

    }
    @GetMapping("/addPerson2")
    @ResponseBody
    public Person addpersonNew(Person person)
    {

        return person;

    }
}


@Getter
@Setter
@AllArgsConstructor
 class Article {
    private static int LastId = 0;
    private int id;
    private String title;
    private String body;

    public Article(String title, String body) {
        this(++LastId, title, body);
    }

}
@AllArgsConstructor
@Getter
class Person{
    private int id;
    private int age;
    private String name;

}