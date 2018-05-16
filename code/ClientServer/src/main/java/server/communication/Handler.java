package server.communication;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler extends Thread {

    private Thread listener;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Interpreter interpreter;
    private int clientNo;
    private Gson gson = new Gson();

    public Handler(Socket socket, int clientNo) throws IOException{
        this.socket = socket;
        this.clientNo = clientNo;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        this.interpreter = new Interpreter();
        interpreter.setHandler(this);
        System.out.println("Handling message...");
    }

    public void sendMessage(Message message){
        out.println(gson.toJson(message));
    }






    public void run(){
        String msg;

        try{
            while((msg = in.readLine())!=null){
                interpreter.process(msg);
            }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                System.out.println(Handler.this.clientNo + " Disconnected");
                out.close();
                try{
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
                try {
                    interpreter.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


}
