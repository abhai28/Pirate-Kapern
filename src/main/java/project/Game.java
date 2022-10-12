package project;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    ArrayList<Fortune> fortuneCards = new ArrayList<>();

    public void rollDice(Player p){

    }

    public void shuffleDeck(){
        Collections.shuffle(fortuneCards);
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
