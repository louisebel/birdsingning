package com.birdsinging.level.quiz;

import com.birdsinging.level.quiz.Choice;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChoiceEntity {
    public String getValue() {
        return value;
    }

    @Column
    private String value;

    public ChoiceEntity(Choice choice) {
        this.value = choice.getValue();
    }

    public ChoiceEntity() {
    }
}
