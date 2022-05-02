package com.company.models;

import org.bson.types.ObjectId;

public class Relation {

    public String idActorSQL;
    public String idMovieSQL;
    public ObjectId idActorMongo;
    public ObjectId idMovieMongo;
    public String date;

    public Relation(String idSqlActor, String idSqlMovie, String insertDate) {
        this.idActorSQL = idSqlActor;
        this.idMovieSQL = idSqlMovie;
        this.date = insertDate;
    }

    public String toStringSql() {
        return "Relation{" +
                "idActorSQL='" + idActorSQL + '\'' +
                ", idMovieSQL='" + idMovieSQL + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String toStringMongo() {
        return "Relation{" +
                "idActorMongo=" + idActorMongo +
                ", idMovieMongo=" + idMovieMongo +
                ", date='" + date + '\'' +
                '}';
    }
}
