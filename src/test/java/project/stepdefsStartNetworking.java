package project;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import junit.framework.TestCase;

public class stepdefsStartNetworking extends TestCase{
    Server server;

    ArrayList<Socket> sockets = new ArrayList<>();
    ArrayList<BufferedReader> brs = new ArrayList<>();
    ArrayList<BufferedWriter> bws = new ArrayList<>();
    Thread t;
    @Given("Start server {string}")
    public void start_server(String rig){
        t = new Thread(()->{
            server = Server.test_start(new String[]{},rig);
        });
        t.start();
    }
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

    @And("Player get message {string}")
    public void playerGetMsgGameStart(String msg) {
        try{
            for(BufferedReader br : brs){
                assertEquals(msg,br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("Player get message {string} and {string}")
    public void playerGetMessageAnd(String msg1, String msg2) {
        try{
            for(BufferedReader br : brs){
                assertEquals(msg1,br.readLine());
                assertEquals(msg2,br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("Player {int} gets message {string} and {string}")
    public void playerGetsMessageAnd(int id, String msg1, String msg2) {
        try{
            assertEquals(msg1,brs.get(id-1).readLine());
            assertEquals(msg2,brs.get(id-1).readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("Player {int} writes back {string}")
    public void playerWritesBack(int id, String msg) {
        try{
            writeToBuffer(bws.get(id-1),msg);
            assertEquals("no",brs.get(id-1).readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //helper function
    public void writeToBuffer(BufferedWriter buffer, String msg){
        try {
            buffer.write(msg);
            buffer.newLine();
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("Close server")
    public void closeServer() {
        assertSame(t.getState(), Thread.State.TERMINATED);
    }
}
