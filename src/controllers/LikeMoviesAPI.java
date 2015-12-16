package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import models.Movie;
import models.Rating;
import models.User;
import utils.CvsLoader;

public class LikeMoviesAPI{
	
	 public Map<Long, User> userIndex = new HashMap<>();
	 public Map<Long, Movie> movieIndex = new HashMap<>();
	 ArrayList<String> userList, movieList, ratingList;
	 
	 public LikeMoviesAPI(){}
	 
	 public  void loadFromCvs(){
		 CvsLoader cvs = new CvsLoader();
		 		 		 
		 userList = cvs.CvsUsers();		 
		 for(String userLoad: userList){
			 		 
			 String[] userTokens = userLoad.split("[|]");
			 
			 Optional<User> userCheck = Optional.fromNullable(getUserById(Long.parseLong(userTokens[0])));
			    if (!userCheck.isPresent())
			    {
			    	 User user = new User (Long.parseLong(userTokens[0]), userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5]);
					 userIndex.put(user.id, user);
			    }
			 
		 }
		 
		 movieList = cvs.CvsMovies();		 
		 for(String movieLoad: movieList){
			 		 
			 String[] movieTokens = movieLoad.split("[|]");
			 
			 Optional<Movie> movieCheck = Optional.fromNullable(getMovieById(Long.parseLong(movieTokens[0])));
			    if (!movieCheck.isPresent())
			    {
			    	 Movie movie = new Movie (Long.parseLong(movieTokens[0]), movieTokens[1], movieTokens[2], movieTokens[3]);
					 movieIndex.put(movie.id, movie);
			    }
			 
		 }
		 
		 ratingList = cvs.CvsRatings();	 
		 for(String ratingLoad: ratingList){
			 		 
			 String[] ratingTokens = ratingLoad.split("[|]");
			 
			 Optional<User> userCheck = Optional.fromNullable(getUserById(Long.parseLong(ratingTokens[0])));
			    if (userCheck.isPresent())
			    {
			    	 User user = userIndex.get(Long.parseLong(ratingTokens[0]));
			    	 user.rateMovie(Long.parseLong(ratingTokens[1]), Integer.parseInt(ratingTokens[2]));
					 
			    }
			 
		 }
		 
	 }
	
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