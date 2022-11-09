package project;

import java.util.ArrayList;

public class Player {
    private Fortune fortuneCard;
    private int playerID;
    private ArrayList<String> dice;
    private int score;
    private ArrayList<String> treasureChest;
    public Player(){
        fortuneCard = new Fortune();
        playerID = 1;
        dice = new ArrayList<>();
        treasureChest = new ArrayList<>();
        score = 0;
    }
    public Player(int id){
        fortuneCard = new Fortune();
        playerID = id;
        dice = new ArrayList<>();
        treasureChest = new ArrayList<>();
        score = 0;
    }
    public void addTreasure(int i){
        treasureChest.add(dice.remove(i));
    }
    public void removeTreasure(int i){
        dice.add(treasureChest.remove(i));
    }
    public ArrayList<String> getTreasureChest(){
        return treasureChest;
    }
    public void setFortuneCard(Fortune f){
        fortuneCard = f;
    }
    public Fortune getFortuneCard(){
        return fortuneCard;
    }
    public int getPlayerID(){
        return playerID;
    }
    public void setPlayerDices(ArrayList<String> d){
        dice = d;
    }
    public void setPlayerD(String v, int i){
        dice.set(i,v);
    }
    public ArrayList<String> getPlayerDice(){
        return dice;
    }
    public void setScore(int s){
        score = s;
    }
    public int getScore(){
        return score;
    }
}
