package com.company.databases;

import com.company.models.Actor;
import com.company.models.Movie;
import com.company.models.Relation;

import java.util.stream.Stream;

public interface Database {

    void insertMovie(String title, String synopsis);
    Movie queryMovie(String title);
    Stream<Movie> allMovies();
    void deleteMovie(String title);
    void updateMovie(String title, String newTitle, String synopsis);

    void insertActor(String name, int age);
    Actor queryActor(String name);
    Stream<Actor> allActors();
    void deleteActor(String name);
    void updateActor(String name, String newName, int age);

    void insertRelation(int id_actor, int id_movie);
    void deleteRelation(int id_actor, int id_movie);
    Relation queryRelation(int id_actor, int id_movie);
    Stream<Relation> allRelations();
}


