package server;

import client.communication.Interpreter;
import javafx.beans.InvalidationListener;
import server.business.ArticleBLL;
import server.business.WriterBLL;
import server.persistence.entity.Article;
import server.persistence.entity.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable {

    private static final Controller singleInstance = new Controller();
    private final ArticleBLL articleBLL;
    private final WriterBLL writerBLL;

    private Controller(){
        this.articleBLL = ArticleBLL.getSingleInstance();
        this.writerBLL = WriterBLL.getSingleInstance();

    }

    //Article control
    public void addArticle(Article a){
        articleBLL.addArticle(a);
        this.notifyObservers();
    }

    public void deleteArticle(Article a){
        articleBLL.deleteArticle(a);
        this.notifyObservers();
    }

    public  ArrayList<Article> findAllArticles(){
        return articleBLL.findAll();
    }

    public  Article findByTitle(String title){
        return articleBLL.findByTitle(title);
    }

    //Writer Control

    public  void updateWriter(String name, Writer w){
        writerBLL.updateWriter(name, w);
    }

    public  Writer findByUsername(String username){
        return writerBLL.findByUsername(username);
    }

    public static Controller getController(){
        return singleInstance;
    }

    public void updateArticle(Article article){
        articleBLL.updateArticle(article);
        this.notifyObservers();
    }

    public void addObserver(Observer o){
        // Calls the method addObserver of the class Observable
        super.addObserver(o);
        System.out.println("New observer added!");
    }







}
