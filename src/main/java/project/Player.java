package project;

public class Player {
    String fortuneCard;
    int playerID;
    String[] dice;
    int score;
    public Player(){
        fortuneCard = "";
        playerID = 1;
        dice = new String[8];
        score = 0;
    }
    public Player(int id){
        fortuneCard = "";
        playerID = id;
        dice = new String[8];
        score = 0;
    }
    public void setFortuneCard(String f){
        fortuneCard = f;
    }
    public String getFortuneCard(){
        return fortuneCard;
    }
}
