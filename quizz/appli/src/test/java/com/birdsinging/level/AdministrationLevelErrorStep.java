package com.birdsinging.level;

import com.birdsinging.exception.LevelBusinessException;
import com.birdsinging.level.quiz.Choice;
import com.birdsinging.level.quiz.Quiz;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;


public class AdministrationLevelErrorStep {

    @Mock
    private LevelRepository levelRepository;
    private LevelService levelService;
    private Level level;
    private Quiz exampleOfQuiz;
    private Exception exceptedException;


    @Before
    public final void setUp(){
        levelRepository = mock(LevelRepository.class);
        levelService = new LevelService(levelRepository);
        exampleOfQuiz = new Quiz("cock-a-doodle-doo",
                                        new Choice("rooster"),
                                        new Choice("hen"),
                                        new Choice("eagle"),
                                        new Choice("rooster"));

    }




    @Given("^The level has an \"([^\"]*)\" and \"([^\"]*)\" and will be activate$")
    public void theLevelHasAnIdAndNumberOfQuizesAndWillBeActivate(Long levelId, int numberOfquiz) {
        List<Quiz> quizes = new ArrayList<>();
        for(int i=0; i<numberOfquiz; i++){
            quizes.add(exampleOfQuiz);
        }
        level = new Level(levelId, StatusLevel.ACTIVE, quizes);
    }

    @And("^the \"([^\"]*)\"$")
    public void the(int numberOfPreviousLevelActivate){
        Mockito.when(levelRepository.countAllSmallerLevelActive(level.getId(), level.getStatus())).thenReturn(Long.valueOf(numberOfPreviousLevelActivate));

    }

    @When("^an admin try to patch the level \"([^\"]*)\"$")
    public void anAdminTryToPatchTheLevel(Long levelId) {
        Mockito.when(levelRepository.updateLevel(level)).thenReturn(level);
        try {
            levelService.patchLevel(level, levelId);
        }catch (Exception e){
            exceptedException = e;
        }
    }

    @Then("^there is an exception with \"([^\"]*)\"$")
    public void theyAreAnExceptionWith(String exceptionMessage) {
            assertThat(exceptedException).isInstanceOf(LevelBusinessException.class);
            assertThat(exceptedException.getMessage()).isEqualTo(exceptionMessage);
    }


}
