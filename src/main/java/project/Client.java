package project;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private Scanner scanner;
    public Client() {
        try {
            scanner = new Scanner(System.in);
            socket = new Socket("localhost", Config.GAME_SERVER_PORT_NUMBER);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        } catch (IOException ex) {
            System.out.println("Client failed to open");
        }
    }

    private void closeConnection(){
        try {
            bufferedReader.close();
            socket.close();
            bufferedWriter.close();
            scanner.close();
            System.out.println("Client connection closed.");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void playGame() throws IOException {
        System.out.println(bufferedReader.readLine());
        while(socket.isConnected()){
            String scenario = bufferedReader.readLine();
            switch (scenario) {
                case "Fortune" -> System.out.println(bufferedReader.readLine());
                case "Dice" -> {
                    System.out.println(bufferedReader.readLine());
                }
                case "Skull Island"->{
                    boolean done = false;
                    System.out.println(bufferedReader.readLine());
                    while (!done) {
                        String exit = bufferedReader.readLine();
                        if (exit.equals("done")) {
                            done = true;
                        } else {
                            System.out.println(bufferedReader.readLine());
                            String val = scanner.nextLine();
                            System.out.println(val);
                            writeToBuffer(val);
                            String in = bufferedReader.readLine();
                            if (in.equals("pass")) {
                                System.out.println(bufferedReader.readLine());
                                String m = bufferedReader.readLine();
                                if(m.equals("no")){
                                    System.out.println(bufferedReader.readLine());
                                    done = true;
                                }
                            }
                            else{
                                done = true;
                            }
                        }
                    }
                }
                case "Reroll" -> {
                    System.out.println(bufferedReader.readLine());
                    String ans = scanner.nextLine();
                    writeToBuffer(ans);
                    String servReply = bufferedReader.readLine();
                    if(servReply.equalsIgnoreCase("Yes")){
                        int val = 1;
                        while(val<9 &&val>0){
                            System.out.println(bufferedReader.readLine());
                            String userIn = scanner.nextLine();
                            val = Integer.parseInt(userIn);
                            writeToBuffer(userIn);
                            String servRes = bufferedReader.readLine();
                            if(servRes.equals("Skull")){
                                System.out.println(bufferedReader.readLine());
                            }
                            else if(servRes.equals("dq")){
                                System.out.println(bufferedReader.readLine());
                                val = 9;
                            }
                            else if(servRes.equals("fail")){
                                System.out.println(bufferedReader.readLine());
                                val = 1;
                            }
                        }
                    }
                }
                case"Score"->{
                    String in = bufferedReader.readLine();
                    if(in.equals("Skull")){
                        System.out.println(bufferedReader.readLine());
                        System.out.println(bufferedReader.readLine());
                    }
                    else{
                        System.out.println(in);
                    }
                }
                case"Treasure Chest" ->{
                    System.out.println(bufferedReader.readLine());
                    String ans = scanner.nextLine();
                    writeToBuffer(ans);
                    String servRes = bufferedReader.readLine();
                    if(servRes.equals("yes")){
                        int val = 1;
                        while(val<9 && val>0){
                            System.out.println(bufferedReader.readLine());
                            String userAns = scanner.nextLine();
                            writeToBuffer(userAns);
                            val = Integer.parseInt(userAns);
                            String cond = bufferedReader.readLine();
                            if(cond.equals("Skull")){
                                System.out.println(bufferedReader.readLine());
                            }
                            else{

                            }
                        }
                    }
                }
                case"Sorceress"->{
                    System.out.println(bufferedReader.readLine());
                    String ans = scanner.nextLine();
                    writeToBuffer(ans);
                }
                default -> System.out.println("Game Over");
            }
        }
    }

    public void writeToBuffer(String msg){
        try {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        Client c = new Client();
        c.playGame();
        c.closeConnection();
    }
}