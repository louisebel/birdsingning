package birdsinging.level;

import com.birdsinging.exception.ErrorMessage;
import com.birdsinging.exception.LevelBusinessException;
import com.birdsinging.level.quiz.Choice;
import com.birdsinging.level.Level;
import com.birdsinging.level.quiz.Quiz;
import com.birdsinging.level.StatusLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class LevelTest {

    private List<Quiz> quizes;

    @Before
    public final void setUp() {
        quizes = new ArrayList<>();
        quizes.add(new Quiz("question",
                new Choice("A"),
                new Choice("B"),
                new Choice("C"),
                new Choice("C")));
    }

    @Test
    public void level_equality_on_id(){
        Level level = new Level(1L, StatusLevel.ACTIVE, new ArrayList<>());
        Level levelEqual = new Level(1L, StatusLevel.INACTIVE, new ArrayList<>());
        assertThat(level).isEqualTo(levelEqual);
    }
    @Test
    public void patch_active_level_without_quiz_generate_errors(){
        Level level = new Level(1L, StatusLevel.ACTIVE, new ArrayList<>());
        assertThatThrownBy(()->level.verifyPatchLevel(1L))
                .isInstanceOf(LevelBusinessException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_TO_ACTIVATE_QUIZES_EMPTY );
    }

    @Test
    public void patch_active_level_with_null_quizes_generate_errors(){
        Level level = new Level(1L, StatusLevel.ACTIVE, null);
        assertThatThrownBy(()->level.verifyPatchLevel(1L))
                .isInstanceOf(LevelBusinessException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_TO_ACTIVATE_QUIZES_EMPTY);
    }

    @Test
    public void patch_active_level_quizes_OK(){
        Level level = new Level(1L, StatusLevel.ACTIVE, quizes);
        assertThatCode(() ->level.verifyPatchLevel(0L)).doesNotThrowAnyException();

    }




}
