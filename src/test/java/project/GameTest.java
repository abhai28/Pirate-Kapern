package project;
import io.cucumber.java.sl.In;
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

        game.calculateDiceScore(p);
        assertEquals(300,p.getScore());
    }
    public void testCalculateDiceScoreDiamond(){
        Player p = new Player(1);
        String[] values = {"Monkey","Monkey","Monkey","Sword","Parrot","Skull","Gold","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);

        game.calculateDiceScore(p);
        assertEquals(400,p.getScore());
    }
    public void testCalculateDiceScoreGold(){
        Player p = new Player(1);
        String[] values = {"Monkey","Monkey","Monkey","Sword","Parrot","Skull","Gold","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);

        game.calculateDiceScore(p);
        assertEquals(400,p.getScore());
    }
    public void testCalculateDiceScoreMonkey(){
        Player p = new Player(1);
        String[] values = {"Monkey","Monkey","Monkey","Sword","Parrot","Skull","Gold","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);

        game.calculateDiceScore(p);
        assertEquals(400,p.getScore());
    }
    public void testDetermineWinners(){
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Player p3 = new Player(3);
        p1.setScore(300);
        p2.setScore(3000);
        p3.setScore(2999);
        game.players.add(p1);
        game.players.add(p2);
        game.players.add(p3);
        assertTrue(game.determineWinners());
    }

    public void testSkullIsland(){
        String[] values = {"Skull","Monkey","Monkey","Skull","Parrot","Skull","Gold","Skull"};
        ArrayList<String> trueTest = new ArrayList<>(Arrays.asList(values));
        values = new String[]{"Skull", "Monkey", "Monkey", "Sword", "Parrot", "Skull", "Gold", "Diamond"};
        ArrayList<String> falseTest = new ArrayList<>(Arrays.asList(values));
        Player p = new Player(1);
        p.setPlayerDices(trueTest);
        assertTrue(game.skullIsland(p));
        p.setPlayerDices(falseTest);
        assertFalse(game.skullIsland(p));
    }
    public void testSkullsIsland2(){

        String []values = {"Skull", "Monkey", "Monkey", "Sword", "Parrot", "Skull", "Gold", "Diamond"};
        ArrayList<String> falseTest = new ArrayList<>(Arrays.asList(values));
        Fortune f = new Fortune("Skulls",3);
        Player p = new Player(1);
        p.setFortuneCard(f);
        p.setPlayerDices(falseTest);
        assertTrue(game.skullIsland(p));
    }
    public void testArrayDiceToString(){
        String[] values = {"Skull","Monkey","Monkey","Skull","Parrot","Skull","Gold","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        Player p = new Player(1);
        p.setPlayerDices(dice);
        String d = "1: Skull 2: Monkey 3: Monkey 4: Skull 5: Parrot 6: Skull 7: Gold 8: Skull ";
        assertEquals(d,game.arrayDiceToString(p));
    }
    public void testSkullIslandReroll(){
        String[] values = {"Skull","Monkey","Monkey","Skull","Parrot","Skull","Gold","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        Player p = new Player(1);
        p.setPlayerDices(dice);
        game.skullIslandReroll(p);
        assertEquals("Skull",p.getPlayerDice().get(0));
        assertEquals("Skull",p.getPlayerDice().get(3));
        assertEquals("Skull",p.getPlayerDice().get(5));
        assertEquals("Skull",p.getPlayerDice().get(7));
    }
    public void testSkullIslandDeduction(){
        Player p = new Player(1);
        assertEquals(400, game.skullIslandDeduction(p,4));
        Fortune f = new Fortune("Captain",0);
        p.setFortuneCard(f);
        assertEquals(800,game.skullIslandDeduction(p,4));
    }
    public void testMultiplayerReroll(){
        String[] values = {"Skull","Monkey","Monkey","Skull","Parrot","Skull","Gold","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        Player p = new Player(1);
        p.setPlayerDices(dice);
        ArrayList<Integer> m = new ArrayList<>();
        m.add(1);
        m.add(4);
        game.multiplayerReroll(p,m);
        assertNotSame("Monkey",p.getPlayerDice().get(1));
        assertNotSame("Parrot",p.getPlayerDice().get(4));
    }
    public void testArrayTreasureChestToString(){
        String[] values = {"Skull","Monkey","Monkey","Skull","Parrot","Skull","Gold","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        String d = "Treasure Chest: 1: Skull 2: Monkey 3: Monkey 4: Skull 5: Parrot 6: Skull 7: Gold 8: Skull ";
        assertEquals(d,game.arrayTreasureChestToString(dice));
    }
    public void testTreasureChestScoreCalculator(){
        Player p = new Player(1);
        String[] values = {"Monkey","Monkey","Monkey","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));


        assertEquals(200,game.treasureChestScoreCalculator(dice));
    }

    public void testSeaBattleScore(){
        String[] values = {"Sword","Monkey","Monkey","Sword","Parrot","Skull","Gold","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        Player p = new Player(1);
        p.setPlayerDices(dice);
        Fortune f = new Fortune("Sea Battle",2);
        p.setFortuneCard(f);
        assertEquals(300,game.seaBattleScore(p));

        values = new String[]{"Sword", "Sword", "Monkey", "Sword", "Parrot", "Skull", "Gold", "Skull"};
        dice = new ArrayList<>(Arrays.asList(values));
        p = new Player(1);
        p.setPlayerDices(dice);
        f = new Fortune("Sea Battle",3);
        p.setFortuneCard(f);
        assertEquals(500,game.seaBattleScore(p));

        values = new String[]{"Sword", "Sword", "Sword", "Sword", "Parrot", "Skull", "Gold", "Skull"};
        dice = new ArrayList<>(Arrays.asList(values));
        p = new Player(1);
        p.setPlayerDices(dice);
        f = new Fortune("Sea Battle",4);
        p.setFortuneCard(f);
        assertEquals(1000,game.seaBattleScore(p));

        values = new String[]{"Monkey", "Monkey", "Monkey", "Sword", "Parrot", "Skull", "Gold", "Skull"};
        dice = new ArrayList<>(Arrays.asList(values));
        p = new Player(1);
        p.setPlayerDices(dice);
        f = new Fortune("Sea Battle",3);
        p.setFortuneCard(f);
        assertEquals(-500,game.seaBattleScore(p));
    }
}
