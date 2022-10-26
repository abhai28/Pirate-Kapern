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
        for (BufferedWriter bufferedWriter : bufferedWriters) {
            writeToBuffer(bufferedWriter, "Game Has Begun");
        }
        while (!determineWinners()) {
            if (fortuneCards.size() <= 0) {
                populateDeck();
                shuffleDeck();
            }
            for (Player p : players) {
                drawFortuneCard(p);
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Fortune");
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Fortune Card: " + p.getFortuneCard().getName());
            }
            for (Player p : players) {
                rollDice(p);
                String[] values = {"Skull","Monkey","Monkey","Skull","Parrot","Skull","Gold","Skull"};
                ArrayList<String> dice = new ArrayList<>(Arrays.asList(values));
                p.setPlayerDices(dice);
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Dice");
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), arrayDiceToString(p));

                if(skullIsland(p)){
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Skull Island");
                    playSkullIsland(p,bufferedReaders.get(p.getPlayerID() - 1),bufferedWriters.get(p.getPlayerID() - 1));
                }
                else{
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"no skull");
                }
            }
            break;
        }
    }

    public void playSkullIsland(Player p,BufferedReader br, BufferedWriter bw){
        try{
            int count = 0;
            for(String s : p.getPlayerDice()){
                if(s.equals("Skull")){
                    count ++;
                }
            }
            writeToBuffer(bw,"You have rolled "+ count +" skulls and have entered The Island of Skulls.");
            boolean done = false;
            while(!done){
                writeToBuffer(bw,"If you would like to reroll enter Yes or else enter anything.");
                String ans = br.readLine();
                if(ans.equalsIgnoreCase("yes")){
                    writeToBuffer(bw,"pass");

                }
                else{
                    writeToBuffer(bw,"fail");
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String arrayDiceToString(Player p){
        int m = 1;
        StringBuilder dice = new StringBuilder();
        for (String d : p.getPlayerDice()) {
            dice.append(m).append(": ").append(d).append(" ");
            m++;
        }
        return dice.toString();
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

    public boolean determineWinners(){
        for(Player p : players){
            if(p.getScore()>=3000){
                return true;
            }
        }
        return false;
    }

    public void skullIslandReroll(Player p){
        for(int i=0;i<p.getPlayerDice().size();i++){
            if(!p.getPlayerDice().get(i).equals("Skull")){
                reroll(p,i);
            }
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

    public boolean skullIsland(Player p){
        int count = 0;
        if(p.getFortuneCard().getName().equals("Skulls")){
            count = p.getFortuneCard().getAmount();
        }
        for(String s : p.getPlayerDice()){
            if(s.equals("Skull")){
                count ++;
            }
        }
        return count >= 4;
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
