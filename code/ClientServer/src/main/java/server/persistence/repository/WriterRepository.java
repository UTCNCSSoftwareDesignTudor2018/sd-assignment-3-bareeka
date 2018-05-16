package server.persistence.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import server.persistence.entity.Article;
import server.persistence.entity.Writer;

import static com.mongodb.client.model.Filters.eq;

public class WriterRepository {

    private Document getDoc(Writer writer){

        Document document = new Document("name",writer.getName())
                .append("username",writer.getUsername())
                .append("password",writer.getPassword());
        return document;
    }

    private Writer getWriter(Document document) {
        Writer writer = new Writer(
                document.getString("name"),
                document.getString("username"),
                document.getString("password")
        );

        return writer;
    }


    public Writer findByName(String username) {
        MongoCollection<Document> writerCollection = getWriterCollection();
        Document doc = writerCollection.find(eq("name",username)).first();
        return getWriter(doc);

    }

    public void updateWriter(String name, Writer writer){
        MongoCollection<Document> col = getWriterCollection();
        col.updateOne(eq("name",name),getDoc(writer));
    }

    private MongoCollection<Document> getWriterCollection(){
        ConnectionFactory cf = new ConnectionFactory();
        return cf.getCollection("writer");
    }
}
