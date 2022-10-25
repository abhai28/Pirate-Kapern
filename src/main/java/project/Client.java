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
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());

            break;
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