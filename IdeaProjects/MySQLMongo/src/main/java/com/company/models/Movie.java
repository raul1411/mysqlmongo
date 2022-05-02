package com.company.models;

import org.bson.types.ObjectId;

public class Movie {

    public String idSql;
    public ObjectId idMongo;
    public String title;
    public String synopsis;

    public Movie(String idSql, String title, String synopsis) {
        this.idSql = idSql;
        this.title = title;
        this.synopsis = synopsis;
    }

    public Movie(ObjectId idMongo, String title, String synopsis) {
        this.idMongo = idMongo;
        this.title = title;
        this.synopsis = synopsis;
    }

    public String toStringSql() {
        return "Movie{" +
                "idSql='" + idSql + '\'' +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

    public String toStringMongo() {
        return "Movie{" +
                "idMongo=" + idMongo +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
