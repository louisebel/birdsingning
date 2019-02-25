package com.birdsinging.level;

import java.util.List;
import java.util.Optional;

public interface LevelRepository {

    Optional<Level> getLevel(long LevelId);

    Level postLevel();

    Level updateLevel(Level level);

    List<Level> getLevelByStatus(StatusLevel statusLevel);

    List<Level> getLevels();

    Long countAllSmallerLevelActive(Long id, StatusLevel statusLevel);
}
