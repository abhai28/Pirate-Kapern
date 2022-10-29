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
    public void testRow46(){
        Player p = new Player(1);
        String[] values = {"Parrot","Parrot","Parrot","Skull","Sword","Parrot","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(4);
        diceNum.add(6);
        diceNum.add(7);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Parrot", "Parrot", "Parrot", "Skull", "Skull", "Parrot", "Skull", "Sword"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(0,p.getScore());
    }
    public void testRow47(){
        Player p = new Player(1);
        String[] values = {"Parrot","Parrot","Parrot","Skull","Sword","Parrot","Sword","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(4);
        diceNum.add(6);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Parrot", "Parrot", "Parrot", "Skull", "Skull", "Parrot", "Sword", "Skull"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(0,p.getScore());
    }

    public void testRow48(){
        Player p = new Player(1);
        String[] values = {"Parrot","Parrot","Parrot","Skull","Sword","Parrot","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(4);
        diceNum.add(6);
        diceNum.add(7);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Parrot", "Parrot", "Parrot", "Skull", "Skull", "Parrot", "Monkey", "Monkey"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        diceNum.add(7);
        diceNum.add(6);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Parrot", "Parrot", "Parrot", "Skull", "Skull", "Parrot", "Skull", "Monkey"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        game.calculateDiceScore(p);
        assertEquals(0,p.getScore());
    }

    public void testRow50(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Parrot","Gold","Skull","Sword","Gold","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(0);
        diceNum.add(1);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Gold", "Gold", "Gold", "Skull", "Sword", "Gold", "Sword", "Sword"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        diceNum.add(4);
        diceNum.add(6);
        diceNum.add(7);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Gold", "Gold", "Gold", "Skull", "Gold", "Gold", "Gold", "Gold"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        game.calculateDiceScore(p);
        assertEquals(4800,p.getScore());
    }

    public void testRow52(){
        Player p = new Player(1);
        Fortune f = new Fortune("Captain",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Parrot","Gold","Gold","Diamond","Diamond","Monkey","Monkey"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(800,p.getScore());
    }

    public void testRow53(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Parrot","Skull","Skull","Sword","Sword","Monkey","Monkey"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(0);
        diceNum.add(1);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Sword","Monkey","Skull","Skull","Sword","Sword","Monkey","Monkey"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(300,p.getScore());
    }

    public void testRow54(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Sword","Skull","Sword","Sword","Skull","Monkey","Monkey"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(300,p.getScore());
    }

    public void testRow55(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Diamond","Diamond","Diamond","Monkey","Skull","Skull","Sword","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(500,p.getScore());
    }
    public void testRow56(){
        Player p = new Player(1);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);
        String[] values = {"Gold","Gold","Gold","Gold","Skull","Skull","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(700,p.getScore());
    }
    public void testRow57(){
        Player p = new Player(1);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);
        String[] values = {"Sword","Parrot","Parrot","Parrot","Parrot","Skull","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(400,p.getScore());
    }

    public void testRow58(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Gold","Gold","Parrot","Parrot","Skull","Sword","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(2);
        diceNum.add(3);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Gold","Gold","Gold","Sword","Skull","Sword","Sword","Sword"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        game.calculateDiceScore(p);
        assertEquals(800,p.getScore());
    }
    public void testRow59(){
        Player p = new Player(1);
        Fortune f = new Fortune("Captain",0);
        p.setFortuneCard(f);
        String[] values = {"Gold","Gold","Parrot","Parrot","Skull","Sword","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(2);
        diceNum.add(3);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Gold","Gold","Gold","Sword","Skull","Sword","Sword","Sword"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        game.calculateDiceScore(p);
        assertEquals(1200,p.getScore());
    }

    public void testRow60(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);

        String[] values = {"Parrot","Parrot","Monkey","Skull","Sword","Monkey","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(2);
        diceNum.add(5);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Parrot","Parrot","Sword","Skull","Sword","Skull","Sword","Sword"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        diceNum.add(0);
        diceNum.add(1);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Sword","Monkey","Sword","Skull","Sword","Skull","Sword","Sword"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);

        game.calculateDiceScore(p);
        assertEquals(600,p.getScore());
    }
    public void testRow62(){
        Player p = new Player(1);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Skull","Monkey","Monkey","Monkey","Skull","Monkey","Monkey"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1100,p.getScore());
    }
    public void testRow63(){
        Player p = new Player(1);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Parrot","Parrot","Parrot","Parrot","Skull","Parrot","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(2100,p.getScore());
    }
    public void testRow64(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Gold","Gold","Gold","Gold","Gold","Gold","Gold","Gold"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(5400,p.getScore());
    }
    public void testRow65(){
        Player p = new Player(1);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);
        String[] values = {"Gold","Gold","Gold","Gold","Gold","Gold","Gold","Gold"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(5400,p.getScore());
    }
    public void testRow66(){
        Player p = new Player(1);
        Fortune f = new Fortune("Captain",0);
        p.setFortuneCard(f);
        String[] values = {"Sword","Sword","Sword","Sword","Sword","Sword","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(9000,p.getScore());
    }
    public void testRow67(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);

        String[] values = {"Monkey","Monkey","Monkey","Monkey","Sword","Monkey","Monkey","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(4);
        diceNum.add(7);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Monkey","Monkey","Monkey","Monkey","Monkey","Monkey","Monkey","Monkey"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(4600,p.getScore());
    }
    public void testRow68(){
        Player p = new Player(1);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);

        String[] values = {"Monkey","Monkey","Skull","Skull","Sword","Sword","Parrot","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(6);
        diceNum.add(7);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Monkey","Monkey","Skull","Skull","Sword","Sword","Diamond","Diamond"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(400,p.getScore());
    }

    public void testRow69(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);

        String[] values = {"Monkey","Monkey","Skull","Skull","Sword","Sword","Diamond","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(0);
        diceNum.add(1);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Diamond","Diamond","Skull","Skull","Sword","Sword","Diamond","Parrot"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(500,p.getScore());
    }

    public void testRow70(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);

        String[] values = {"Monkey","Parrot","Skull","Sword","Gold","Gold","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        ArrayList<Integer> diceNum = new ArrayList<>();
        diceNum.add(3);
        diceNum.add(6);
        diceNum.add(7);
        game.multiplayerReroll(p,diceNum);
        values = new String[]{"Monkey","Parrot","Skull","Gold","Gold","Gold","Monkey","Parrot"};
        dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(600,p.getScore());
    }
    public void testRow71(){
        Player p = new Player(1);
        Fortune f = new Fortune("Diamond",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Parrot","Skull","Gold","Gold","Gold","Monkey","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(500,p.getScore());
    }
    public void testRow72(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Monkey","Monkey","Monkey","Gold","Gold","Skull","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(600,p.getScore());
    }
    public void testRow77(){
        Player p = new Player(1);
        Fortune f = new Fortune("Sorceress",0);
        p.setFortuneCard(f);
        String[] values = {"Diamond","Diamond","Sword","Monkey","Gold","Monkey","Monkey","Monkey"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(500,p.getScore());
    }
    public void testRow78(){
        Player p = new Player(1);
        Fortune f = new Fortune("Sorceress",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Skull","Skull","Parrot","Parrot","Parrot","Parrot","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1000,p.getScore());
    }
    public void testRow79(){
        Player p = new Player(1);
        Fortune f = new Fortune("Sorceress",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Skull","Parrot","Parrot","Parrot","Parrot","Parrot","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(2000,p.getScore());
    }
    public void testRow82(){
        Player p = new Player(1);
        Fortune f = new Fortune("Monkey Business",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Skull","Monkey","Monkey","Monkey","Gold","Parrot","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1100,p.getScore());
    }
    public void testRow83(){
        Player p = new Player(1);
        Fortune f = new Fortune("Monkey Business",0);
        p.setFortuneCard(f);
        String[] values = {"Parrot","Parrot","Monkey","Monkey","Monkey","Gold","Gold","Parrot"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1700,p.getScore());
    }
    public void testRow84(){
        Player p = new Player(1);
        Fortune f = new Fortune("Monkey Business",0);
        p.setFortuneCard(f);
        String[] values = {"Skull","Skull","Skull","Monkey","Monkey","Parrot","Parrot","Monkey"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(0,p.getScore());
    }

    public void testRow87(){
        Player p = new Player(1);
        Fortune f = new Fortune("Treasure Chest",0);
        p.setFortuneCard(f);
        String[] values = {"Skull","Parrot","Parrot","Parrot","Parrot","Parrot","Parrot","Gold"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1100,p.getScore());
    }

    public void testRow92(){
        Player p = new Player(1);
        Fortune f = new Fortune("Treasure Chest",0);
        p.setFortuneCard(f);
        String[] values = {"Skull","Skull","Gold","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        ArrayList<String> treasureChest = new ArrayList<>();
        treasureChest.add("Gold");
        treasureChest.add("Gold");
        treasureChest.add("Gold");
        treasureChest.add("Gold");
        p.setPlayerDices(dice);
        int treasureScore = game.treasureChestScoreCalculator(treasureChest);
        game.calculateDiceScore(p);
        p.setScore(p.getScore()+treasureScore);
        assertEquals(600,p.getScore());
    }
    public void testRow97(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Monkey","Monkey","Parrot","Sword","Sword","Sword","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(400,p.getScore());
    }
    public void testRow98(){
        Player p = new Player(1);
        Fortune f = new Fortune("Captain",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Monkey","Monkey","Sword","Sword","Sword","Gold","Gold"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1800,p.getScore());
    }
    public void testRow99(){
        Player p = new Player(1);
        Fortune f = new Fortune("Gold",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Monkey","Monkey","Sword","Sword","Sword","Sword","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1000,p.getScore());
    }
    public void testRow100(){
        Player p = new Player(1);
        Fortune f = new Fortune("Sea Battle",2);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Monkey","Monkey","Monkey","Gold","Sword","Sword","Gold"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        int score = game.seaBattleScore(p);
        p.setScore(Math.max(p.getScore() + score, 0));
        game.calculateDiceScore(p);
        assertEquals(1200,p.getScore());
    }
    public void testRow103(){
        Player p = new Player(1);
        Fortune f = new Fortune("Monkey Business",0);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Monkey","Parrot","Gold","Gold","Diamond","Diamond","Diamond"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(1200,p.getScore());
    }
    public void testRow106(){
        Player p = new Player(1);
        Fortune f = new Fortune("Skulls",2);
        p.setFortuneCard(f);
        String[] values = {"Skull","Sword","Sword","Sword","Sword","Sword","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(0,p.getScore());
    }
    public void testRow107(){
        Player p = new Player(1);
        Fortune f = new Fortune("Skulls",1);
        p.setFortuneCard(f);
        String[] values = {"Skull","Skull","Sword","Sword","Sword","Sword","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        game.calculateDiceScore(p);
        assertEquals(0,p.getScore());
    }
    public void testRow108(){
        Player p = new Player(1);
        Fortune f = new Fortune("Skulls",2);
        p.setFortuneCard(f);
        String[] values = {"Skull","Skull","Skull","Skull","Sword","Skull","Skull","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        int deduct = game.skullIslandDeduction(p,9);
        assertEquals(900,deduct);
    }
    public void testRow110(){
        Player p = new Player(1);
        Fortune f = new Fortune("Captain",0);
        p.setFortuneCard(f);
        String[] values = {"Skull","Skull","Skull","Skull","Coin","Skull","Skull","Skull"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        int deduct = game.skullIslandDeduction(p,7);
        assertEquals(1400,deduct);
    }
    public void testRow111(){
        Player p = new Player(1);
        Fortune f = new Fortune("Skulls",2);
        p.setFortuneCard(f);
        String[] values = {"Skull","Skull","Skull","Gold","Gold","Gold","Gold","Gold"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        int deduct = game.skullIslandDeduction(p,5);
        assertEquals(500,deduct);
    }
    public void testRow114(){
        Player p = new Player(1);
        Fortune f = new Fortune("Sea Battle",2);
        p.setFortuneCard(f);
        String[] values = {"Monkey","Skull","Skull","Monkey","Monkey","Monkey","Skull","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        int score = game.seaBattleScore(p);
        assertEquals(-300,score);
    }
    public void testRow115(){
        Player p = new Player(1);
        Fortune f = new Fortune("Sea Battle",3);
        p.setFortuneCard(f);
        String[] values = {"Sword","Skull","Skull","Skull","Skull","Skull","Skull","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        int score = game.seaBattleScore(p);
        assertEquals(-500,score);
    }
    public void testRow116(){
        Player p = new Player(1);
        Fortune f = new Fortune("Sea Battle",4);
        p.setFortuneCard(f);
        String[] values = {"Sword","Monkey","Monkey","Skull","Skull","Skull","Sword","Sword"};
        ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
        p.setPlayerDices(dice);
        int score = game.seaBattleScore(p);
        assertEquals(-1000,score);
    }

}
