package com.birdsinging.level;

import com.birdsinging.level.quiz.QuizEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="LEVEL")
public class LevelEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="STATUS")
    private String status;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    //(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "LEVEL_ID")
    private List<QuizEntity> quizEntities;

    public LevelEntity() {
        this.status="INACTIVE";
    }

    public LevelEntity(Level level) {
        this.id = level.getId();
        this.status=level.getStatus().toString();
        this.quizEntities = level.getQuizes()
                                 .stream()
                                 .map(QuizEntity::new)
                                 .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<QuizEntity> getQuizEntities() { return quizEntities;}
}
