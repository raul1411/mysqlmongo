package com.company.databases;

import com.company.models.Actor;
import com.company.models.Movie;
import com.company.models.Relation;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;

public class DatabaseMongo implements Database {

    String uri = "mongodb://localhost";
    MongoDatabase mongoDatabase;

    public void insertMovie(String title, String synopsis) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("movies");
            Document d = new Document();
            d.append("title", title);
            d.append("synopsis", synopsis);
            collection.insertOne(d);
        }
    }

    @Override
    public void insertActor(String name, int age) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("actors");
            Document d = new Document();
            d.append("name", name);
            d.append("age", age);
            collection.insertOne(d);
        }
    }

    @Override
    public void insertRelation(int id_actor, int id_movie) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("actorsMovies");
            Document d = new Document();
            d.append("id_actor", id_actor);
            d.append("id_movie", id_movie);
            collection.insertOne(d);
        }
    }

    public Movie queryMovie(String title) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("movies");
            for (Document d : collection.find(eq("title", title))) {
                Movie movie = new Movie(d.getObjectId("_id"), d.getString("title"), d.getString("synopsis"));
                System.out.println(movie.toStringMongo());
                return movie;
            }
        }
        return null;
    }

    @Override
    public Actor queryActor(String name) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("actors");
            for (Document d : collection.find(eq("name", name))) {
                Actor actor = new Actor(d.getObjectId("_id"), d.getString("name"), d.getInteger("age"));
                System.out.println(actor.toStringMongo());
                return actor;
            }
        }
        return null;
    }

    @Override
    public Relation queryRelation(int id_actor, int id_movie) {
        //TODO
        List<Relation> relations = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> actorsMovies = mongoDatabase.getCollection("actorsMovies");
            for (Document d : actorsMovies.find()) {
                Relation relation = new Relation(d.getString("id"), d.getString("_id_movie"), d.getString("timeStamp"));
                relations.add(relation);
                System.out.println(relation.toStringMongo());
            }
        }
        return relations.get(0);
    }

    @Override
    public Stream<Movie> allMovies() {
        List<Movie> movies = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("movies");
            for (Document d : collection.find()) {
                Movie movie = new Movie(d.getObjectId("_id"), d.getString("title"), d.getString("synopsis"));
                movies.add(movie);
                System.out.println(movie.toStringMongo());
            }
        }
        return movies.stream();
    }

    @Override
    public Stream<Actor> allActors() {
        List<Actor> actors = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("actors");
            for (Document d : collection.find()) {
                Actor actor = new Actor(d.getObjectId("_id"), d.getString("name"), d.getInteger("age"));
                actors.add(actor);
                System.out.println(actor.toStringMongo());
            }
        }
        return actors.stream();
    }

    @Override
    public Stream<Relation> allRelations() {
        List<Relation> relations = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> actorsMovies = mongoDatabase.getCollection("actorsMovies");
            for (Document d : actorsMovies.find()) {
                Relation relation = new Relation(d.getString("id"), d.getString("_id_movie"), d.getString("timeStamp"));
                relations.add(relation);
                System.out.println(relation.toStringMongo());
            }
        }
        return relations.stream();
    }

    @Override
    public void deleteMovie(String title) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("movies");
            Document d = new Document();
            d.append("title", title);
            collection.deleteOne(d);
        }
    }

    @Override
    public void deleteActor(String name) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("actors");
            Document d = new Document();
            d.append("name", name);
            collection.deleteOne(d);
        }
    }

    @Override
    public void deleteRelation(int id_actor, int id_movie) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> actorsMovies = mongoDatabase.getCollection("actorsMovies");
            Document d = new Document();
            d.append("id_actor", id_actor);
            d.append("id_movie", id_movie);
            actorsMovies.deleteOne(d);
        }
    }

    @Override
    public void updateMovie(String title, String newTitle, String synopsis) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("movies");
            Document doc = new Document().append("title", title);
            Bson updates = Updates.combine(
                    Updates.set("title", newTitle),
                    Updates.set("synopsis", synopsis)
            );
            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = collection.updateOne(doc, updates, options);
            } catch (MongoException mongoException) {
                System.out.println("Unable to update due to an error: " + mongoException);
            }
        }
    }

    @Override
    public void updateActor(String name, String newName, int age) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            mongoDatabase = mongoClient.getDatabase("sampledb");
            MongoCollection<Document> collection = mongoDatabase.getCollection("actors");
            Document doc = new Document().append("name", name);
            Bson updates = Updates.combine(
                    Updates.set("name", newName),
                    Updates.set("age", age)
            );
            UpdateOptions options = new UpdateOptions().upsert(true);
            try {
                UpdateResult result = collection.updateOne(doc, updates, options);
            } catch (MongoException mongoException) {
                System.out.println("Unable to update due to an error: " + mongoException);
            }
        }
    }
}