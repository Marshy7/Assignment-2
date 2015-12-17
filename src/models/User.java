package models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

import utils.ToJsonString;

public class User{
	
	 static Long counter = 0l;
	 public Long id;
	 public String firstName;
	 public String lastName;
	 public String gender;
	 public int age;
	 public String occupation;
	
	 public ArrayList<Rating> ratings = new ArrayList<>();
	
	 public User(String firstName, String lastName, String age, String gender, String occupation){
		 this.id = counter++;
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.gender = gender;
		 this.age = Integer.parseInt(age);
		 this.occupation = occupation;
	 }
	 
	 public User(String id, String firstName, String lastName, String age, String gender, String occupation){
		 this.id = Long.parseLong(id);
		 counter++;
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.gender = gender;
		 this.age = Integer.parseInt(age);
		 this.occupation = occupation;
	 }
	 
	 public void setFirstName(String firstName){
		 this.firstName = firstName;
	 }
	 
	 public String getFirstName(){
		 return firstName;
	 }
	 
	 public void setLastName(String lastName){
		 this.lastName = lastName;
	 }
	 
	 public String getLastName(){
		 return lastName;
	 }
	 
	 public void setGender(String gender){
		 this.gender = gender;
	 }
	 
	 public String getgender(){
		 return gender;
	 }
	 
	 public void setAge(String age){
		 this.age = Integer.parseInt(age);
	 }
	 
	 public int getAge(){
		 return age;
	 }
	 
	 public void setOccupation(String occupation){
		 this.occupation = occupation;
	 }
	 
	 public String getOccupation(){
		 return occupation;
	 }
	 
	 public void rateMovie(String movieId, String rating){
		 ratings.add(new Rating(id, Long.parseLong(movieId), Integer.parseInt(rating)));
	 }
	 	 
	 public ArrayList<Rating> getUserRatings(){
		 
		 return ratings;
	 }
	
	 public String toString() {
		 return new ToJsonString(getClass(), this).toString();
	 }
	
	 @Override
	 public int hashCode(){
		 return Objects.hashCode(this.lastName, this.firstName, this.gender, this.age);
	 }
	
	 @Override
	 public boolean equals(final Object obj) {
		 if (obj instanceof User){
		 final User other = (User) obj;
			 return Objects.equal(firstName, other.firstName)
			 && Objects.equal(lastName, other.lastName)
			 && Objects.equal(gender, other.gender)
			 && Objects.equal(age, other.age)
			 && Objects.equal(occupation, other.occupation)
			 && Objects.equal(ratings, other.ratings);
		 }
		 else{
			 return false;
		 }
	 }
	 
}