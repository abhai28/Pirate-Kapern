package project;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class AcceptanceTest extends TestCase{
    Game game;
    public AcceptanceTest (){
        game = new Game();
    }
    public void testRow45(){
        Player p = new Player(1);
        String[] values = {"Skull","Sword","Sword","Skull","Sword","Skull","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(0,p.getScore());
    }
}
