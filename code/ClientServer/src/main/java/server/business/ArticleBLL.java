package server.business;

import server.persistence.entity.Article;
import server.persistence.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

public class ArticleBLL {

    private static ArticleRepository ar = new ArticleRepository();
    private static ArticleBLL singleInstance = new ArticleBLL();

    private ArticleBLL() {
    }

    public static ArticleBLL getSingleInstance(){
        return singleInstance;
    }

    public synchronized void addArticle(Article a){
        ar.addArticle(a);
    }

    public synchronized void deleteArticle(Article a){
        ar.deleteArticle(a);
    }

    public synchronized ArrayList<Article> findAll(){
        return ar.findAll();
    }

    public synchronized Article findByTitle(String title){
        return ar.findByTitle(title);
    }


}
