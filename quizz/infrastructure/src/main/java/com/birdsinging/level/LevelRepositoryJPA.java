package com.birdsinging.level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepositoryJPA extends JpaRepository<LevelEntity, String> {

    LevelEntity findTop1ByOrderByIdDesc();
    Optional<LevelEntity> findById(Long id);
    List<LevelEntity> findAllByStatus(String status);
    Long countByIdBeforeAndStatusEquals(Long id, String Status);
}