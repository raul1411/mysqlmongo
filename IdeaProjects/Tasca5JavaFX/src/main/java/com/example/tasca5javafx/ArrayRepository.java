package com.example.tasca5javafx;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayRepository {
    static int actorId = 0, movieId = 0;

    List<Actor> actorList = new ArrayList<>();
    List<Movie> movieList = new ArrayList<>();

    Stream<Actor> createActor(Actor actor) {
        actor.actorid = ++actorId;
        actorList.add(actor);
        return Stream.of(actor);
    }

    Stream<Actor> readActor(Integer actorid) {
        return actorList.stream()
                .filter(p -> p.actorid.equals(actorid))
                .peek(p -> p.movies = readAllMoviesByActorid(p.actorid))
                .limit(1);
    }

    Stream<Actor> readAllActors(){
        return actorList.stream()
                .peek(p -> p.movies = readAllMoviesByActorid(p.actorid));
    }

    void updatePerson(Actor actor){
        actorList.stream()
                .filter(p -> p.actorid.equals(actor.actorid))
                .findFirst().ifPresent(p -> p.name = actor.name);
    }

    void deletePerson(Integer actorid) {
        actorList.removeIf(p -> p.actorid.equals(actorid));
        movieList.removeIf(t -> t.actorid.equals(movieId));
    }

    Stream<Movie> createMovie(Movie movie) {
        movie.movieid = ++movieId;
        movie.dateTime = LocalDateTime.now();
        movieList.add(movie);
        return Stream.of(movie);
    }

    Stream<Movie> readAllMoviesByActorid(Integer actorid) {
        return movieList.stream().filter(t -> t.actorid.equals(actorid));
    }

    void updateMovie(Movie movie){
        movieList.stream()
                .filter(t -> t.movieid.equals(movie.movieid))
                .findFirst().ifPresent(t -> t.title = movie.title);
    }

    void deleteMovie(Integer movieid) {
        movieList.removeIf(t -> t.movieid.equals(movieid));
    }
}
