package com.example;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class App
{

    public static void main( String[] args )
    {
        App a = new App();
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        String s = "mongodb+srv://Bharath:bharath12345678@movie.jeait.mongodb.net/test?retryWrites=true&w=majority";
        try(MongoClient mongoClient = MongoClients.create(s))
        {
            /*printDatabases(mongoClient);
            insert(mongoClient);
            many(mongoClient);
            a.delete(mongoClient);
            update(mongoClient);*/
            a.printall(mongoClient);
            //finduser(mongoClient,s);
//            findadmin(mongoClient,s,s1);
        }

    }

    public FindIterable<Document> finduser(MongoClient mongoClient, String s) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        FindIterable<Document> res = record.find(new BasicDBObject("name",s));
        return res;
    }
    public FindIterable<Document> findlogin(MongoClient mongoClient, String s,String s1) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        FindIterable<Document> res = record.find(new BasicDBObject("name",s).append("password",s1));
        return res;
    }

    public void printall(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        Document doc = record.find(Filters.eq("name", "gokzi")).first();
        System.out.println(doc.getString("password"));
    }

    public void update(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        record.updateMany(new Document(), Updates.set("Value ",3+""));
    }

    public void delete(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        record.deleteMany(new Document());
    }

    public void many(MongoClient mongoClient) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        List<Document> doc = new ArrayList<>();
        for (int i=2;i<10;i++)
            doc.add(new Document("name",i+""));
        record.insertMany(doc);
    }

    public void insertadmin(MongoClient mongoClient,String s,String s2) {
        MongoCollection<Document> record = mongoClient.getDatabase("test").getCollection("person");
        Document doc = new Document("name",s)
                .append("password",s2);

        record.insertOne(doc);
    }

    public void printDatabases(MongoClient mongoClient) {
        List<Document> db = mongoClient.listDatabases().into(new ArrayList<>());
        db.forEach(document -> System.out.println(document.toJson()));
    }
}
