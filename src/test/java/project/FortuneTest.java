package project;
import junit.framework.TestCase;
public class FortuneTest extends TestCase{
    Fortune f;
    public FortuneTest(){
        f = new Fortune();
    }
    public void testGetName(){
        f = new Fortune("Captain",0);
        assertEquals("Captain",f.getName());
    }
    public void testGetAmount(){
        f = new Fortune("Sea Battle",2);
        assertEquals(2,f.getAmount());
    }
}
