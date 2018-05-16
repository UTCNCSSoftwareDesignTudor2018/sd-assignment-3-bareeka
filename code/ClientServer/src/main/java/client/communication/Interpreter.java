package client.communication;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.GUI;
import com.google.gson.Gson;
import server.persistence.entity.Article;


public class Interpreter {

    private static final String ALL_ARTICLES="findall_articles";
    private static final String RECEIVE_ARTICLES ="receive_articles";
    private static final String REQUEST_LOGIN ="login";
    private static final String SEND_LOGIN_TYPE_WRITER ="login_successful_writer";
    private static final String SEND_LOGIN_FAILED ="login_failed";

    private static final String CREATE_ARTICLE ="create_article";
    private static final String EDIT_ARTICLE ="edit_article";
    private static final String REQUEST_WRITERS="request_writers";
    private Handler handler;
    private Writer writer = null;
    private Gson gson;
    private final GUI gui;


    public Interpreter(GUI gui){
        this.gson = new Gson();
        this.gui = gui;
        gui.setInterpreter(this);

    }

    public void sendMessage(Message message){
        handler.sendMessage(message);
    }

    public void requestArticles(){
        sendMessage(new Message(ALL_ARTICLES,null));
    }

    void setHandler(Handler handler){
        this.handler = handler;
    }

    public void process(String msg){

        Message message = gson.fromJson(msg,Message.class);
        String command = message.getName();

        if(command.equals(ALL_ARTICLES)){
            ArrayList<Article> articles = (ArrayList<Article>) deserializeObject(message.getMsg());
            for(Article a: articles){
                System.out.println(a.getTitle());
            }
            //gui.refreshArticles(articles);
        }

        // messages to process
    }

    public static byte[] serializeObject(Serializable o) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] yourBytes = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(o);
            out.flush();
            yourBytes = bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
            return yourBytes;
        }
    }

    public static Object deserializeObject(byte[] bytesToDeserialize){
        ByteArrayInputStream bis = new ByteArrayInputStream(bytesToDeserialize);
        ObjectInput in = null;
        Object o = null;
        try {
            in = new ObjectInputStream(bis);
            o = in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
            return o;
        }

    }





}
