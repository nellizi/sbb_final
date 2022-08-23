package com.ll.exam.sbbfinal.answer;


import com.ll.exam.sbbfinal.question.Question;
import com.ll.exam.sbbfinal.question.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;





    @PostMapping("/create/{id}")
    public String detail(Model model, @PathVariable long  id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = questionService.getQuestion(id);

        if(bindingResult.hasErrors()){
           model.addAttribute("question",question);
           return "question_detail";
       }

        answerService.create(question, answerForm.getContent());

      return "redirect:/question/detail/%s".formatted(id);
    }
}
