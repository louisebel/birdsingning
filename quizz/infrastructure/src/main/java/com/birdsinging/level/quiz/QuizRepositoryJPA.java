package com.birdsinging.level.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepositoryJPA extends JpaRepository<QuizEntity, QuizIdentity> {
}
