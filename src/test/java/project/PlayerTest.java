package project;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest extends TestCase{
    Player p;
    public PlayerTest(){
        p = new Player();
    }

    public void testFortuneCardValue(){
        Fortune f = new Fortune("Captain",0);
        p.setFortuneCard(f);

        assertEquals("Captain",p.getFortuneCard().getName());
    }
    public void testGetPlayerID(){
        assertEquals(1,p.getPlayerID());
    }
    public void testPlayerDiceValue(){
        String[] values = {"Monkey","Monkey","Monkey","Sword","Parrot","Skull","Gold Coin","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));

        p.setPlayerDice(dice);
        assertEquals(dice, p.getPlayerDice());
    }
    public void testPlayerScoreValue(){
        p.setScore(300);
        assertEquals(300,p.getScore());
    }
}
