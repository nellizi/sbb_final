package com.ll.exam.sbbfinal.answer;

import com.ll.exam.sbbfinal.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;


    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
       question.addAnswer(answer);
        this.answerRepository.save(answer);
    }
}
