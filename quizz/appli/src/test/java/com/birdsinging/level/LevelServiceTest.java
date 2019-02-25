package com.birdsinging.level;

import com.birdsinging.exception.ErrorMessage;
import com.birdsinging.exception.LevelBusinessException;
import com.birdsinging.level.quiz.Choice;
import com.birdsinging.level.quiz.Quiz;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class LevelServiceTest {

    @Mock
    private LevelRepository levelRepository;
    private LevelService levelService;
    private List<Quiz> quizes;

    @Before
    public final void setUp(){
        levelRepository = mock(LevelRepository.class);
        levelService = new LevelService(levelRepository);
        quizes = new ArrayList<>();
        quizes.add(new Quiz("question",
                                new Choice("A"),
                                new Choice("B"),
                                new Choice("C"),
                                new Choice ("C")));
    }

    @Test
    public final void smaller_Level_not_Activate_generate_Error(){
        Level level2 = new Level(2L, StatusLevel.ACTIVE, quizes);
        Mockito.when(levelRepository.countAllSmallerLevelActive(2L, StatusLevel.ACTIVE)).thenReturn(Long.valueOf(0));
        assertThatThrownBy(() -> levelService.patchLevel(level2, 2L)).isInstanceOf(LevelBusinessException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_TO_ACTIVATE_LEVEL_ALL_PREVIOUS_LEVEL_NOT_ACTIVATE);
    }

    @Test
    public final void smaller_Level_Activate_patch_OK(){
        Level level3 = new Level(3L, StatusLevel.ACTIVE, quizes);
        Mockito.when(levelRepository.countAllSmallerLevelActive(3L, StatusLevel.ACTIVE)).thenReturn(Long.valueOf(2));
        Mockito.when(levelRepository.updateLevel(level3)).thenReturn(level3);
        assertThat(levelService.patchLevel(level3, 3L)).isEqualTo(level3);
    }

}
