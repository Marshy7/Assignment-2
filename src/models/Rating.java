package models;

import com.google.common.base.Objects;

import utils.ToJsonString;

public class Rating {

	public long userId;
	public long movieId;
	public int movieRating;
	
	public Rating(long userId, long movieId, int movieRating ){
		
		this.userId = userId;
		this.movieId = movieId;
		this.movieRating = movieRating;
	}
	
	
	
	@Override
	 public String toString() {
		 return new ToJsonString(getClass(), this).toString();
	 }
	
	 @Override
	 public int hashCode() {
		 return Objects.hashCode(this.movieId, this.movieRating);
	 }
	
	 @Override
	 public boolean equals(final Object obj) {
		 if (obj instanceof Rating) {
			 
			 final Rating other = (Rating) obj;
			 return Objects.equal(movieId, other.movieId)
			 && Objects.equal(movieRating, other.movieRating)
			 && Objects.equal(userId, other.userId);
		 }
		 else {
			 return false;
		 }
	 }
	
}
