package project;

public class Player {
    private String fortuneCard;
    private int playerID;
    private String[] dice;
    private int score;

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
    public int getPlayerID(){
        return playerID;
    }
}
