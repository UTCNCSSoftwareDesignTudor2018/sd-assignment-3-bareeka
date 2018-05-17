package client.presentation;

import client.communication.Interpreter;
import server.persistence.entity.Article;
import server.persistence.entity.Writer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class WriterUI extends JFrame {
    private JButton updateButton;
    private JButton createButton;
    private JButton deleteButton;
    private JList articleList;
    private JTextArea textArea;
    DefaultListModel<String> model;
    ArrayList<Article> articles;
    private Interpreter interpreter;
    private JTextField titleField;
    private JTextField abstractField;
    private Writer defaultWriter;

    public WriterUI(Interpreter interpreter) {
        //construct preComponents
        super("Writer Interface");
        //construct components
        updateButton = new JButton ("Update");
        createButton = new JButton ("Create");
        deleteButton = new JButton ("Delete");
        model = new DefaultListModel<>();
        articleList = new JList (model);
        this.interpreter = interpreter;
        articleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        titleField = new JTextField (5);
        abstractField = new JTextField (5);
        defaultWriter = new Writer("George R.R. Martin","Georgie","starkpowa88");

        //adjust size and set layout
        setPreferredSize (new Dimension (875, 514));
        setLayout (null);

        //add components
        add (updateButton);
        add (articleList);
        add (textArea);
        add (titleField);
        add (abstractField);
        add (createButton);
        add (deleteButton);

        //set component bounds (only needed by Absolute Positioning)
        titleField.setBounds (600, 280, 245, 25);
        abstractField.setBounds (600, 310, 245, 25);
        updateButton.setBounds (655, 345, 100, 20);
        createButton.setBounds (655, 375, 100, 20);
        deleteButton.setBounds (655, 405, 100, 20);
        articleList.setBounds (560, 55, 260, 150);
        textArea.setBounds (35, 55, 460, 375);

        updateButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Updating article...");
                int indx = articleList.getSelectedIndex();
                Article article = articles.get(indx);
                article.setBody(textArea.getText());
                updateArticle(article);

            }
        });

        createButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creating article...");
               Article article = new Article(
                       titleField.getText(),
                       abstractField.getText(),
                       textArea.getText(),
                        defaultWriter
               );
                createArticle(article);

            }
        });

        deleteButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int indx = articleList.getSelectedIndex();
                Article article = articles.get(indx);
                deleteArticle(article);

            }
        });


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

    public void updateArticle(Article article){
        interpreter.updateArticle(article);

    }

    public void createArticle(Article article){
        interpreter.createArticle(article);

    }

    public void deleteArticle(Article article){
        interpreter.deleteArticle(article);

    }

    public void refreshArticles(ArrayList<Article> articles){
        System.out.println("Articles updated!");
        this.articles = articles;
        if(!articleList.isSelectionEmpty()){
            int index = articleList.getSelectedIndex();
            textArea.setText(articles.get(index).getBody());
        }else{
            textArea.setText(articles.get(0).getBody());
        }
        DefaultListModel model = new DefaultListModel();
        for(Article a: articles){
            model.addElement(a.getTitle()+ "-" + a.getWriter().getName());
        }
        articleList.setModel(model);
    }

}
