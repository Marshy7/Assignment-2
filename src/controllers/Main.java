package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.User;
import utils.CvsLoader;
import utils.Serializer;
import utils.XMLSerializer;

public class Main{
	
	 public LikeMoviesAPI likeMovies;
	 CvsLoader cvs;
	 ArrayList<String> userList;
	 
	 public Main() throws Exception  {
		
		 
		 		
		File datastore = new File("datastore.xml");
		Serializer serializer = new XMLSerializer(datastore);
			
		likeMovies = new LikeMoviesAPI(serializer);
		
		
	  }
	 
	 public static void main(String[] args) throws Exception {
		 
		 
		 Main main = new Main();
		 Shell shell = ShellFactory.createConsoleShell("lm", "Welcome to likemovie - ?help for instructions", main);
		 shell.commandLoop();
		 //main.likeMovies.store();
	 }
	 
	 @Command(description="Load from CVS file")
	 public void loadFromCvs (			 ){
		 
		 likeMovies.loadFromCvs(); 
	 
	 }
	 
	 @Command(description="Add a new User")
	 public void addUser (
			 @Param(name="first name") String firstName,
			 @Param(name="last name") String lastName,
			 @Param(name="age") String age, 
			 @Param(name="gender") String gender, 
			 @Param(name="occupation") String occupation){
		 
	 likeMovies.addUser(firstName, lastName, age, gender, occupation);
	 }
	
	 @Command(description="Delete a User")
	 public void removeUser (@Param(name="User ID") String id){
		 
		 likeMovies.removeUser(id);
		 
	 }
	 
	 @Command(description="Get all users details")
	  public void getUsers ()  {
	    Collection<User> users = likeMovies.getUsers();
	    System.out.println(users);
	  }
	 
	 @Command(description="Get User recommendations")
	  public void recommendMovies (@Param(name="User ID") String id)  {
	    ArrayList<Movie> movies = likeMovies.recommendMovies(id);
	    System.out.println(movies);
	  }
	 
	 @Command(description="Store file to XML")
	  public void write () throws Exception  {
	    likeMovies.store();
	  }
	 
	 @Command(description="load file from XML")
	  public void load () throws Exception  {
	    likeMovies.load();
	  }
	 
	 
	 
	 @Command(description="Add a Movie")
	 public void addMovie (
			 @Param(name="title") String title, 
			 @Param(name="year") String year, 
			 @Param(name="url") String url)	 {
		 
	 likeMovies.addMovie(title, year, url);
	 }
	 
	 @Command(description="Delete a Movie")
	 public void removeMovie (@Param(name="id") String id){
		 
		 Optional<Movie> Movie = Optional.fromNullable(likeMovies.getMovieById(id));
		    if (Movie.isPresent())
		    {
		    	likeMovies.removeMovie(id);
		    }
		 
	 }
	 
	 @Command(description="Get a movies details")
	  public void getMovie (@Param(name="Movie ID") String id)  {
	    Movie movie = likeMovies.getMovie(id);
	    System.out.println(movie);
	  }
	 
	 @Command(description="Get all movies details")
	  public void getAllMovies ()  {
	    Collection<Movie> Movies = likeMovies.getAllMovies();
	    System.out.println(Movies);
	  }
	 
	 @Command(description="Get top 10 movies details")
	  public void getTopTenMovies ()  {
	    ArrayList<Movie> movies = likeMovies.getTopTenMovies();
	    System.out.println(movies);
	  }
	 
	 @Command(description="Rate a Movie")
	 public void rateMovie (
			 @Param(name="User ID") String userId, 
			 @Param(name="Movie ID") String movieId, 
			 @Param(name="Movie Rating") String rating){
		 
		 likeMovies.rateMovie(userId, movieId, rating);
	 }
	 
	 @Command(description="Get user Ratings")
	 public void getUserRatings (
			 @Param(name="User ID") String userId){
		 
		 System.out.println(likeMovies.getUserRatings(userId));
	 }
	 
	 	 
}