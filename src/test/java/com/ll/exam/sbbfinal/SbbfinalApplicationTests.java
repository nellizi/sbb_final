package com.ll.exam.sbbfinal;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ll.exam.sbbfinal.question.Question;
import com.ll.exam.sbbfinal.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2);

		assertThat(q1.getId()).isGreaterThan(0);
		assertThat(q2.getId()).isGreaterThan(q1.getId());
	}

	@Test
	void testJpa2() {
		// SELECT * FROM question
		List<Question> all = questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	void testJpa3() {
		// SELECT * FROM question
		Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	@Test
	void testJpa4() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());
	}

	@Test
	void testJpa5() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
	@Test
	void testJpa6() {
		Optional<Question> oq = this.questionRepository.findById(1L);  //findById가 옵셔널로 설정되어 있음. 싫으면 오버라이딩 고
		assertTrue(oq.isPresent());
		Question q = oq.get();  //옵셔널로 들어오면 get으로 받기. 이게 싫으면 orElse(null) 로 받기
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);  //update
	}


}