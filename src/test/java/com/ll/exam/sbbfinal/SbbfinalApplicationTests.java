package com.ll.exam.sbbfinal;


import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SbbfinalApplicationTests {

@Autowired
	private QuestionReporitory questionRepository;

	@Test
	void contextLoads() {
	}


	@Test
	void testJpa(){
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);  // 두번째 질문 저장

		assertThat(q1.getId()).isGreaterThan(0);
		assertThat(q2.getId()).isGreaterThan(q1.getId());
	}


//	@Test
//	void testJpa1(){
//
//	}

}
