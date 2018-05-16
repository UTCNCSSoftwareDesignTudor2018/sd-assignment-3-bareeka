package client;

import client.communication.Interpreter;
import client.communication.Message;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JFrame {
    private JButton readerButton;
    private JButton writerButton;
    private Interpreter interpreter;

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

            }
        });
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }
}
