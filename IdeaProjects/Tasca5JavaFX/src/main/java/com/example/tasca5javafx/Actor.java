package com.example.tasca5javafx;

import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Actor {
    Integer actorid;
    String name;
    Stream<Movie> movies;

    Actor(String name) {
        this.name = name;
    }

    Actor(Integer actorid, String name) {
        this.actorid = actorid;
        this.name = name;
    }

    static Stream<String> toMaster(Actor p) {
        return Stream.of(new StringJoiner(", ")
                .add("personid=\33[34m" + p.actorid + "\33[0m")
                .add("name='\33[34m" + p.name + "\33[0m'")
                .toString());
    }

    static Stream<String> toDetail(Actor p) {
        return Stream.of(new StringJoiner(", ", Actor.class.getSimpleName() + ": ", "")
                        .add("personid=\33[34m" + p.actorid + "\33[0m")
                        .add("name='\33[34m" + p.name + "\33[0m'")
                        .toString(),
                "\t" + p.movies.flatMap(Movie::toDetail).collect(Collectors.joining("\n\t")));
    }
}
