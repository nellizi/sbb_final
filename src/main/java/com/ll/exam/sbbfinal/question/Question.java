package com.ll.exam.sbbfinal.question;

import com.ll.exam.sbbfinal.answer.Answer;
import com.ll.exam.sbbfinal.user.SiteUser;
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
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question" , cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) //mappesBy : answer엔티티에 쓰인 변수명
    private List<Answer> answerList = new ArrayList<>();  //클래스에만 존재하는 것

    @ManyToOne
    private SiteUser author;

    public void addAnswer(Answer answer) {

        answer.setQuestion(this);
        getAnswerList().add(answer);
    }
}