package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game {
    ArrayList<Fortune> fortuneCards = new ArrayList<>();
    ArrayList<Fortune> discardDeck = new ArrayList<>();
    public void rollDice(Player p){
        String[] values = {"Monkey","Sword","Parrot","Skull","Gold Coin","Diamond"};
        ArrayList<String> faceValues = new ArrayList<>(Arrays.asList(values));
        ArrayList<String> playerDice = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<8;i++){
            playerDice.add(faceValues.get(rand.nextInt(faceValues.size())));
        }
        p.setPlayerDices(playerDice);
    }
    public void drawFortuneCard(Player p){
        p.setFortuneCard(fortuneCards.remove(0));
    }
    public void shuffleDeck(){
        Collections.shuffle(fortuneCards);
    }
    public void reroll(Player p, int diceNum){
        String[] values = {"Monkey","Sword","Parrot","Skull","Gold Coin","Diamond"};
        ArrayList<String> faceValues = new ArrayList<>(Arrays.asList(values));
        Random rand = new Random();
        p.setPlayerD(faceValues.get(rand.nextInt(faceValues.size())),diceNum);

    }
    public void populateDeck(){
        Fortune f;
        f = new Fortune("Treasure Chest",0);
        for(int i=0;i<4;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Sorceress",0);
        for(int i=0;i<4;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Captain",0);
        for(int i=0;i<4;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Monkey Business",0);
        for(int i=0;i<4;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Diamond",0);
        for(int i=0;i<4;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Coin",0);
        for(int i=0;i<4;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Skulls",2);
        for(int i=0;i<2;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Skulls",1);
        for(int i=0;i<3;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Sea Battle",2);
        for(int i=0;i<2;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Sorceress",3);
        for(int i=0;i<2;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Sorceress",4);
        for(int i=0;i<2;i++){
            fortuneCards.add(f);
        }
    }
}
