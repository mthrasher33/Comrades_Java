package nhcc.CSCI2001.Thrasher.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import nhcc.CSCI2001.Thrasher.jsoup.CommieScraper;
import nhcc.CSCI2001.Thrasher.profiles.Communist;
import nhcc.CSCI2001.Thrasher.profiles.Hobby;
import nhcc.CSCI2001.Thrasher.profiles.HobbyDirectory;
import nhcc.CSCI2001.Thrasher.profiles.User;
import nhcc.CSCI2001.Thrasher.utilities.Utilities;

public class UserModel {
	
	private User user;
	private static ArrayList<Communist> allCommunists = new ArrayList<Communist>();
	private static ArrayList<Hobby> allHobbies = new ArrayList<Hobby>();
	public static String tableValues[][]; //the table that will display matches
	private Communist communistProfileInView; //this will be used to determine which profile to show based on mouse click event

	public UserModel() throws IOException{
		instantiateHobbies();
		instantiateCommunists();
		
	}
	
	//creates new user object, which inherits from the profile object
	public void createNewUser(String firstName, String lastName, String userName, int age, ArrayList<Hobby> hobbies){
		 this.user = new User(firstName, lastName, userName, age, hobbies); //fixed. 
		 calculateUserComrades();
	}
	
	//fills the jtable that will constitute the view of the main site
	public String[][] populateJTable(){
		tableValues = new String[getAllCommunists().size()][6];
		for(int row = 0; row<user.getStrongComrades().size(); row++){
			tableValues[row][0] = user.getStrongComrades().get(row).getUserName();
			tableValues[row][1] = Integer.toString(user.getStrongComrades().get(row).getAge());
			tableValues[row][2] = user.getStrongComrades().get(row).getComradeMatch();
			tableValues[row][3] = user.getStrongComrades().get(row).getHobbies().get(0).getName();
			tableValues[row][4] = user.getStrongComrades().get(row).getHobbies().get(1).getName();
			tableValues[row][5] = user.getStrongComrades().get(row).getHobbies().get(2).getName();
		}
		
		for(int row = 0; row<user.getModerateComrades().size(); row++){
			tableValues[row+user.getStrongComrades().size()][0] = user.getModerateComrades().get(row).getUserName();
			tableValues[row+user.getStrongComrades().size()][1] = Integer.toString(user.getModerateComrades().get(row).getAge());
			tableValues[row+user.getStrongComrades().size()][2] = user.getModerateComrades().get(row).getComradeMatch();
			tableValues[row+user.getStrongComrades().size()][3] = user.getModerateComrades().get(row).getHobbies().get(0).getName();
			tableValues[row+user.getStrongComrades().size()][4] = user.getModerateComrades().get(row).getHobbies().get(1).getName();
			tableValues[row+user.getStrongComrades().size()][5] = user.getModerateComrades().get(row).getHobbies().get(2).getName();
		}
		
		for(int row = 0; row<user.getWeakComrades().size(); row++){
			tableValues[row+user.getStrongComrades().size()+user.getModerateComrades().size()][0] = user.getWeakComrades().get(row).getUserName();
			tableValues[row+user.getStrongComrades().size()+user.getModerateComrades().size()][1] = Integer.toString(user.getWeakComrades().get(row).getAge());
			tableValues[row+user.getStrongComrades().size()+user.getModerateComrades().size()][2] = user.getWeakComrades().get(row).getComradeMatch();
			tableValues[row+user.getStrongComrades().size()+user.getModerateComrades().size()][3] = user.getWeakComrades().get(row).getHobbies().get(0).getName();
			tableValues[row+user.getStrongComrades().size()+user.getModerateComrades().size()][4] = user.getWeakComrades().get(row).getHobbies().get(1).getName();
			tableValues[row+user.getStrongComrades().size()+user.getModerateComrades().size()][5] = user.getWeakComrades().get(row).getHobbies().get(2).getName();
		}
		
		return tableValues;
	}
	
	//finds matches for the user and adds them to the User's profile
	public void calculateUserComrades(){
		for (Communist c : getAllCommunists()){
		int	numberOfSimilarities = 0;
			for (Hobby hobby : c.getHobbies()){
				if(user.getHobbies().contains(hobby)){
					numberOfSimilarities++;
				}
			}
				//determine the comrade strength based on number of hobbies in common
				switch (numberOfSimilarities){
					case 0: c.setComradeMatch("Weak Comrades");
							user.classifyCommunistMatch(c, user.getWeakComrades());
						break;
					case 1: c.setComradeMatch("Moderate Comrades");
							user.classifyCommunistMatch(c, user.getModerateComrades());
						break;
					case 2: c.setComradeMatch("Strong Comrades");
							user.classifyCommunistMatch(c, user.getStrongComrades());
						break;						
				}
			}
		}
	
	//instantiates the hobbies from an enum
	public static void instantiateHobbies(){
		for (HobbyDirectory hobby : HobbyDirectory.values()){
			Hobby possibleHobby = new Hobby(hobby.getName());		
			Utilities.getAllHobbies().add(possibleHobby);
		}

	}
	
	//instantiates the communists from the jsoup scrape
	public void instantiateCommunists() throws IOException{
		CommieScraper commies = new CommieScraper();
		for(int i = 0; i< commies.getWikiURLs().size(); i++){
			Communist communist = new Communist(commies.getFirstNames().get(i), commies.getLastNames().get(i), 
					commies.getUserNames().get(i), commies.getAges().get(i), randomHobbies(), commies.getWikiURLs().get(i));
			
			allCommunists.add(communist);
		}
	}
	
	//adds random hobbies to the communist profiles
	public ArrayList<Hobby> randomHobbies(){
		ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
		ArrayList<Integer> randomHobbiesArray = generateUniqueRandom(Utilities.getAllHobbies().size());
		for (int i=0; i<Utilities.getNUMBER_OF_HOBBIES_PER_COMMUNIST(); i++){
			hobbies.add(Utilities.getAllHobbies().get(randomHobbiesArray.get(i)));
		}
		
		return hobbies;
	}
	
	//help for this from stack overflow: http://stackoverflow.com/questions/8115722/generating-unique-random-numbers-in-java
	public ArrayList<Integer> generateUniqueRandom(int range){
			ArrayList<Integer> noRepeats = new ArrayList<Integer>();
			for (int i = 0; i<range; i++){
				noRepeats.add(i);
			}
			Collections.shuffle(noRepeats);
			return noRepeats;
		}
	
	//getters and setters
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	public ArrayList<Communist> getAllCommunists() {
		return allCommunists;
	}

	public void setAllCommunists(ArrayList<Communist> commies) {
		allCommunists = commies;
	}

	public static ArrayList<Hobby> getAllHobbies() {
		return allHobbies;
	}

	public void setAllHobbies(ArrayList<Hobby> hobbyList) {
		allHobbies = hobbyList;
	}
	
	public Communist getCommunistProfileInView(){
		return communistProfileInView;
	}
	
	public void setCommunistProfileInView(Communist communist){
		this.communistProfileInView = communist;
	}
}
