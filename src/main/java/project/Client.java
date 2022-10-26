package project;

import java.io.*;
import java.net.Socket;
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
            System.out.println(scenario);
            System.out.println("Hello");
            switch (scenario) {
                case "Fortune" -> System.out.println(bufferedReader.readLine());
                case "Dice" -> {
                    System.out.println(bufferedReader.readLine());
                    String in = bufferedReader.readLine();
                    if (in.equals("Skull Island")) {
                        boolean done = false;
                        while (!done) {
                            String exit = bufferedReader.readLine();
                            if (exit.equals("done")) {
                                done = true;
                            } else {
                                System.out.println(exit);
                                System.out.println(bufferedReader.readLine());
                                String val = scanner.nextLine();
                                System.out.println(val);
                                writeToBuffer(val);
                                in = bufferedReader.readLine();
                                if (in.equals("pass")) {
                                    System.out.println(bufferedReader.readLine());
                                }
                            }
                        }
                    }
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