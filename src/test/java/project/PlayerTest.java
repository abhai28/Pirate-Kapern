package project;
import junit.framework.TestCase;
public class PlayerTest extends TestCase{
    Player p;
    public PlayerTest(){
        p = new Player();
    }

    public void testFortuneCardValue(){
        p.setFortuneCard("Captain");

        assertEquals("Captain",p.getFortuneCard());
    }
}
