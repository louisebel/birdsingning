package com.birdsinging;

import com.birdsinging.level.LevelEntity;
import com.birdsinging.level.quiz.Choice;
import com.birdsinging.level.Level;
import com.birdsinging.level.quiz.ChoiceEntity;
import com.birdsinging.level.quiz.Quiz;
import com.birdsinging.level.StatusLevel;
import com.birdsinging.level.quiz.QuizEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Converter {

    private static List<Quiz> transformIntoQuizes(List<QuizEntity> quizEntities){
        if(quizEntities!=null && !quizEntities.isEmpty()){
          return  quizEntities.stream()
                    .map(Converter::transformIntoQuiz)
                    .collect(Collectors.toList());
        }
            return new ArrayList<>();
    }


    private static Quiz transformIntoQuiz(QuizEntity quizEntity){
        return new Quiz( quizEntity.getQuizIdentity().getQuestion(),
                         transformIntoChoice(quizEntity.getQuizIdentity().getA()),
                         transformIntoChoice(quizEntity.getQuizIdentity().getB()),
                         transformIntoChoice(quizEntity.getQuizIdentity().getC()),
                         transformIntoChoice(quizEntity.getQuizIdentity().getAnswer()));
    }

    public static Level transformIntoLevel(LevelEntity levelEntity) {
        return new Level(levelEntity.getId(), StatusLevel.valueOf(levelEntity.getStatus()), transformIntoQuizes(levelEntity.getQuizEntities()));
    }

    private static Choice transformIntoChoice(ChoiceEntity choiceEntity){
        return new Choice(choiceEntity.getValue());
    }

    public static Optional<Level> transformIntoLevel(Optional<LevelEntity> levelEntity) {
        if(levelEntity.isPresent()) {
            return Optional.of(new Level(levelEntity.get().getId(),
                    StatusLevel.valueOf(levelEntity.get().getStatus()),
                    transformIntoQuizes(levelEntity.get().getQuizEntities())));
        }else{
            return Optional.empty();
        }
    }

    public static List<Level> transformIntoLevels(List<LevelEntity> levelEntities){
        return levelEntities.stream()
                .map(Converter::transformIntoLevel)
                .collect(Collectors.toList());
    }

}
