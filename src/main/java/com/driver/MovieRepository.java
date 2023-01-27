package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

@Repository
public class MovieRepository {
    Map<String,List<String>> db1 = new HashMap<>(); // Movie -Director pair
    Map<String,Movie> db2 = new HashMap<>(); // Movie
    Map<String,Director> db3 = new HashMap<>(); // Director

    public String addMovie(Movie movie){
        String str = movie.getName();
        db2.put(str,movie);
        return "Added Movie Successfully";
    }

    public String addDirector(Director director){
        String st = director.getName();
        db3.put(st,director);
        return "Added Director Successfully";
    }

    public String addMovieDirectorPair(String MovieName, String DirectorName){
        if(!db1.containsKey(MovieName) || (!db1.containsKey(DirectorName))){
            return "Movie or Director not available";
        }
        List<String>movieList = db1.getOrDefault(DirectorName, new ArrayList<>());
        if(movieList.contains(MovieName)){
            return "Already available";
        }
        else{
            movieList.add(MovieName);
            db1.put(DirectorName,movieList);
            return "Added pair successfully";
        }
    }

   public Movie getMovieByName(String name){
        if(db2.containsKey(name)){
            return db2.get(name);
        }
        return null;
   }


   public Director getDirectorByName(String name){
        if(db3.containsKey(name)){
            return db3.get(name);
        }
        return null;
   }

   public List<String> getMoviesByDirectorName(String DirectorName){
        if(db1.containsKey(DirectorName)){
            return db1.get(DirectorName);
        }
        return null;
   }

   public List<String> findAllMovies(){
        List<String> allMovie = new ArrayList<>();
        for(String str : db2.keySet()){
            allMovie.add(str);
        }
        return allMovie;
   }

   public String deleteDirectorByName(String DirectorName){
        List<String> movieList = new ArrayList<>();
        if(db1.containsKey(DirectorName)){
            movieList = db1.get(DirectorName);
        }

        for(String str: movieList){
            if(db2.containsKey(str)){
                db2.remove(str);
            }
        }
        db1.remove(DirectorName);
        if(db3.containsKey(DirectorName)){
            db3.remove(DirectorName);
        }
        return "Delete Director and Movie from Database";
   }

   public String deleteAllDirectors(){
        for(String str:db1.keySet()){
            List<String> directorMovieList = new ArrayList<>();
            directorMovieList = db1.get(str);
            for(String movie: directorMovieList){
                if(db2.containsKey(movie)){
                    db2.remove(movie);
                }
            }
            db1.remove(str);
        }
        for(String str : db3.keySet()){
            db3.remove(str);
        }
        return "Delete All Movie and Director for Database";
   }

}
