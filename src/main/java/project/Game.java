package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class Game {
    ArrayList<Fortune> fortuneCards = new ArrayList<>();
    ArrayList<Fortune> discardDeck = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();
    //main game function
    public void start(ArrayList<Socket> sockets, ArrayList<BufferedReader> bufferedReaders, ArrayList<BufferedWriter> bufferedWriters){
        populateDeck();
        shuffleDeck();
        for(int i=0; i<bufferedWriters.size();i++){
            writeToBuffer(bufferedWriters.get(i),"Hello player: "+players.get(i).getPlayerID());
        }
    }

    //multiplayer write function
    public void writeToBuffer(BufferedWriter buffer, String msg){
        try {
            buffer.write(msg);
            buffer.newLine();
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public int calculateDiceScore(ArrayList<String> d){
        int totalScore = 0;
        boolean notAll = true;
        HashMap<String, Integer> tally = new HashMap<String, Integer>();
        for(String v: d){
            tally.merge(v,1,Integer::sum);
        }
        if(tally.containsKey("Diamond")){
            totalScore+= 100*tally.get("Diamond");
        }
        if(tally.containsKey("Gold")){
            totalScore+= 100*tally.get("Gold");
        }

        for(String key: tally.keySet()){
            int num = tally.get(key);
            switch (num) {
                case 3 ->{
                    totalScore += 100;
                }
                case 4 -> totalScore += 200;
                case 5 -> totalScore += 500;
                case 6 -> totalScore += 1000;
                case 7 -> totalScore += 2000;
                case 8 -> {
                    totalScore += 4000;
                    notAll = false;
                }
                default -> notAll = true;
            }
        }
        if(!notAll){
            totalScore+=500;
        }
        return totalScore;
    }

    public void skullIsland(){

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
