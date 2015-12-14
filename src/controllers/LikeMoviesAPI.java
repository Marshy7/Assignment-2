package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.Movie;
import models.User;

public class LikeMoviesAPI{
	
	 public Map<Long, User> userIndex = new HashMap<>();
	 public Map<Long, Movie> movieIndex = new HashMap<>();
	
	 public LikeMoviesAPI(){}
	
	 public User addUser(String firstName, String lastName, String age, String gender, String occupation){
		 User user = new User (firstName, lastName, age, gender, occupation);
		 userIndex.put(user.id, user);
		 return user;
	 }
	 
	 public void removeUser(Long id)  {
		 userIndex.remove(id);	    
	  }
	 
	 public User getUserById(Long id) 
	  {
	    return userIndex.get(id);
	  }
	 
	 public Collection<User> getUsers ()  {
		 return userIndex.values();
	  }
	 
	 public Movie addMovie(String title, String year, String url) {
		 Movie movie = new Movie (title, year, url);
		 movieIndex.put(movie.id, movie);
		 return movie;
	 }
	 
	 public void removeMovie(Long id)  {
		 movieIndex.remove(id);	    
	  }
	 
	 public Movie getMovieById(Long id) 
	  {
	    return movieIndex.get(id);
	  }
	 
	 public Collection<Movie> getMovies ()  {
		 return movieIndex.values();
	  }
	 
}