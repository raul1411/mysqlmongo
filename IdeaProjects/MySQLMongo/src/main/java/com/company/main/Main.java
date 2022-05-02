package com.company.main;

import com.company.databases.Database;
import com.company.databases.DatabaseMongo;
import com.company.databases.DatabaseMysql;
import com.company.models.Actor;
import com.company.models.Movie;
import com.company.models.Relation;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main implements Database {

    static Scanner sc = new Scanner(System.in);
    static Database db = null;
    static int action = 1;

    public static void main(String[] args) {
        Main main = new Main();

        while (action != 0) {

            if (Menu.dbMenu() == 1) {
                db = new DatabaseMysql();
            } else {
                db = new DatabaseMongo();
            }
            action = Menu.menu();

            switch (action) {
                case 1 -> {
                    System.out.println("FILM NAME [enter] FILM SYNOPSIS");
                    main.insertMovie(sc.next(), sc.next());
                }
                case 2 -> {
                    System.out.println("FILM NAME");
                    main.queryMovie(sc.next());
                }
                case 3 -> main.allMovies();
                case 4 -> {
                    System.out.println("FILM NAME");
                    main.deleteMovie(sc.next());
                }
                case 5 -> {
                    System.out.println("FILM NAME [enter] FILM NEW NAME [enter] FILM NEW SYNOPSIS");
                    main.updateMovie(sc.next(), sc.next(), sc.next());
                }
                case 6 -> {
                    System.out.println("ACTOR NAME [enter] ACTOR AGE");
                    main.insertActor(sc.next(), sc.nextInt());
                }
                case 7 -> {
                    System.out.println("ACTOR NAME");
                    main.queryActor(sc.next());
                }
                case 8 -> main.allActors();
                case 9 -> {
                    System.out.println("ACTOR NAME");
                    main.deleteActor(sc.next());
                }
                case 10 -> {
                    System.out.println("ACTOR NAME [enter] ACTOR NEW NAME [enter] ACTOR NEW AGE");
                    main.updateActor(sc.next(), sc.next(), sc.nextInt());
                }
                case 11 -> {
                    System.out.println("ACTOR_ID [enter] MOVIE_ID");
                    main.insertRelation(sc.nextInt(), sc.nextInt());
                }
                case 12 -> {
                    System.out.println("ACTOR_ID [enter] MOVIE_ID");
                    main.deleteRelation(sc.nextInt(), sc.nextInt());
                }
                case 13 -> {
                    System.out.println("ACTOR_ID [enter] MOVIE_ID");
                    main.queryRelation(sc.nextInt(), sc.nextInt()).toStringSql();
                }
                case 14 -> main.allRelations();
                case 0 -> System.out.println("exit");
            }
        }

    }

    @Override
    public void insertMovie(String title, String synopsis) {
        db.insertMovie(title, synopsis);
    }

    @Override
    public Movie queryMovie(String title) {
        return db.queryMovie(title);
    }

    @Override
    public Stream<Movie> allMovies() {
        return db.allMovies();
    }

    @Override
    public void deleteMovie(String title) {
        db.deleteMovie(title);
    }

    @Override
    public void updateMovie(String title, String newTitle, String synopsis) {
        db.updateMovie(title, newTitle, synopsis);
    }

    @Override
    public void insertActor(String name, int age) {
        db.insertActor(name, age);
    }

    @Override
    public Actor queryActor(String name) {
        return db.queryActor(name);
    }

    @Override
    public Stream<Actor> allActors() {
        return db.allActors();
    }

    @Override
    public void deleteActor(String name) {
        db.deleteActor(name);
    }

    @Override
    public void updateActor(String name, String newName, int age) {
        db.updateActor(name, newName, age);
    }

    @Override
    public void insertRelation(int id_actor, int id_movie) {
        db.insertRelation(id_actor, id_movie);
    }

    @Override
    public void deleteRelation(int id_actor, int id_movie) {
        db.deleteRelation(id_actor, id_movie);
    }

    @Override
    public Relation queryRelation(int id_actor, int id_movie) {
        return db.queryRelation(id_actor, id_movie);
    }

    @Override
    public Stream<Relation> allRelations() {
        return db.allRelations();
    }

}
