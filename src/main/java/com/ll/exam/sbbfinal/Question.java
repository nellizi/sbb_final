package com.ll.exam.sbbfinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question" , cascade = CascadeType.REMOVE) //mappesBy : answer엔티티에 쓰인 변수명
    private List<Answer> answerList = new ArrayList<>();  //클래스에만 존재하는 것

    public void addAnswer(Answer answer) {

        answer.setQuestion(this);
        getAnswerList().add(answer);
    }
}