package com.company.models;

import org.bson.types.ObjectId;

public class Actor {

    public String idSql;
    public ObjectId idMongo;
    public String name;
    public int age;

    public Actor(String idSql, String name, int age) {
        this.idSql = idSql;
        this.name = name;
        this.age = age;
    }

    public Actor(ObjectId idMongo, String name, int age) {
        this.idMongo = idMongo;
        this.name = name;
        this.age = age;
    }

    public String toStringSql() {
        return "Actor{" +
                "idSql='" + idSql + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String toStringMongo() {
        return "Actor{" +
                "idMongo=" + idMongo +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
