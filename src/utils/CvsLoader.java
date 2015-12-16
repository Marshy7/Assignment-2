package utils;

import java.io.File;
import java.util.ArrayList;

import edu.princeton.cs.introcs.In;

public class CvsLoader{
	

     
     public ArrayList<String> CvsUsers(){
    	 
    	 File usersFile = new File("lib/moviedata_small/users5.dat");
         In inUsers = new In(usersFile);
         //each field is separated(delimited) by a '|'
         String delims = "[|]";
	    	 
    	 ArrayList<String> users = new ArrayList<String>();
	    	 	    	 
		     while (!inUsers.isEmpty()) {
		         // get user and rating from data source
		         String userDetails = inUsers.readLine();
		
		         // parse user details string
		         String[] userTokens = userDetails.split(delims);
		
		         // output user data to console.
		         if (userTokens.length == 7) {
		             users.add(userTokens[0]+"|"+
		                     userTokens[1]+"|" + userTokens[2]+"|"+
		                     Integer.parseInt(userTokens[3])+"|"+userTokens[4]+"|"+
		                     userTokens[5]);
		
		         }else
		         {
		             try {
						throw new Exception("Invalid member length: "+userTokens.length);
					} catch (Exception e) {
						System.out.println("Unable to load user data from CVS file");
						e.printStackTrace();
					}
		         }
		     }
		     
		     return users;
	     }
     
     public ArrayList<String> CvsMovies(){
    	 File moviesFile = new File("lib/moviedata_small/items5.dat");
         In inmovies = new In(moviesFile);
         //each field is separated(delimited) by a '|'
         String delims = "[|]";
         
         ArrayList<String> movies = new ArrayList();
         
         while (!inmovies.isEmpty()) {
             // get user and rating from data source
             String movieDetails = inmovies.readLine();

             // parse user details string
             String[] movieTokens = movieDetails.split(delims);

             // output user data to console.
             if (movieTokens.length == 23) {
                 movies.add(movieTokens[0]+"|"+
                		 movieTokens[1]+"|" + movieTokens[2]+"|"+
                		 movieTokens[3]);

             }else
             {
                 try {
					throw new Exception("Invalid member length: "+movieTokens.length);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
         }
         
         return movies;
     }
     
     public ArrayList<String> CvsRatings(){
    	 
    	 File ratingsFile = new File("lib/moviedata_small/ratings5.dat");
         In inRatings = new In(ratingsFile);
         //each field is separated(delimited) by a '|'
         String delims = "[|]";
         
         ArrayList<String> ratings = new ArrayList<String>();
         
         while (!inRatings.isEmpty()) {
             // get user and rating from data source
             String ratingDetails = inRatings.readLine();

             // parse user details string
             String[] ratingTokens = ratingDetails.split(delims);

             // output user data to console.
             if (ratingTokens.length == 4) {
                 ratings.add(ratingTokens[0]+"|"+
                		 ratingTokens[1]+"|" + ratingTokens[2]+"|"+
                         ratingTokens[3]);

             }else
             {
                 try {
					throw new Exception("Invalid member length: "+ratingTokens.length);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
         }
         
         return ratings;
     }
         
}


