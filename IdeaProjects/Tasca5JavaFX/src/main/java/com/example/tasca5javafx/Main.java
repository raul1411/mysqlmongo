package com.example.tasca5javafx;

import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main implements Database {static ArrayRepository repository = new ArrayRepository();
    static Scanner scanner = new Scanner(System.in);
    static Scanner sc = new Scanner(System.in);
    static Database db = null;
    static int action = 1;
    static String bgcolor;

    public static void main(String[] args) {
        populate();
        startApp();
    }

    static void populate() {
        repository.createActor(new Actor("joan")).findFirst().ifPresent(p -> {
            repository.createMovie(new Movie("piso", p.actorid));
            repository.createMovie(new Movie("casa", p.actorid));
        });

        repository.createActor(new Actor("anna")).findFirst().ifPresent(p -> {
            repository.createMovie(new Movie("yate", p.actorid));
        });

        repository.createActor(new Actor("laia")).findFirst().ifPresent(p -> {
            repository.createMovie(new Movie("boli", p.actorid));
            repository.createMovie(new Movie("clip", p.actorid));
            repository.createMovie(new Movie("bloc", p.actorid));
        });
    }

    static void startApp(){
        while (true) {
            System.out.println("\33[1;30;45m--- MASTER SCREEN ---\33[0m\n");
            repository.readAllActors().flatMap(Actor::toMaster).forEach(System.out::println);
            System.out.print("\n\33[1;30;45m[PERSON] CREATE/READ/UPDATE/DELETE or QUIT:\33[0m ");
            String option = scanner.next().substring(0,1).toLowerCase(Locale.ROOT);

            if (option.equals("q")) {
                break;
            } else if (option.equals("c")){
                System.out.print("Person name: ");
                String name = scanner.next();
                repository.createActor(new Actor(name));
            } else {
                System.out.print("Person ID: ");
                int personid = scanner.nextInt();
                if (option.equals("u")) {
                    System.out.println("Person new name: ");
                    String newName = scanner.next();
                    repository.updatePerson(new Actor(personid, newName));
                } else if (option.equals("d")) {
                    repository.deletePerson(personid);
                } else if (option.equals("r")) {
                    while (true) {
                        System.out.println("\33[1;30;104m--- DETAIL SCREEN ---\33[0m\n");
                        repository.readActor(personid).flatMap(Actor::toDetail).forEach(System.out::println);
                        System.out.print("\n\33[1;30;104m[THING] CREATE/UPDATE/DELETE or BACK:\33[0m ");
                        option = scanner.next().substring(0, 1).toLowerCase(Locale.ROOT);
                        if (option.equals("b")) {
                            break;
                        } else if (option.equals("c")) {
                            System.out.println("Thing title: ");
                            String title = scanner.next();
                            repository.createMovie(new Movie(title, personid));
                        } else {
                            System.out.print("Thing ID: ");
                            int thingid = scanner.nextInt();

                            if (option.equals("u")) {
                                System.out.println("Thing new title: ");
                                String newTitle = scanner.next();
                                repository.updateMovie(new Movie(thingid, newTitle));
                            } else if (option.equals("d")) {
                                repository.deleteMovie(thingid);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void insertFilm(String title, String synopsis) {
        db.insertFilm(title, synopsis);
    }

    @Override
    public Movie queryFilm(String title) {
        return db.queryFilm(title);
    }

    @Override
    public Stream<Movie> queryAllFilms() {
        return db.queryAllFilms();
    }

    @Override
    public void deleteFilm(String title) {
        db.deleteFilm(title);
    }

    @Override
    public void updateFilm(String title, String newTitle, String synopsis) {
        db.updateFilm(title, newTitle, synopsis);
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
    public Stream<Actor> queryAllActors() {
        return db.queryAllActors();
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
    public Stream<Relation> queryAllRelations() {
        return db.queryAllRelations();
    }

}