package project;
import junit.framework.TestCase;
import org.junit.Assert;

public class PlayerTest extends TestCase{
    Player p;
    public PlayerTest(){
        p = new Player();
    }

    public void testFortuneCardValue(){
        p.setFortuneCard("Captain");

        assertEquals("Captain",p.getFortuneCard());
    }
    public void testGetPlayerID(){
        assertEquals(1,p.getPlayerID());
    }
    public void testPlayerDiceValue(){
        String[] dice = {"Monkey","Monkey","Monkey","Sword","Parrot","Skull","Gold Coin","Diamond"};
        p.setPlayerDice(dice);
        Assert.assertArrayEquals(dice,p.getPlayerDice());
    }
}
