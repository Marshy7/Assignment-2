package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;

import models.Movie;
import models.Rating;
import models.User;
import utils.CvsLoader;
import utils.Serializer;

public class LikeMoviesAPI{
	
	Serializer serializer;	
	
	 public Map<Long, User> userIndex = new HashMap<>();
	 public Map<Long, Movie> movieIndex = new HashMap<>();
	 ArrayList<String> userList, movieList, ratingList;
	 
	 public LikeMoviesAPI(){}
	 
	 
	 public LikeMoviesAPI(Serializer serializer){
	    this.serializer = serializer;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	    serializer.read();
	    userIndex 		= (Map<Long, User>) serializer.pop();
	    movieIndex      = (Map<Long, Movie>)   serializer.pop();
	    
	  }
	  
	  public void store() throws Exception
	  {
	    
	    serializer.push(movieIndex);
	    serializer.push(userIndex);
	    serializer.write(); 
	  }
	 
	 public  void loadFromCvs(){
		 CvsLoader cvs = new CvsLoader();
		 		 		 
		 userList = cvs.CvsUsers();		 
		 for(String userLoad: userList){
			 		 
			 String[] userTokens = userLoad.split("[|]");
			 
			 Optional<User> userCheck = Optional.fromNullable(getUserById(userTokens[0]));
			    if (!userCheck.isPresent())
			    {
			    	 User user = new User (userTokens[0], userTokens[1], userTokens[2], userTokens[3], userTokens[4], userTokens[5]);
					 userIndex.put(user.id, user);
			    }
			 
		 }
		 
		 movieList = cvs.CvsMovies();		 
		 for(String movieLoad: movieList){
			 		 
			 String[] movieTokens = movieLoad.split("[|]");
			 
			 Optional<Movie> movieCheck = Optional.fromNullable(getMovieById(movieTokens[0]));
			    if (!movieCheck.isPresent())
			    {
			    	 Movie movie = new Movie (movieTokens[0], movieTokens[1], movieTokens[2], movieTokens[3]);
					 movieIndex.put(movie.id, movie);
			    }
			 
		 }
		 
		 ratingList = cvs.CvsRatings();	 
		 for(String ratingLoad: ratingList){
			 		 
			 String[] ratingTokens = ratingLoad.split("[|]");
			 
			 Optional<User> userCheck = Optional.fromNullable(getUserById(ratingTokens[0]));
			 Optional<Movie> movieCheck = Optional.fromNullable(getMovieById(ratingTokens[1]));
			    if (userCheck.isPresent() && movieCheck.isPresent())
			    {
			    	 User user = userIndex.get(Long.parseLong(ratingTokens[0]));
			    	 user.rateMovie(ratingTokens[1], ratingTokens[2]);
			    	 
			    	 Movie movie = movieIndex.get(Long.parseLong(ratingTokens[1]));
					 movie.addRating(ratingTokens[2]);
			    }
			 
		 }
		 
	 }
	
	 public User addUser(String firstName, String lastName, String age, String gender, String occupation){
		 User user = new User (firstName, lastName, age, gender, occupation);
		 userIndex.put(user.id, user);
		 return user;
	 }
	 
	 public void removeUser(String id)  {
		 userIndex.remove(Long.parseLong(id));    
	  }
	 
	 public User getUserById(String id) 
	  {
	    return userIndex.get(Long.parseLong(id));
	  }
	 
	 public Collection<User> getUsers ()  {
		 return userIndex.values();
	  }
	 
	 public ArrayList<Movie> recommendMovies(String userId) {
		 
		 ArrayList<Movie> movies = new ArrayList<Movie>();
		 ArrayList<Movie> recommendMovies = new ArrayList<Movie>();
		 
		 List<Rating> ratings = null;
		 
		 Optional<User> userCheck = Optional.fromNullable(getUserById(userId));
		
		    if (userCheck.isPresent())
		    {
		    	 User user = userIndex.get(Long.parseLong(userId));
		    	 ratings = user.getUserRatings();
		    	 		    	 
		    }
		 
		 for (Movie movie: movieIndex.values()) {
			 
			 boolean watched = false;
			 			 
			 for(Rating movieWatched: ratings){
				 if(movie.id == movieWatched.movieId){
					 watched = true;
				 }
			 }
			 if(!watched){
				 movies.add(movie);
			 }
		 }		 		 
		 
		 Collections.sort(movies);
		 int recommendMovie = 5;
		 if(movies.size() < recommendMovie){
			 recommendMovie = movies.size();
		 }
		 
		 for (int i = (movies.size() -1); i > (movies.size() - recommendMovie); i--){
			 			 
			 recommendMovies.add(movies.get(i));
			 			 
		 }
		 
		 return recommendMovies;
	 }
	 
	 public Movie addMovie(String title, String year, String url) {
		 Movie movie = new Movie (title, year, url);
		 movieIndex.put(movie.id, movie);
		 return movie;
	 }
	 
	 public void removeMovie(String id)  {
		 
		 Optional<User> user = Optional.fromNullable(getUserById(id));
		    if (user.isPresent())
		    {
		    	movieIndex.remove(id);
		    }
		    
	  }
	 
	 public Movie getMovieById(String id) 
	  {
	    return movieIndex.get(Long.parseLong(id));
	  }
	 
	 public Movie getMovie(String movieId){
		 
		 Movie movie = null;;
		 
		 Optional<Movie> movieCheck = Optional.fromNullable(getMovieById(movieId));
		    if (movieCheck.isPresent()){
		    	
		    	movie = movieIndex.get(movieId);
		    }
		 return movie;
	 }
	 
	 public Collection<Movie> getAllMovies ()  {
		 return movieIndex.values();
	  }
	 
	 
	 public ArrayList<Movie> getTopTenMovies() {
		 int topMovieSize = 10;
		 ArrayList<Movie> movies = new ArrayList<Movie>();
		 ArrayList<Movie> topTenMovies = new ArrayList<Movie>();
		 for (Movie movie: movieIndex.values()) {
			 movies.add(movie);
		 }
		 
		 if(movies.size() < 10){
			 topMovieSize = movies.size();
		 }
		 
		 Collections.sort(movies);
		 for (int i = (movies.size() -1); i > (movies.size() - topMovieSize); i--){
			 topTenMovies.add(movies.get(i));
		 }
		 
		 return topTenMovies;
	 }
	 
	 public void rateMovie(String userId, String movieId, String rating){
		 Optional<User> userCheck = Optional.fromNullable(getUserById(userId));
		 Optional<Movie> movieCheck = Optional.fromNullable(getMovieById(movieId));
		    if (userCheck.isPresent() && movieCheck.isPresent())
		    {
		    	 User user = userIndex.get(Long.parseLong(userId));
		    	 user.rateMovie(movieId, rating);
		    	 
		    	 Movie movie = movieIndex.get(Long.parseLong(movieId));
				 movie.addRating(rating);
		    }
	 }
	 
	 public List<Rating> getUserRatings(String userId){
		 
		 List<Rating> ratings = null;
		 
		 Optional<User> userCheck = Optional.fromNullable(getUserById(userId));
		
		    if (userCheck.isPresent())
		    {
		    	 User user = userIndex.get(Long.parseLong(userId));
		    	 ratings = user.getUserRatings();
		    	 		    	 
		    }
		 
		 return ratings;
	 }
	 
	 
	 
}