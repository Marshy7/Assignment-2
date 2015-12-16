package models;

import com.google.common.base.Objects;

import utils.ToJsonString;

public class Movie{
	
	 static Long counter = 0l;
	
	 public Long id;
	
	 public String title;
	 public String year;
	 public String url;
	 
	 public int noOfRatings;
	 public int totalRating;
	 public double averageRating;
	 
	 
	 public Movie(String title, String year, String url) {
		 this.id = counter++;
		 this.title = title;
		 this.year = year;
		 this.url = url;
		 noOfRatings = 0;
		 totalRating = 0;
		 averageRating = 0;
	 }
	 
	 public Movie(String id, String title, String year, String url) {
		 this.id = Long.parseLong(id);
		 counter++;
		 this.title = title;
		 this.year = year;
		 this.url = url;
		 noOfRatings = 0;
		 totalRating = 0;
		 averageRating = 0;
	 }
	 
	 public void setTitle(String title){
		 this.title = title;
	 }
	 
	 public String getTitle(){
		 return title;
	 }
	 
	 public void setYear(String year){
		 this.year = year;
	 }
	 
	 public String getYear(){
		 return year;
	 }
	 
	 public void setUrl(String url){
		 this.url = url;
	 }
	 
	 public String getUrl(){
		 return url;
	 }
	 
	 public void addRating(String rating){
		 noOfRatings++;
		 totalRating += Integer.parseInt(rating);
		 averageRating = totalRating/noOfRatings;
	 }
	 
	 public int getAverageRating(){
		 return (int)(averageRating + 0.5);
	 }
	
	 @Override
	 public String toString() {
		 return new ToJsonString(getClass(), this).toString();
	 }
	
	 @Override
	 public int hashCode() {
		 return Objects.hashCode(this.id, this.title, this.year, this.url);
	 }
	
	 @Override
	 public boolean equals(final Object obj) {
		 if (obj instanceof Movie) {
			 
			 final Movie other = (Movie) obj;
			 return Objects.equal(title, other.title)
			 && Objects.equal(year, other.year)
			 && Objects.equal(url, other.url);
		 }
		 else {
			 return false;
		 }
	 }
}