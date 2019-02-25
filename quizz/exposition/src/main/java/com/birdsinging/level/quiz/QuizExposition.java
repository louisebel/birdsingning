package com.birdsinging.level.quiz;

import com.birdsinging.level.quiz.Choice;
import com.birdsinging.level.quiz.Quiz;

import javax.validation.constraints.NotEmpty;

public class QuizExposition  {
    @NotEmpty
    private String question;
    @NotEmpty
    private String a;
    @NotEmpty
    private String b;
    @NotEmpty
    private String c;
    @NotEmpty
    private String answer;

    public QuizExposition() { }

    public QuizExposition(Quiz quiz) {
        this.question = quiz.getQuestion();
        this.a = quiz.getA().getValue();
        this.b = quiz.getB().getValue();
        this.c = quiz.getC().getValue();
        this.answer = quiz.getAnswer().getValue();
    }

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getAnswer() {
        return answer;
    }

    public Quiz transformIntoNewQuiz(){
        return new Quiz(this.question,
                new Choice(this.a),
                new Choice(this.b),
                new Choice(this.c),
                new Choice(this.answer));
    }

}
