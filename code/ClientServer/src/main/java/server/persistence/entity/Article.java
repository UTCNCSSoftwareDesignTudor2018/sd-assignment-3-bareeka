package server.persistence.entity;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Objects;

public class Article implements Serializable {

    private ObjectId id;
    private String title;
    private String articleAbstract;
    private String body;
    private Writer writer;

    public Article(ObjectId id, String title, String articleAbstract, String body, Writer writer) {
        this.id = id;
        this.title = title;
        this.articleAbstract = articleAbstract;
        this.body = body;
        this.writer = writer;
    }

    public Article(String title, String articleAbstract, String body, Writer writer) {

        this.title = title;
        this.articleAbstract = articleAbstract;
        this.body = body;
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", articleAbstract='" + articleAbstract + '\'' +
                ", body='" + body + '\'' +
                ", writer=" + writer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
                Objects.equals(title, article.title) &&
                Objects.equals(articleAbstract, article.articleAbstract) &&
                Objects.equals(body, article.body) &&
                Objects.equals(writer, article.writer);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, articleAbstract, body, writer);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id){
        this.id = id;
    }
}
