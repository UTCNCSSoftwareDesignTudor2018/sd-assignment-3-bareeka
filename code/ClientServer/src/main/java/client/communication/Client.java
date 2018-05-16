package client.communication;

import client.GUI;

import javax.swing.*;
import java.io.IOException;

public class Client {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 1010;
    private Interpreter interpreter;
    private Handler handler;
    private GUI gui;

    public Client() throws IOException {
        this.gui = new GUI();
        this.interpreter = new Interpreter(gui);
        this.handler = new Handler(HOST,PORT,interpreter);
        handler.start();
    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
    }

}
