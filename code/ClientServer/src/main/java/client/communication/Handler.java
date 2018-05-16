package client.communication;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler extends Thread {

    private final PrintWriter out;
    private final BufferedReader in;
    private final Interpreter interpreter;
    private final Socket serverSocket;
    private final Gson gson = new Gson();
    private Thread listener;

    public Handler(String host, int port, Interpreter i) throws IOException{
        this.interpreter = i;
        serverSocket = new Socket(host,port);
        out = new PrintWriter(serverSocket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        interpreter.setHandler(this);
    }

    public void sendMessage(Message message){
        out.println(gson.toJson(message));
    }

    public void disconnect(){
        try{
            this.interrupt();
            in.close();
            out.close();
            serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        String msg;

        try{
            while((msg = in.readLine())!=null){
                interpreter.process(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
