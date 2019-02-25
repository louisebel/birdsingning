package com.birdsinging.level.quiz;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class QuizIdentity implements Serializable {

    @Column(name = "QUESTION")
    private String question;

    @AttributeOverrides( {
            @AttributeOverride(name="value", column = @Column
                    (name = "CHOICE_A"))
    })
    @Embedded
    private ChoiceEntity a;

    @AttributeOverrides( {
            @AttributeOverride(name="value", column = @Column
                    (name = "CHOICE_B"))
    })
    @Embedded
    private ChoiceEntity b;

    @AttributeOverrides( {
            @AttributeOverride(name="value", column = @Column
                    (name = "CHOICE_C"))
    })
    @Embedded
    private ChoiceEntity c;

    @AttributeOverrides( {
            @AttributeOverride(name="value", column = @Column
                    (name = "ANSWER"))
    })
    @Embedded
    private ChoiceEntity answer;



    public QuizIdentity() {
    }

    public QuizIdentity(Quiz quiz) {
        this.question = quiz.getQuestion();
        this.a = new ChoiceEntity(quiz.getA());
        this.b = new ChoiceEntity(quiz.getB());
        this.c = new ChoiceEntity(quiz.getC());
        this.answer =  new ChoiceEntity(quiz.getAnswer());
    }

    public String getQuestion() {
        return question;
    }

    public ChoiceEntity getA() {
        return a;
    }

    public ChoiceEntity getB() {
        return b;
    }

    public ChoiceEntity getC() {
        return c;
    }

    public ChoiceEntity getAnswer() {
        return answer;
    }
}
