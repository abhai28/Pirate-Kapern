package project;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.TestCase;

public class stepdefsNetworking extends TestCase{
    Server server = new Server();

    ArrayList<Socket> sockets = new ArrayList<>();
    ArrayList<BufferedReader> brs = new ArrayList<>();
    ArrayList<BufferedWriter> bws = new ArrayList<>();

    @And("Players are connected")
    public void connect_players(){
        try{
            Socket s1 = new Socket("localhost",Config.GAME_SERVER_PORT_NUMBER);
            Socket s2 = new Socket("localhost",Config.GAME_SERVER_PORT_NUMBER);
            Socket s3 = new Socket("localhost",Config.GAME_SERVER_PORT_NUMBER);
            sockets.add(s1);
            sockets.add(s2);
            sockets.add(s3);
            BufferedReader br1 = new BufferedReader(new InputStreamReader(sockets.get(0).getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(sockets.get(1).getInputStream()));
            BufferedReader br3 = new BufferedReader(new InputStreamReader(sockets.get(2).getInputStream()));
            brs.add(br1);
            brs.add(br2);
            brs.add(br3);
            BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(sockets.get(0).getOutputStream()));
            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(sockets.get(1).getOutputStream()));
            BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(sockets.get(2).getOutputStream()));
            bws.add(bw1);
            bws.add(bw2);
            bws.add(bw3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
