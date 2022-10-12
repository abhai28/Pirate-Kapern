package project;
import junit.framework.TestCase;
public class GameTest extends TestCase{
    Game game;

    public GameTest(){
        game = new Game();
    }
    public void testPopulateDeck(){
        game.populateDeck();
        //test checks if the size of array is correct and if the first card matchs expected card
        assertEquals(35,game.fortuneCards.size());
        assertEquals("Treasure Chest", game.fortuneCards.get(0).getName());
    }

    public void testShuffleDeck(){
        game.populateDeck();
        String n = game.fortuneCards.get(0).getName();
        game.shuffleDeck();
        assertNotSame(n,game.fortuneCards.get(0).getName());
    }

    public void testRollDice(){
        Player p = new Player(1);
        game.rollDice(p);
        assertEquals(8,p.getPlayerDice().size());
    }
}
