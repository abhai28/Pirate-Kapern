package project;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Socket socket;
    private ObjectInputStream dIn;
    private ObjectOutputStream dOut;

    public Client() {
        try {
            socket = new Socket("localhost", Config.GAME_SERVER_PORT_NUMBER);
            dOut = new ObjectOutputStream(socket.getOutputStream());
            dIn = new ObjectInputStream(socket.getInputStream());

            int playerId = dIn.readInt();

            System.out.println("Connected as " + playerId);


        } catch (IOException ex) {
            System.out.println("Client failed to open");
        }
    }

    public static void main(String args[]) throws Exception {
        Client c = new Client();
    }
}