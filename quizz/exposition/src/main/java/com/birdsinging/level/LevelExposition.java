package com.birdsinging.level;

import com.birdsinging.level.quiz.QuizExposition;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

class LevelExposition {

    private long id;

    @NotNull
    private String status;

    private List<QuizExposition> quizExpositions;

    public LevelExposition() {
    }

    public LevelExposition(Level level) {
        this.id = level.getId();
        this.status = level.getStatus().toString();
        this.quizExpositions = level.getQuizes()
                                        .stream()
                                        .map(QuizExposition::new)
                                        .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<QuizExposition> getQuizExpositions() {
        return quizExpositions;
    }

    public Level transformIntoNewLevel(){
        return new Level(
                this.id,
                StatusLevel.valueOf(this.status),
                this.quizExpositions
                        .stream()
                        .map(QuizExposition::transformIntoNewQuiz)
                        .collect(Collectors.toList()));
    }

}
