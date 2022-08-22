package com.ll.exam.sbbfinal.question;

import com.ll.exam.sbbfinal.DataNotFoundExceprion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

   public List<Question> getList(){
       return this.questionRepository.findAll();
   }


    public Question getQuestion(int id) {
     return questionRepository.findById(id)
             .orElseThrow(() -> new DataNotFoundExceprion("no %d question".formatted(id)));
   }
}
