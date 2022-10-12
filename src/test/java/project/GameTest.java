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
        String n = game.fortuneCards.get(0).getName();
        game.shuffleDeck();
        assertEquals(n,game.fortuneCards.get(0).getName());
    }
}
