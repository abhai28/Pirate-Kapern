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
            //int playerId = dIn.readInt();
            String test = (String)bufferedReader.readLine();
            //System.out.println("Connected as " + playerId);
            System.out.println(test);


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

    public void playGame(){

    }
    public static void main(String args[]) throws Exception {
        Client c = new Client();
        c.playGame();
        c.closeConnection();
    }
}