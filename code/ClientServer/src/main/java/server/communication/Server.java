package server.communication;

import server.communication.Handler;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private static final int PORT = 1010;
    private final ServerSocket serverSocket;
    private static int clientNoS = 0;
    private int clientNo = clientNoS++;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        System.out.println("Server is listening...");
    }

    public void listen(){
        try{
            while(true){
                Handler h = new Handler(serverSocket.accept(),clientNo);
                h.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
