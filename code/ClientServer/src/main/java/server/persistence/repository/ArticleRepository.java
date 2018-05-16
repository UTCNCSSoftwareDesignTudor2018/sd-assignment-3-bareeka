package server.persistence.repository;


import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import server.persistence.entity.Article;
import server.persistence.entity.Writer;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ArticleRepository {

    private Document getDoc(Article article){

        Document document = new Document("title",article.getTitle())
                .append("abstract",article.getArticleAbstract())
                .append("body",article.getBody())
                .append("writer",article.getWriter().getName());

        return document;
    }

    private Article getArticle(Document document){
        WriterRepository wr = new WriterRepository();
        Article article = new Article(
                document.getString("title"),
                document.getString("abstract"),
                document.getString("body"),
                wr.findByName(document.getString("writer"))
        );

        return article;
    }

    private MongoCollection<Document> getArticleCollection(){
        ConnectionFactory cf = new ConnectionFactory();
        return cf.getCollection("article");
    }



    public void addArticle(Article article){
        MongoCollection ac = getArticleCollection();
        ac.insertOne(getDoc(article));

    }

    public void deleteArticle(Article article){
        MongoCollection ac = getArticleCollection();
        ac.deleteOne(getDoc(article));
    }

    public ArrayList<Article>  findAll(){
        MongoCollection<Document> ac = getArticleCollection();
        ArrayList<Article> articles = new ArrayList<>();
        Block<Document> addArticles = new Block<Document>(){
            public void apply(final Document document){
                articles.add(getArticle(document));
            }
        };
        ac.find().forEach(addArticles);
        return articles;
    }

    public Article findByTitle(String title){
        MongoCollection<Document> ac = getArticleCollection();
        Document doc = ac.find(eq("title",title)).first();
        return getArticle(doc);
    }


}
