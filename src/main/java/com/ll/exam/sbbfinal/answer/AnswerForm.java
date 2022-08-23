package com.ll.exam.sbbfinal.answer;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
public class AnswerForm {
@NotEmpty(message = "내용은 필수 입력 항목입니다.")
private String content;
}
