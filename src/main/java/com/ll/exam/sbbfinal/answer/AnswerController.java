package com.ll.exam.sbbfinal.answer;


import com.ll.exam.sbbfinal.question.Question;
import com.ll.exam.sbbfinal.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;




    @PostMapping("/create/{id}")
    public String detail(Model model, @PathVariable("id") Integer id,  String content) {
        Question question = questionService.getQuestion(id);


      return "redirect:/question/detail/%s".formatted(id);
    }
}
