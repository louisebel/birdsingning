package com.birdsinging.level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository){
        this.levelRepository = levelRepository;
    }

    public Level postLevel() {
        return levelRepository.postLevel();
    }

    public List<Level> getLevelActivate() {
        return levelRepository.getLevelByStatus(StatusLevel.ACTIVE);
    }

    public List<Level> getLevel() {
        return levelRepository.getLevels();
    }

    public Level patchLevel(Level level, Long levelId) {
        level.verifyPatchLevel(levelRepository.countAllSmallerLevelActive(levelId, level.getStatus()));
        return  levelRepository.updateLevel(level);
    }
}
