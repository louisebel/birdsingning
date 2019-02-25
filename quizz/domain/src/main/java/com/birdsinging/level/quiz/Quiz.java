package com.birdsinging.level.quiz;

import com.birdsinging.exception.ErrorMessage;

import java.util.Objects;
import java.util.Optional;

/*
DDD
Value Object
 */
public class Quiz {
	private String question;
	private Choice a;
	private Choice b;
	private Choice c;
	private Choice answer;

	public Quiz(String question, Choice a, Choice b, Choice c, Choice answer) {
		this.question = question;
		this.a = a;
		this.b = b;
		this.c = c;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public Choice getA() {
		return a;
	}

	public Choice getB() {
		return b;
	}

	public Choice getC() {
		return c;
	}

	public Choice getAnswer() {
		return answer;
	}

	public Optional<String>  verify(){
		if(answerIsNotEqualToAnyChoice()){
			return Optional.of(ErrorMessage.ANSWER_SHOULD_BE_EQUALS_TO_A_CHOICE);
		}
		return Optional.empty();
	}

	private boolean answerIsNotEqualToAnyChoice() {
		return !(answer.equals(a) || answer.equals(b) || answer.equals(c));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Quiz quiz = (Quiz) o;
		return question.equals(quiz.question) &&
				a.equals(quiz.a) &&
				b.equals(quiz.b) &&
				c.equals(quiz.c) &&
				answer.equals(quiz.answer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(question, a, b, c, answer);
	}
}
