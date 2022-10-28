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

    
}
