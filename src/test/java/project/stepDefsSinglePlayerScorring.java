package project;

import java.util.ArrayList;
import java.util.Arrays;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.TestCase;

public class stepDefsSinglePlayerScorring extends TestCase{
    Player p = new Player(1);
    Game game = new Game();

    @Given("Play a turn")
    public void play_turn(){
        game.populateDeck();
        game.shuffleDeck();
        p.setScore(0);
    }
    @When("Get fortune card")
    public void get_fortune_card(){
        game.drawFortuneCard(p);
    }
    @And("Roll dice")
    public void random_roll_dice(){
        game.rollDice(p);
    }
    @And("Fortune card is {string} {int}")
    public void set_fortune_card(String fortune, int amount){
        Fortune f = new Fortune(fortune,amount);
        p.setFortuneCard(f);
    }
    @And("Roll is {string}")
    public void roll_dice(String d){
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(d.split(",")));
        p.setPlayerDices(dice);
    }
    @Then("Score is {int}")
    public void score(int score) {
        game.calculateDiceScore(p);
        assertEquals(score, p.getScore());
    }
}
