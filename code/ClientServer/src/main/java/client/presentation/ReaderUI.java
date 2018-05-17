package client.presentation;

import server.persistence.entity.Article;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class ReaderUI extends JFrame {
    private JButton jcomp1;
    private JList articleList;
    private JTextArea textArea;
    DefaultListModel<String> model;
    ArrayList<Article> articles;

    public ReaderUI() {
        //construct preComponents
        super("Reader Interface");
        //construct components
        jcomp1 = new JButton ("Button 1");
        model = new DefaultListModel<>();
        articleList = new JList (model);
        articleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        //adjust size and set layout
        setPreferredSize (new Dimension (875, 514));
        setLayout (null);

        //add components
       // add (jcomp1);
        add (articleList);
        add (textArea);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (655, 375, 100, 20);
        articleList.setBounds (560, 55, 260, 255);
        textArea.setBounds (35, 55, 460, 375);

        MouseListener mouseListener = new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int index = articleList.getSelectedIndex();
                textArea.setText(articles.get(index).getBody());
            }
        };
        articleList.addMouseListener(mouseListener);

        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible (true);
    }

    public void refreshArticles(ArrayList<Article> articles){
        //articleList.removeAll();
       // System.out.println();
        System.out.println("refreshing");
        this.articles = articles;
        if(!articleList.isSelectionEmpty()){
            int index = articleList.getSelectedIndex();
            textArea.setText(articles.get(index).getBody());
        }else{
            textArea.setText(articles.get(0).getBody());
        }
        DefaultListModel model = new DefaultListModel();
        for(Article a: articles){
            model.addElement(a.getTitle());
        }
        articleList.setModel(model);
    }

}
