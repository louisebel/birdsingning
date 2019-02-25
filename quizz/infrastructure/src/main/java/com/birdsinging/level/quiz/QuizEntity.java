package com.birdsinging.level.quiz;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="QUIZ")
public class QuizEntity {

    @EmbeddedId
    private QuizIdentity quizIdentity;

    public QuizEntity() {
    }

    public QuizEntity(Quiz quiz) {
        this.quizIdentity= new QuizIdentity(quiz);
    }

    public QuizIdentity getQuizIdentity() {
        return quizIdentity;
    }
}
