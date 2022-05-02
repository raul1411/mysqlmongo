package com.company.databases;

import com.company.models.Actor;
import com.company.models.Movie;
import com.company.models.Relation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DatabaseMysql implements Database{

    String uri ="jdbc:mysql://localhost/mydatabase?user=myuser&password=mypass";

    @Override
    public void insertMovie(String title, String synopsis) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement statement = c.prepareStatement("INSERT INTO movies(title, synopsis) VALUES(?, ?)");
            statement.setString(1, title);
            statement.setString(2, synopsis);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertActor(String name, int age) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement s = c.prepareStatement("INSERT INTO actors(name, age) VALUES(?, ?)");
            s.setString(1, name);
            s.setString(2, String.valueOf(age));
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertRelation(int id_actor, int id_movie) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement s = c.prepareStatement("INSERT INTO actorsMovies(id_actor, id_movie) VALUES(?, ?)");
            s.setString(1, String.valueOf(id_actor));
            s.setString(2, String.valueOf(id_movie));
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Movie> allMovies() {
        List<Movie> movies = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(uri)){
            ResultSet resultSet = c.createStatement().executeQuery("SELECT * FROM movies");
            while (resultSet.next()) {
                Movie movie = new Movie(resultSet.getString("id"), resultSet.getString("title"), resultSet.getString("synopsis"));
                movies.add(movie);
                System.out.println(movie.toStringSql());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies.stream();
    }

    @Override
    public Stream<Actor> allActors() {
        List<Actor> actors = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(uri)){
            ResultSet resultSet = c.createStatement().executeQuery("SELECT * FROM actors");
            while (resultSet.next()) {
                Actor actor = new Actor(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("age"));
                actors.add(actor);
                System.out.println(actor.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors.stream();
    }

    @Override
    public Stream<Relation> allRelations() {
        List<Relation> relations = new ArrayList<>();
        try(Connection c = DriverManager.getConnection(uri)){
            ResultSet resultSet = c.createStatement().executeQuery("SELECT * FROM actorsMovies");
            while (resultSet.next()) {
                Relation relation = new Relation(resultSet.getString("id_actor"), resultSet.getString("id_movie"), resultSet.getString("insertDate"));
                relations.add(relation);
                System.out.println(relation.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relations.stream();
    }

    @Override
    public Movie queryMovie(String title) {
        Movie movie = null;
        try(Connection c = DriverManager.getConnection(uri)){
            ResultSet resultSet = c.createStatement().executeQuery("SELECT * FROM movies WHERE title = '" + title + "'");
            while (resultSet.next()) {
                movie = new Movie(resultSet.getString("id"), resultSet.getString("title"), resultSet.getString("synopsis"));
                System.out.println(movie.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public Actor queryActor(String name) {
        Actor actor = null;
        try(Connection c = DriverManager.getConnection(uri)){
            ResultSet resultSet = c.createStatement().executeQuery("SELECT * FROM actors WHERE name = '" + name + "'");
            while (resultSet.next()) {
                actor = new Actor(resultSet.getString("id"), resultSet.getString("name"), resultSet.getInt("age"));
                System.out.println(actor.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }

    @Override
    public Relation queryRelation(int id_actor, int id_movie) {
        Relation relation = null;
        try(Connection c = DriverManager.getConnection(uri)){
            ResultSet resultSet = c.createStatement().executeQuery("SELECT * FROM actorsMovies WHERE id_actor = '" + id_actor + "' AND id_movie = '" + id_movie + "'");
            while (resultSet.next()) {
                relation = new Relation(resultSet.getString("id_actor"), resultSet.getString("id_movie"), resultSet.getString("insertDate"));
                System.out.println(relation.toStringSql());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relation;
    }

    @Override
    public void deleteMovie(String title) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement statement = c.prepareStatement("DELETE FROM movies WHERE title = (?)");
            statement.setString(1, title);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActor(String name) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement statement = c.prepareStatement("DELETE FROM actors WHERE name = (?)");
            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRelation(int id_actor, int id_movie) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement statement = c.prepareStatement("DELETE FROM actorsMovies WHERE id_actor = (?) AND id_movie = (?)");
            statement.setString(1, String.valueOf(id_actor));
            statement.setString(2, String.valueOf(id_movie));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMovie(String title, String newTitle, String synopsis) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement statement = c.prepareStatement("UPDATE movies SET title = (?), synopsis = (?) WHERE title = (?)");
            statement.setString(1, newTitle);
            statement.setString(2, synopsis);
            statement.setString(3, title);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateActor(String name, String newName, int age) {
        try(Connection c = DriverManager.getConnection(uri)){
            PreparedStatement statement = c.prepareStatement("UPDATE actors SET name = (?), age = (?) WHERE name = (?)");
            statement.setString(1, newName);
            statement.setString(1, String.valueOf(age));
            statement.setString(1, name);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}