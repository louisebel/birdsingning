package com.birdsinging.level;

import com.birdsinging.Converter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LevelRepositoryImpl implements LevelRepository {

    @Autowired
    private LevelRepositoryJPA levelRepositoryJPA;

    @Override
    public Optional<Level> getLevel(long levelId) {
        return Converter.transformIntoLevel(levelRepositoryJPA.findById(levelId));
    }

    @Override
    public Level postLevel() {
        levelRepositoryJPA.save(new LevelEntity());
        return Converter.transformIntoLevel(levelRepositoryJPA.findTop1ByOrderByIdDesc());
    }

    @Override
    public Level updateLevel(Level level) {
         levelRepositoryJPA.save(new LevelEntity(level));
         return level;
    }

    @Override
    public List<Level> getLevelByStatus(StatusLevel statusLevel) {
        return Converter.transformIntoLevels(levelRepositoryJPA.findAllByStatus(statusLevel.toString()));
    }

    @Override
    public List<Level> getLevels() {
        return Converter.transformIntoLevels(levelRepositoryJPA.findAll());
    }

    @Override
    public Long countAllSmallerLevelActive(Long id, StatusLevel statusLevel) {
        return levelRepositoryJPA.countByIdBeforeAndStatusEquals(id, statusLevel.toString());
    }

}
