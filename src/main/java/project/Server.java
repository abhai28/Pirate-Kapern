package project;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private final ArrayList<Socket> sockets = new ArrayList<>();
    private final ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();
    private final ArrayList<BufferedWriter> bufferedWriters = new ArrayList<>();
    private Game game;

    private void initialize() {
        try {
            serverSocket = new ServerSocket(Config.GAME_SERVER_PORT_NUMBER);
            game = new Game();


            System.out.println("Server initialized. Waiting for players...");
            int i = 0;
            while(i<3) {
                Socket s = serverSocket.accept();
                sockets.add(s);
                try {
                    System.out.println("Hello");
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    bufferedWriters.add(bufferedWriter);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    bufferedReaders.add(bufferedReader);
                    Player newPlayer = new Player(i);
                    game.players.add(newPlayer);
                    i++;
                } catch(IOException ex){
                    System.out.println("Server Connection Failed");
                }
                System.out.println("Player " + i + " has joined!");
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    private void startGame(){
        game.start(sockets,bufferedReaders,bufferedWriters);
    }

    private void closeConnection() {
        try {
            serverSocket.close();
            for(int i = 0; i < sockets.size(); i++) {
                sockets.get(i).close();
                bufferedReaders.get(i).close();
                bufferedWriters.get(i).close();
            }
            System.out.println("Server connection closed.");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        server.initialize();
        server.startGame();
        server.closeConnection();
    }
}


