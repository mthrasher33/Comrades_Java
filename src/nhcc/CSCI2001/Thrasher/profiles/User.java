package nhcc.CSCI2001.Thrasher.profiles;

import java.util.ArrayList;
import nhcc.CSCI2001.Thrasher.profiles.Communist;
 

public class User extends Profile{
		
	//user profile maintains three array lists of comrade matches
	private ArrayList<Communist> strongComrades = new ArrayList<Communist>();
	private ArrayList<Communist> moderateComrades = new ArrayList<Communist>();
	private ArrayList<Communist> weakComrades = new ArrayList<Communist>();

	public User(String firstName, String lastName, String userName, int age, ArrayList<Hobby> hobbies){
		super(firstName, lastName, userName, age, hobbies);
	}
	
	//method to sort communists into the arrays based on their match strength
	public void classifyCommunistMatch(Communist commy, ArrayList<Communist> comradeStrength){
		comradeStrength.add(commy);
	}

	//getters and setters
	public ArrayList<Communist> getStrongComrades() {
		return strongComrades;
	}

	public void setStrongComrades(ArrayList<Communist> strongComrades) {
		this.strongComrades = strongComrades;
	}

	public ArrayList<Communist> getModerateComrades() {
		return moderateComrades;
	}

	public void setModerateComrades(ArrayList<Communist> moderateComrades) {
		this.moderateComrades = moderateComrades;
	}

	public ArrayList<Communist> getWeakComrades() {
		return weakComrades;
	}

	public void setWeakComrades(ArrayList<Communist> weakComrades) {
		this.weakComrades = weakComrades;
	}
}
