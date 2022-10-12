package project;

import java.util.ArrayList;

public class Player {
    private String fortuneCard;
    private int playerID;
    private ArrayList<String> dice;
    private int score;

    public Player(){
        fortuneCard = "";
        playerID = 1;
        dice = new ArrayList<>();
        score = 0;
    }
    public Player(int id){
        fortuneCard = "";
        playerID = id;
        dice = new ArrayList<>();
        score = 0;
    }
    public void setFortuneCard(String f){
        fortuneCard = f;
    }
    public String getFortuneCard(){
        return fortuneCard;
    }
    public int getPlayerID(){
        return playerID;
    }
    public void setPlayerDice(ArrayList<String> d){
        dice = d;
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
