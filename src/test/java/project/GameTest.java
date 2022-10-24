package project;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTest extends TestCase{
    Game game;

    public GameTest(){
        game = new Game();
        game.populateDeck();
    }
    public void testPopulateDeck(){
        //test checks if the size of array is correct and if the first card matchs expected card
        assertEquals(35,game.fortuneCards.size());
        assertEquals("Treasure Chest", game.fortuneCards.get(0).getName());
    }

    public void testShuffleDeck(){
        String n = game.fortuneCards.get(0).getName();
        game.shuffleDeck();
        assertNotSame(n,game.fortuneCards.get(0).getName());
    }

    public void testRollDice(){
        Player p = new Player(1);
        game.rollDice(p);
        assertEquals(8,p.getPlayerDice().size());
    }

    public void testDrawFortuneCard(){
        Player p = new Player(1);
        game.drawFortuneCard(p);
        assertNotSame("",p.getFortuneCard().getName());
    }
    public void testReRoll(){
        Player p = new Player(1);
        game.rollDice(p);
        String v = p.getPlayerDice().get(3);
        game.reroll(p,3);
        assertNotSame(v,p.getPlayerDice().get(3));
    }
    public void testCalculateDiceScore(){
        Player p = new Player(1);
        String[] values = {"Monkey","Monkey","Monkey","Sword","Parrot","Skull","Gold","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        Fortune f = new Fortune("Treasure Chest",0);
        p.setFortuneCard(f);

        p.setScore(game.calculateDiceScore(p.getPlayerDice()));
        assertEquals(300,p.getScore());
    }
}
