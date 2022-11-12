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
    String gameRig = "";
    //main game function
    public void start(ArrayList<Socket> sockets, ArrayList<BufferedReader> bufferedReaders, ArrayList<BufferedWriter> bufferedWriters, String rig){
        gameRig =rig;
        try {
            populateDeck();

            shuffleDeck();
            for (BufferedWriter bufferedWriter : bufferedWriters) {
                writeToBuffer(bufferedWriter, "Game Has Begun");
            }
            for(Player p : players){
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Score");
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Score: "+p.getScore());
            }
            while (!determineWinners()) {
                if (fortuneCards.size() <= 0) {
                    populateDeck();
                    shuffleDeck();
                }
                for (Player p : players) {
                    drawFortuneCard(p);
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Fortune");
                    if(p.getFortuneCard().getAmount()>0){
                        writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Fortune Card: " + p.getFortuneCard().getName()+" "+p.getFortuneCard().getAmount());
                    }
                    else{
                        writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Fortune Card: " + p.getFortuneCard().getName());
                    }
                }
                for (Player p : players) {
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Dice");
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Your turn has started!");
                    if(p.getFortuneCard().getName().equals("Sea Battle")){
                        BufferedWriter bw = bufferedWriters.get(p.getPlayerID() - 1);
                        BufferedReader br = bufferedReaders.get(p.getPlayerID()-1);
                        writeToBuffer(bw,"Dice");
                        String out = "Since you have drawn a Sea Battle fortune card, now you are engaged in a sea battle. You are required to roll at least "+p.getFortuneCard().getAmount()+" swords to win!";
                        writeToBuffer(bw,out);
                        rollDice(p);
                        writeToBuffer(bw,"Dice");
                        writeToBuffer(bw,arrayDiceToString(p));
                        multiplayerRe(p,br,bw);
                        int score = seaBattleScore(p);
                        if(p.getScore()-score<0){
                            p.setScore(0);
                        }
                        else{
                            p.setScore(p.getScore()+score);
                        }
                        calculateDiceScore(p);
                    }
                    else {
                        rollDice(p);
                        if(p.getPlayerID()==1){
                            ArrayList<String> d = new ArrayList<>();
                            for(int i=0;i<8;i++){
                                d.add("Gold");
                            }
                            p.setPlayerDices(d);
                        }
                        writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Dice");
                        writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), arrayDiceToString(p));
                        if (skullIsland(p)) {
                            writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Skull Island");
                            int numSkulls = playSkullIsland(p, bufferedReaders.get(p.getPlayerID() - 1), bufferedWriters.get(p.getPlayerID() - 1));
                            int scoreDeduction = skullIslandDeduction(p, numSkulls);
                            for (int i = 0; i < players.size(); i++) {
                                if (i != p.getPlayerID() - 1) {
                                    players.get(i).setScore(players.get(i).getScore() - scoreDeduction);
                                    if (players.get(i).getScore() < 0) {
                                        players.get(i).setScore(0);
                                    }
                                    writeToBuffer(bufferedWriters.get(i), "Score");
                                    writeToBuffer(bufferedWriters.get(i), "Skull");
                                    writeToBuffer(bufferedWriters.get(i), "Player " + p.getPlayerID() + " entered skull island and due to this you have lost " + scoreDeduction + " points.");
                                    writeToBuffer(bufferedWriters.get(i), "Score: " + players.get(i).getScore());
                                }
                            }
                        } else {
                            BufferedWriter bw = bufferedWriters.get(p.getPlayerID() - 1);
                            BufferedReader br = bufferedReaders.get(p.getPlayerID() - 1);
                            if (p.getFortuneCard().getName().equals("Sorceress")) {
                                boolean hasSkull = false;
                                for (String d : p.getPlayerDice()) {
                                    if (d.equals("Skull")) {
                                        hasSkull = true;
                                        break;
                                    }
                                }
                                if (hasSkull) {
                                    writeToBuffer(bw, "Sorceress");
                                    writeToBuffer(bw, "Would you like to use your Sorceress card to reroll a skull? Yes or No");
                                    String ans = br.readLine();
                                    if (ans.equalsIgnoreCase("Yes")) {
                                        sorceressReroll(p);
                                        writeToBuffer(bw, "Dice");
                                        writeToBuffer(bw, arrayDiceToString(p));
                                    }
                                }
                            }
                            int numSkulls = 0;
                            for(String d : p.getPlayerDice()){
                                if(d.equals("Skull")){
                                    numSkulls++;
                                }
                            }
                            if(p.getFortuneCard().getName().equals("Skulls")){
                                numSkulls+=p.getFortuneCard().getAmount();
                            }
                            if(numSkulls>=3){
                                writeToBuffer(bw,"Dice");
                                if(p.getFortuneCard().getName().equals("Skulls")){
                                    numSkulls-= p.getFortuneCard().getAmount();
                                    writeToBuffer(bw,"You have rolled "+numSkulls+" skulls and plus "+p.getFortuneCard().getAmount()+" from your skulls fortune card" +" has disqualified you.");
                                }
                                else{
                                    writeToBuffer(bw,"You have rolled "+numSkulls+" which has disqualified you.");
                                }
                            }
                            else {
                                multiplayerRe(p,br,bw);
                                calculateDiceScore(p);
                            }
                        }
                    }
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Score");
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Score: "+p.getScore());
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Dice");
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1),"Your turn has ended!");
                }
            }
            Player winner = getWinner(players);
            lastTurn(bufferedReaders,bufferedWriters,winner);
            winner = getWinner(players);
            for(Player p: players){
                writeToBuffer(bufferedWriters.get(p.getPlayerID()-1),"Dice");
                String winMsg = "The winner is player " + winner.getPlayerID()+ " with score "+winner.getScore()+".";
                writeToBuffer(bufferedWriters.get(p.getPlayerID()-1),winMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sorceressReroll(Player p){
        for (int i = 0; i < p.getPlayerDice().size(); i++) {
            if (p.getPlayerDice().get(i).equals("Skull")) {
                reroll(p, i);
                break;
            }
        }
    }
    public void lastTurn(ArrayList<BufferedReader> bufferedReaders,ArrayList<BufferedWriter> bufferedWriters,Player winner) throws IOException {
        if (fortuneCards.size() <= 0) {
            populateDeck();
            shuffleDeck();
        }
        for (Player p : players) {
            if(p.getPlayerID()!=winner.getPlayerID()){
                drawFortuneCard(p);
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Fortune");
                if(p.getFortuneCard().getAmount()>0){
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Fortune Card: " + p.getFortuneCard().getName()+" "+p.getFortuneCard().getAmount());
                }
                else{
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Fortune Card: " + p.getFortuneCard().getName());
                }
            }
        }
        for (Player p : players) {
            if(p.getPlayerID()!=winner.getPlayerID()) {
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Dice");
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Your turn has started!");
                if (p.getFortuneCard().getName().equals("Sea Battle")) {
                    BufferedWriter bw = bufferedWriters.get(p.getPlayerID() - 1);
                    BufferedReader br = bufferedReaders.get(p.getPlayerID() - 1);
                    writeToBuffer(bw, "Dice");
                    String out = "Since you have drawn a Sea Battle fortune card, now you are engaged in a sea battle. You are required to roll at least " + p.getFortuneCard().getAmount() + " swords to win!";
                    writeToBuffer(bw, out);
                    rollDice(p);
                    writeToBuffer(bw, "Dice");
                    writeToBuffer(bw, arrayDiceToString(p));
                    multiplayerRe(p, br, bw);
                    int score = seaBattleScore(p);
                    if(p.getScore()-score<0){
                        p.setScore(0);
                    }
                    else{
                        p.setScore(p.getScore()+score);
                    }
                    calculateDiceScore(p);
                } else {
                    rollDice(p);
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Dice");
                    writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), arrayDiceToString(p));
                    if (skullIsland(p)) {
                        writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Skull Island");
                        int numSkulls = playSkullIsland(p, bufferedReaders.get(p.getPlayerID() - 1), bufferedWriters.get(p.getPlayerID() - 1));
                        int scoreDeduction = skullIslandDeduction(p, numSkulls);
                        for (int i = 0; i < players.size(); i++) {
                            if (i != p.getPlayerID() - 1) {
                                players.get(i).setScore(players.get(i).getScore() - scoreDeduction);
                                if (players.get(i).getScore() < 0) {
                                    players.get(i).setScore(0);
                                }
                                writeToBuffer(bufferedWriters.get(i), "Score");
                                writeToBuffer(bufferedWriters.get(i), "Skull");
                                writeToBuffer(bufferedWriters.get(i), "Player " + p.getPlayerID() + " entered skull island and due to this you have lost " + scoreDeduction + " points.");
                                writeToBuffer(bufferedWriters.get(i), "Score: " + players.get(i).getScore());
                            }
                        }
                    } else {
                        BufferedWriter bw = bufferedWriters.get(p.getPlayerID() - 1);
                        BufferedReader br = bufferedReaders.get(p.getPlayerID() - 1);
                        if (p.getFortuneCard().getName().equals("Sorceress")) {
                            boolean hasSkull = false;
                            for (String d : p.getPlayerDice()) {
                                if (d.equals("Skull")) {
                                    hasSkull = true;
                                    break;
                                }
                            }
                            if (hasSkull) {
                                writeToBuffer(bw, "Sorceress");
                                writeToBuffer(bw, "Would you like to use your Sorceress card to reroll a skull? Yes or No");
                                String ans = br.readLine();
                                if (ans.equalsIgnoreCase("Yes")) {
                                    for (int i = 0; i < p.getPlayerDice().size(); i++) {
                                        if (p.getPlayerDice().get(i).equals("Skull")) {
                                            reroll(p, i);
                                            break;
                                        }
                                    }
                                    writeToBuffer(bw, "Dice");
                                    writeToBuffer(bw, arrayDiceToString(p));
                                }
                            }
                        }
                        int numSkulls = 0;
                        for (String d : p.getPlayerDice()) {
                            if (d.equals("Skull")) {
                                numSkulls++;
                            }
                        }
                        if (p.getFortuneCard().getName().equals("Skulls")) {
                            numSkulls += p.getFortuneCard().getAmount();
                        }
                        if (numSkulls >= 3) {
                            writeToBuffer(bw, "Dice");
                            if (p.getFortuneCard().getName().equals("Skulls")) {
                                numSkulls -= p.getFortuneCard().getAmount();
                                writeToBuffer(bw, "You have rolled " + numSkulls + " skulls and plus " + p.getFortuneCard().getAmount() + " from your skulls fortune card" + " has disqualified you.");
                            } else {
                                writeToBuffer(bw, "You have rolled " + numSkulls + " which has disqualified you.");
                            }
                        } else {
                            multiplayerRe(p, br, bw);
                            calculateDiceScore(p);
                        }
                    }
                }
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Score");
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Score: " + p.getScore());
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Dice");
                writeToBuffer(bufferedWriters.get(p.getPlayerID() - 1), "Your turn has ended!");
            }
        }
    }

    public void treasureChest(Player p, BufferedWriter bw, BufferedReader br){
        try{
            writeToBuffer(bw, "Treasure Chest");
            if(p.getTreasureChest().size()==0){
                writeToBuffer(bw, "Since you have a treasure chest fortune card, would you like to place any dice in it? Yes or No");
            }
            else{
                writeToBuffer(bw, "Would you like to add or remove from your treasure chest. Enter add or remove");
            }
            String ans = br.readLine();
            if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("add")) {
                writeToBuffer(bw, "yes");
                addToTreasure(p,br,bw);
            }
            else if(ans.equalsIgnoreCase("remove")){
                int val = 1;
                while(val < p.getPlayerDice().size() + 1 && val > 0){
                    if(p.getTreasureChest().size()==0){
                        writeToBuffer(bw, "exit");
                        writeToBuffer(bw, "Your treasure Chest is empty");
                        val = 0;
                    }
                    else{
                        writeToBuffer(bw, "Enter number associated to dice or any other number to exit.");
                        String userIn = br.readLine();
                        val = Integer.parseInt(userIn);
                        if(val < p.getTreasureChest().size() + 1 && val > 0){
                            writeToBuffer(bw,"pass");
                            writeToBuffer(bw,arrayDiceToString(p));
                            writeToBuffer(bw,arrayTreasureChestToString(p.getTreasureChest()));
                        }
                        else{
                            writeToBuffer(bw,"exit");
                        }
                    }
                }
            }
            else {
                writeToBuffer(bw, "no");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToTreasure(Player p,BufferedReader br, BufferedWriter bw){
        try{
            int val = 1;

            while (val < p.getPlayerDice().size() + 1 && val > -1) {
                if(p.getPlayerDice().size()<=0){
                    writeToBuffer(bw, "exit");
                    System.out.println("Hello1");
                    val = -1;
                }
                else{
                    writeToBuffer(bw, "Enter number associated to dice or any other number to exit.");
                    String userIn = br.readLine();
                    val = Integer.parseInt(userIn);
                    if (val < p.getPlayerDice().size() + 1 && val > 0) {
                        if (p.getPlayerDice().get(val - 1).equals("Skull")) {
                            writeToBuffer(bw, "Skull");
                            writeToBuffer(bw, "You have chosen a skull and that cannot be put in treasure chest!");
                        } else {
                            p.addTreasure(val - 1);
                            writeToBuffer(bw, "pass");
                            writeToBuffer(bw, arrayDiceToString(p));
                            writeToBuffer(bw, arrayTreasureChestToString(p.getTreasureChest()));
                            val = 0;
                        }
                    } else {
                        writeToBuffer(bw, "exit");
                        System.out.println("Hello2");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Player getWinner(ArrayList<Player> p){
        Player pl = new Player(0);
        pl.setScore(0);
        for(Player p1 : p){
            if(pl.getScore()<p1.getScore()){
                pl = p1;
            }
        }
        return pl;
    }
    public int seaBattleScore(Player p){
        int score = 0;
        int numSwords = 0;
        int numSkulls = 0;
        for(String s : p.getPlayerDice()){
            if(s.equals("Sword")){
                numSwords++;
            }
        }
        for(String s : p.getPlayerDice()){
            if(s.equals("Skull")){
                numSkulls++;
            }
        }
        if(numSkulls>=3){
            numSwords = 0;
        }
        switch (p.getFortuneCard().getAmount()){
            case 2 ->{
                if(numSwords>=2){
                    score+=300;
                }
                else{
                    score-=300;
                }
            }
            case 3 ->{
                if(numSwords>=3){
                    score+=500;
                }
                else{
                    score-=500;
                }
            }
            case 4 ->{
                if(numSwords>=4){
                    score +=1000;
                }
                else{
                    score-=1000;
                }
            }
        }
        return score;
    }
    public int treasureChestScoreCalculator(ArrayList<String> t){
        int totalScore = 0;
        int totalDiceScored = 0;
        HashMap<String, Integer> tally = new HashMap<>();
        for(String v: t){
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
                    totalDiceScored+=3;
                }
                case 4 -> {
                    totalScore += 200;
                    totalDiceScored+=4;
                }
                case 5 -> {
                    totalScore += 500;
                    totalDiceScored +=5;
                }
                case 6 -> {
                    totalScore += 1000;
                    totalDiceScored+=6;
                }
                case 7 -> {
                    totalScore += 2000;
                    totalDiceScored+=7;
                }
                case 8 -> {
                    totalScore += 4000;
                    totalDiceScored+=8;
                }
            }
        }
        if(totalDiceScored==8){
            totalScore+=500;
        }
        return totalScore;
    }

    public void multiplayerRe(Player p, BufferedReader br, BufferedWriter bw) throws IOException {
        boolean cont = true;
        while(cont) {
            int numSkulls = 0;
            for (String d : p.getPlayerDice()) {
                if (d.equals("Skull")) {
                    numSkulls++;
                }
            }
            if(numSkulls>=3){
                writeToBuffer(bw,"Dice");
                writeToBuffer(bw,"You have rolled "+numSkulls+" skulls, this has disqualified you");
            }
            if(p.getFortuneCard().getName().equals("Treasure Chest")){
                treasureChest(p,bw,br);
            }
            if(p.getPlayerDice().size()>0){
                writeToBuffer(bw, "Reroll");
                writeToBuffer(bw, "Would you like to reroll the dices? Yes or No");
                String ans = br.readLine();
                if (ans.equalsIgnoreCase("yes")) {
                    writeToBuffer(bw, "Yes");
                    int val = 1;
                    ArrayList<Integer> values = new ArrayList<>();
                    while (val < p.getPlayerDice().size() + 1 && val > 0) {
                        writeToBuffer(bw, "Enter number associated to dice or any other number to exit.");
                        String userIn = br.readLine();
                        val = Integer.parseInt(userIn);
                        if (values.contains(val - 1)) {
                            writeToBuffer(bw, "fail");
                            writeToBuffer(bw, "You Entered a previously selected value");
                            val = 1;
                        } else if (val < p.getPlayerDice().size() + 1 && val > 0) {
                            numSkulls = 0;
                            for (String d : p.getPlayerDice()) {
                                if (d.equals("Skull")) {
                                    numSkulls++;
                                }
                            }
                            if (p.getFortuneCard().getName().equals("Skulls")) {
                                numSkulls += p.getFortuneCard().getAmount();
                            }
                            if (numSkulls >= 3) {
                                writeToBuffer(bw, "dq");
                                writeToBuffer(bw, "You have rolled " + numSkulls + " skulls, this disqualifies you!");
                                val = 9;
                                cont = false;
                            } else if (p.getPlayerDice().get(val - 1).equals("Skull")) {
                                writeToBuffer(bw, "Skull");
                                writeToBuffer(bw, "You have entered a skull which cannot be rerolled.");
                            } else {
                                writeToBuffer(bw, "pass");
                                values.add(val - 1);
                            }
                        } else {
                            if (values.size() < 2) {
                                writeToBuffer(bw, "fail");
                                writeToBuffer(bw, "You need to at least enter 2 dice to reroll");
                                val = 1;
                            } else {
                                writeToBuffer(bw, "exit");
                                val = 9;
                            }
                        }
                    }
                    multiplayerReroll(p, values);
                    writeToBuffer(bw, "Dice");
                    writeToBuffer(bw, arrayDiceToString(p));
                } else {
                    writeToBuffer(bw, "no");
                    cont = false;
                }
            }
            else{
                cont = false;
            }
        }
    }
    public void fixed_reroll(Player p, ArrayList<Integer> index, ArrayList<String> dice){
        int i =0;
        for(String d : dice){
            p.setPlayerD(d,index.get(i));
            i++;
        }
    }
    public String arrayTreasureChestToString(ArrayList<String> s){
        int m = 1;
        StringBuilder dice = new StringBuilder();
        dice.append("Treasure Chest: ");
        for (String d : s) {
            dice.append(m).append(": ").append(d).append(" ");
            m++;
        }
        return dice.toString();
    }
    public void multiplayerReroll(Player p, ArrayList<Integer> d){
        for(int m : d){
            reroll(p,m);
        }
    }

    public int skullIslandDeduction(Player p, int numSkulls){
        int score = numSkulls*100;
        if(p.getFortuneCard().getName().equals("Captain")){
            score = score *2;
        }
        return score;
    }
    public int playSkullIsland(Player p,BufferedReader br, BufferedWriter bw){
        try{
            int count = 0;
            for(String s : p.getPlayerDice()){
                if(s.equals("Skull")){
                    count ++;
                }
            }
            if(p.getFortuneCard().getName().equals("Skulls")){
                writeToBuffer(bw,"You have rolled "+ count +" skulls and plus "+ p.getFortuneCard().getAmount()+" skulls from your fortune card have entered The Island of Skulls.");
            }
            else{
                writeToBuffer(bw,"You have rolled "+ count +" skulls and have entered The Island of Skulls.");
            }
            boolean done = false;
            while(!done){
                writeToBuffer(bw,"continue");
                writeToBuffer(bw,"If you would like to reroll enter Yes or else enter anything.");
                String ans = br.readLine();
                int numSkulls = 0;
                if(ans.equalsIgnoreCase("yes")){
                    writeToBuffer(bw,"pass");
                    for(String s : p.getPlayerDice()){
                        if(s.equals("Skull")){
                            numSkulls++;
                        }
                    }
                    skullIslandReroll(p);
                    int newCount = 0;
                    for(String s : p.getPlayerDice()){
                        if(s.equals("Skull")){
                            newCount++;
                        }
                    }
                    writeToBuffer(bw,arrayDiceToString(p));
                    if(newCount==numSkulls){
                        writeToBuffer(bw,"no");
                        writeToBuffer(bw,"No no skulls rolled");
                        done = true;
                        writeToBuffer(bw,"done");
                    }
                    else{
                        writeToBuffer(bw,"yes");
                    }
                }
                else{
                    writeToBuffer(bw,"done");
                    done = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        int numSkulls = 0;
        for(String s : p.getPlayerDice()){
            if(s.equals("Skull")){
                numSkulls++;
            }
        }
        if(p.getFortuneCard().getName().equals("Skulls")){
            numSkulls += p.getFortuneCard().getAmount();
        }
        return numSkulls;
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
        String[] values = {"Monkey","Sword","Parrot","Skull","Gold","Diamond"};
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
        String[] values = {"Monkey","Sword","Parrot","Skull","Gold","Diamond"};
        ArrayList<String> faceValues = new ArrayList<>(Arrays.asList(values));
        Random rand = new Random();
        String v = faceValues.get(rand.nextInt(faceValues.size()));
        p.setPlayerD(v,diceNum);

    }
    public void calculateDiceScore(Player p){
        int totalScore = 0;
        int totalDiceScored = 0;
        HashMap<String, Integer> tally = new HashMap<>();
        for(String v: p.getPlayerDice()){
            tally.merge(v,1,Integer::sum);
        }
        if(p.getFortuneCard().getName().equals("Sea Battle")&&p.getFortuneCard().getAmount()==2){
            totalDiceScored+=2;
        }
        int fcSkulls = 0;
        if(p.getFortuneCard().getName().equals("Skulls")){
            fcSkulls = p.getFortuneCard().getAmount();
        }
        if(tally.containsKey("Skull")){
            if(tally.get("Skull")+fcSkulls<3){
                for(int i = p.getTreasureChest().size()-1;i>=0;i--){
                    p.removeTreasure(i);
                }
                tally = new HashMap<>();
                for(String v: p.getPlayerDice()){
                    tally.merge(v,1,Integer::sum);
                }
                if(tally.containsKey("Diamond")){
                    totalScore+= 100*tally.get("Diamond");
                    totalDiceScored+= tally.get("Diamond");
                }
                if(tally.containsKey("Gold")){
                    totalScore+= 100*tally.get("Gold");
                    totalDiceScored+= tally.get("Gold");
                }
                if(p.getFortuneCard().getName().equals("Gold")){
                    totalScore+=100;
                    if(tally.containsKey("Gold")){
                        tally.merge("Gold",1,Integer::sum);
                    }
                }
                if(p.getFortuneCard().getName().equals("Diamond")){
                    totalScore+=100;
                    if(tally.containsKey("Diamond")){
                        tally.merge("Diamond",1,Integer::sum);
                    }
                }
                if(p.getFortuneCard().getName().equals("Monkey Business")){
                    if(tally.containsKey("Monkey") && tally.containsKey("Parrot")){
                        tally.merge("Monkey",tally.get("Parrot"),Integer::sum);
                        tally.remove("Parrot");
                    }
                }

                for(String key: tally.keySet()){
                    int num = tally.get(key);
                    switch (num) {
                        case 3 ->{
                            totalScore += 100;
                            if(key.equals("Gold")||key.equals("Diamond")){
                                totalDiceScored-=3;
                            }
                            totalDiceScored+=3;
                        }
                        case 4 -> {
                            totalScore += 200;
                            if(key.equals("Gold")||key.equals("Diamond")){
                                totalDiceScored-=4;
                            }
                            totalDiceScored+=4;
                        }
                        case 5 -> {
                            totalScore += 500;
                            if(key.equals("Gold")||key.equals("Diamond")){
                                totalDiceScored-=5;
                            }
                            totalDiceScored +=5;
                        }
                        case 6 -> {
                            totalScore += 1000;
                            if(key.equals("Gold")||key.equals("Diamond")){
                                totalDiceScored-=6;
                            }
                            totalDiceScored+=6;
                        }
                        case 7 -> {
                            totalScore += 2000;
                            if(key.equals("Gold")||key.equals("Diamond")){
                                totalDiceScored-=7;
                            }
                            totalDiceScored+=7;
                        }
                        case 8, 9 -> {
                            totalScore += 4000;
                            if(key.equals("Gold")||key.equals("Diamond")){
                                totalDiceScored-=8;
                            }
                            totalDiceScored+=8;
                        }
                    }
                }
                if(p.getFortuneCard().getName().equals("Gold")){
                    for(String key: tally.keySet()){
                        if(key.equals("Gold")){
                            tally.put("Gold",tally.get("Gold")-1);
                        }
                    }
                }
                if(p.getFortuneCard().getName().equals("Diamond")){
                    for(String key: tally.keySet()){
                        if(key.equals("Diamond")){
                            tally.put("Diamond",tally.get("Diamond")-1);
                        }
                    }
                }

                if(totalDiceScored==8){
                    totalScore+=500;
                }
            }
            else{
                if(p.getFortuneCard().getName().equals("Treasure Chest")){
                    p.setScore(p.getScore()+treasureChestScoreCalculator(p.getTreasureChest()));
                }
            }
        }
        else{
            for(int i = p.getTreasureChest().size()-1;i>=0;i--){
                p.removeTreasure(i);
            }
            tally = new HashMap<>();
            for(String v: p.getPlayerDice()){
                tally.merge(v,1,Integer::sum);
            }
            if(tally.containsKey("Diamond")){
                totalScore+= 100*tally.get("Diamond");
                totalDiceScored+= tally.get("Diamond");
            }
            if(tally.containsKey("Gold")){
                totalScore+= 100*tally.get("Gold");
                totalDiceScored+= tally.get("Gold");
            }
            if(p.getFortuneCard().getName().equals("Gold")){
                totalScore+=100;
                if(tally.containsKey("Gold")){
                    tally.merge("Gold",1,Integer::sum);
                }
            }
            if(p.getFortuneCard().getName().equals("Diamond")){
                totalScore+=100;
                if(tally.containsKey("Diamond")){
                    tally.merge("Diamond",1,Integer::sum);
                }
            }
            if(p.getFortuneCard().getName().equals("Monkey Business")){
                if(tally.containsKey("Monkey") && tally.containsKey("Parrot")){
                    tally.merge("Monkey",tally.get("Parrot"),Integer::sum);
                    tally.remove("Parrot");
                }
            }

            for(String key: tally.keySet()){
                int num = tally.get(key);
                switch (num) {
                    case 3 ->{
                        totalScore += 100;
                        if(key.equals("Gold")||key.equals("Diamond")){
                            totalDiceScored-=3;
                        }
                        totalDiceScored+=3;
                    }
                    case 4 -> {
                        totalScore += 200;
                        if(key.equals("Gold")||key.equals("Diamond")){
                            totalDiceScored-=4;
                        }
                        totalDiceScored+=4;
                    }
                    case 5 -> {
                        totalScore += 500;
                        if(key.equals("Gold")||key.equals("Diamond")){
                            totalDiceScored-=5;
                        }
                        totalDiceScored +=5;
                    }
                    case 6 -> {
                        totalScore += 1000;
                        if(key.equals("Gold")||key.equals("Diamond")){
                            totalDiceScored-=6;
                        }
                        totalDiceScored+=6;
                    }
                    case 7 -> {
                        totalScore += 2000;
                        if(key.equals("Gold")||key.equals("Diamond")){
                            totalDiceScored-=7;
                        }
                        totalDiceScored+=7;
                    }
                    case 8, 9 -> {
                        totalScore += 4000;
                        if(key.equals("Gold")||key.equals("Diamond")){
                            totalDiceScored-=8;
                        }
                        totalDiceScored+=8;
                    }
                }
            }
            if(p.getFortuneCard().getName().equals("Gold")){
                for(String key: tally.keySet()){
                    if(key.equals("Gold")){
                        tally.put("Gold",tally.get("Gold")-1);
                    }
                }
            }
            if(p.getFortuneCard().getName().equals("Diamond")){
                for(String key: tally.keySet()){
                    if(key.equals("Diamond")){
                        tally.put("Diamond",tally.get("Diamond")-1);
                    }
                }
            }

            if(totalDiceScored==8){
                totalScore+=500;
            }
        }
        if(p.getFortuneCard().getName().equals("Captain")){
            p.setScore((totalScore*2)+p.getScore());
        }
        else{
            p.setScore(totalScore+p.getScore());
        }
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
        f = new Fortune("Gold",0);
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
        f = new Fortune("Sea Battle",3);
        for(int i=0;i<2;i++){
            fortuneCards.add(f);
        }
        f = new Fortune("Sea Battle",4);
        for(int i=0;i<2;i++){
            fortuneCards.add(f);
        }
    }
}
