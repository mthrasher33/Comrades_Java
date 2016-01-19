package nhcc.CSCI2001.Thrasher.profiles;

import java.util.ArrayList;


public class Profile {
	private String lastName, firstName, userName;
	private int age;
	private ArrayList<Hobby> hobbies = new ArrayList<Hobby>();

	private Hobby favoriteHobby;
	
	public Profile(String firstName, String lastName, String userName, int age, ArrayList<Hobby> hobbies){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		try{
			this.age = age;
		}
		catch(NumberFormatException e){
			 System.err.println("Please enter a valid integer for age" + e.getMessage());
		}
		
		this.hobbies = hobbies;
	}
	
	//adds a hobby to the profile's array list of hobbies
	public void addHobby(Hobby hobby){
		getHobbies().add(hobby);
	}
			
	public void printShortProfile(){
		System.out.printf("%-25s%-5s%-25s\n", getUserName(), getAge(), 
				getFavoriteHobby().getName());	
		}
	
	
	public void printFullProfile(){
		System.out.printf("%-25s%-5s%-25s\n", getUserName(), getAge(), 
				getHobbies().get(0).getName());	
		
		for(int i=1; i<getHobbies().size(); i++){
			System.out.printf("%-25s%-5s%-25s%-25s\n", "","",getHobbies().get(i).getName()
					);
		}
	
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public void setFavoriteHobby(Hobby favoriteHobby){
		this.favoriteHobby = favoriteHobby;
	}
	
	public Hobby getFavoriteHobby(){
		return favoriteHobby;
	}

	public ArrayList<Hobby> getHobbies() {
		return hobbies;
	}

	public void setHobbies(ArrayList<Hobby> profileHobbies) {
		this.hobbies = profileHobbies;
	}
}
