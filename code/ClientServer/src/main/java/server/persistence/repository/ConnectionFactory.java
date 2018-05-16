package server.persistence.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectionFactory {

    private MongoClient mongoClient;


    public ConnectionFactory() {
        this.mongoClient = new MongoClient("localhost",27017);
    }


    public MongoDatabase getDatabase() {
        return mongoClient.getDatabase("articledb");
    }

    public MongoCollection getCollection(String name){
        MongoDatabase db = getDatabase();
        return db.getCollection(name);
    }
}
