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
		
		 likeMovies = new LikeMoviesAPI();
		 cvs = new CvsLoader();
		 
		 
		 
		 /**
		File datastore = new File("datastore.xml");
		Serializer serializer = new XMLSerializer(datastore);
			
		paceApi = new PacemakerAPI(serializer);
		if (datastore.isFile())  {
		paceApi.load();
		}
		*/
	  }
	 
	 public static void main(String[] args) throws Exception {
		 
		 
		 Main main = new Main();
		 Shell shell = ShellFactory.createConsoleShell("lm", "Welcome to likemovie - ?help for instructions", main);
		 shell.commandLoop();
		 //main.likeMovies.store();
	 }
	 
	 @Command(description="Add a new User")
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
	 public void removeUser (@Param(name="id") Long id){
		 
		 Optional<User> user = Optional.fromNullable(likeMovies.getUserById(id));
		    if (user.isPresent())
		    {
		    	likeMovies.removeUser(id);
		    }
		 
	 }
	 
	 @Command(description="Get all users details")
	  public void getUsers ()  {
	    Collection<User> users = likeMovies.getUsers();
	    System.out.println(users);
	  }
	 
	 
	 
	 @Command(description="Add a Movie")
	 public void addMovie (
			 @Param(name="title") String title, 
			 @Param(name="year") String year, 
			 @Param(name="url") String url)	 {
		 
	 likeMovies.addMovie(title, year, url);
	 }
	 
	 @Command(description="Delete a Movie")
	 public void removeMovie (@Param(name="id") Long id){
		 
		 Optional<Movie> Movie = Optional.fromNullable(likeMovies.getMovieById(id));
		    if (Movie.isPresent())
		    {
		    	likeMovies.removeMovie(id);
		    }
		 
	 }
	 
	 @Command(description="Get all movies details")
	  public void getMovies ()  {
	    Collection<Movie> Movies = likeMovies.getMovies();
	    System.out.println(Movies);
	  }
	 	 
}