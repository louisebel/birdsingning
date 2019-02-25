package com.birdsinging.level;


import com.birdsinging.exception.ErrorMessage;
import com.birdsinging.exception.LevelBusinessException;
import com.birdsinging.level.quiz.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
DDD
Aggregates
Entity
 */
public class Level {

    private Long id;
    private StatusLevel status;
    private List<Quiz> quizes;

    public Level() {
    }

    public Level(Long id, StatusLevel status, List<Quiz> quizes) {
        this.id = id;
        this.status = status;
        this.quizes = quizes;
    }

    public Long getId() {
        return id;
    }

    public StatusLevel getStatus() {
        return status;
    }

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void verifyPatchLevel( Long numberAllSmallerLevelActive) {
        List<String> errorMessages = new ArrayList<>();
        if(activateLevelWithoutQuiz()){
            errorMessages.add(ErrorMessage.IMPOSSIBLE_TO_ACTIVATE_QUIZES_EMPTY);
        }
        if(!(quizes==null)) {
            quizes.forEach(quiz -> {
                if (quiz.verify().isPresent()) {
                    errorMessages.add(quiz.verify().get());
                }
            });
        }
        if (allSmallerLevelActive(numberAllSmallerLevelActive)) {
            errorMessages.add(ErrorMessage.IMPOSSIBLE_TO_ACTIVATE_LEVEL_ALL_PREVIOUS_LEVEL_NOT_ACTIVATE);
        }
        if(!errorMessages.isEmpty()){
            throw new LevelBusinessException(String.join(" - ", errorMessages));
        }


    }


    private boolean allSmallerLevelActive(Long numberAllSmallerLevelActive){
        return ((numberAllSmallerLevelActive+ 1) != this.getId());
    }

    private boolean activateLevelWithoutQuiz() {
        return this.status == StatusLevel.ACTIVE
                && (this.quizes == null || this.quizes.isEmpty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return Objects.equals(id, level.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
