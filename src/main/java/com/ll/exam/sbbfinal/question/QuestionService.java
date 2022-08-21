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
        Optional<Question> oq = questionRepository.findById(id);

        if(oq.isPresent()){
            return oq.get();
        }
        throw new DataNotFoundExceprion("question not found");

        //    return oq.orElseThrow(DataNotFoundExceprion);
   }
}
