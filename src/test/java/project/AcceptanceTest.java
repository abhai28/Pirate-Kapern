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

    
}
