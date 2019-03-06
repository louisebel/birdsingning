package com.birdsinging.level;

import com.birdsinging.level.quiz.Choice;
import com.birdsinging.level.quiz.Quiz;
import cucumber.api.DataTable;
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


public class AdministrationLevelStep {

    @Mock
    private LevelRepository levelRepository;
    private LevelService levelService;
    private Level level;
    private Level levelResult;
    private List<Quiz> quizes;


    @Before
    public final void setUp(){
        levelRepository = mock(LevelRepository.class);
        levelService = new LevelService(levelRepository);
        quizes = new ArrayList<>();
    }

    @Given("^The level (\\d+) exists with quizes :$")
    public void theLevelExistsWithQuizes(Long levelId, DataTable data) {
        List<List<String>> quizesData = data.raw();
        quizes.add(new Quiz(quizesData.get(1).get(0),
                            new Choice(quizesData.get(1).get(1)),
                            new Choice(quizesData.get(1).get(2)),
                            new Choice(quizesData.get(1).get(3)),
                            new Choice(quizesData.get(1).get(4))));
        level= new Level(levelId, StatusLevel.ACTIVE,quizes);
    }

    @And("^they are already (\\d+) activated levels$")
    public void theyAreAlreadyLevelsActivates(int numbersOfActivatesLevel) {
        Mockito.when(levelRepository.countAllSmallerLevelActive(4L, StatusLevel.ACTIVE)).thenReturn(Long.valueOf(3));
    }

    @When("^an admin try to update the level (\\d+)$")
    public void anAdminTryToUpdateTheLevel(Long levelId) {
        //mock de la bdd
        Mockito.when(levelRepository.updateLevel(level)).thenReturn(level);
        levelResult = levelService.patchLevel(level, levelId);
    }


    @Then("^activation of the level (\\d+) should have been a success$")
    public void activationOfTheLevelShouldHaveBeenASuccess(Long levelId) {
        assertThat(levelResult.getId()).isEqualTo(levelId);
        assertThat(levelResult.getStatus()).isEqualTo(StatusLevel.ACTIVE);
    }

}
