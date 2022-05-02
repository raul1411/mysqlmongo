package com.example.tasca5javafx;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Movie {
    Integer movieid;
    String title;
    LocalDateTime dateTime;
    Integer actorid;

    Movie(Integer movieid, String title) {
        this.movieid = movieid;
        this.title = title;
    }

    Movie(String title, Integer actorid) {
        this.title = title;
        this.actorid = actorid;
    }

    Stream<String> toDetail() {
        return Stream.of(new StringJoiner(", ", Movie.class.getSimpleName() + ": ", "")
                .add("movieid=\033[36m" + movieid + "\033[0m")
                .add("title='\033[36m" + title + "\033[0m'")
                .add("dateTime=\033[36m" + dateTime + "\033[0m")
                .toString());
    }
}
