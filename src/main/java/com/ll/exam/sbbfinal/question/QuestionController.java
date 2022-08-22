package com.ll.exam.sbbfinal.question;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;



    @RequestMapping("/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    @RequestMapping("/detail/{id}")

    public String detail(Model model, @PathVariable int id) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question",question);

      return "question_detail";
    }
    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(String subject, String content) {
        questionService.create(subject,content);
        return "redirect:/question/list";
    }
}
