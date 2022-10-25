package project;
import junit.framework.TestCase;

public class AcceptanceTest extends TestCase{
    Game game;
    Player p;
    public AcceptanceTest (){
        game = new Game();
        p = new Player(1);
    }
}
