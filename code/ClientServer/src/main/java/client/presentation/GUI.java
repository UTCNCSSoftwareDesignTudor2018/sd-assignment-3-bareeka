package client.presentation;

import client.communication.Interpreter;
import client.communication.Message;
import client.login.LoginDialog;
import server.persistence.entity.Article;

import java.awt.*;
import java.awt.event.*;
import java.io.Reader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame {
    private JButton readerButton;
    private JButton writerButton;
    private Interpreter interpreter;
    private ReaderUI rui;
    private WriterUI wrui;

    public GUI() {
        //construct components
        readerButton = new JButton ("Reader");
        writerButton = new JButton ("Writer");

        //adjust size and set layout
        setPreferredSize (new Dimension (318, 165));
        setLayout (null);

        //add components
        add (readerButton);
        add (writerButton);

        //set component bounds (only needed by Absolute Positioning)
        readerButton.setBounds (85, 30, 145, 40);
        writerButton.setBounds (85, 100, 145, 45);

        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible (true);

        readerButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                interpreter.requestArticles();
                GUI.this.rui = new ReaderUI();
            }
        });

        writerButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame frame = new JFrame("Login");
                LoginDialog loginDialog = new LoginDialog(frame);
                loginDialog.setVisible(true);
                if(loginDialog.isSucceeded()){
                    interpreter.requestArticles();
                    GUI.this.wrui = new WriterUI(interpreter);
                }
            }
        });
    }

    public void refreshArticles(ArrayList<Article> articles){
        if(rui != null) {
            rui.refreshArticles(articles);
        }
        if(wrui != null){
            wrui.refreshArticles(articles);
        }
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }
}
