package nhcc.CSCI2001.Thrasher.utilities;

import java.util.ArrayList;
import java.util.Random;

import nhcc.CSCI2001.Thrasher.profiles.Hobby;


public class Utilities {

	private static int LONGEST_TIME_FOR_CHAT_RESPONSE = 10000; //in miliseconds
	private static int NUMBER_OF_HOBBIES_PER_COMMUNIST =3;
	private static ArrayList<Hobby> allHobbies = new ArrayList<Hobby>();
	

	public static int randomChatTime(){
		Random rand = new Random();
		return rand.nextInt(LONGEST_TIME_FOR_CHAT_RESPONSE);
	}
	
	public static ArrayList<Hobby> getAllHobbies(){
		return allHobbies;
	}
	
	public static void setAllHobbies(ArrayList<Hobby> hobbyList){
		allHobbies = hobbyList; 
	}
	
	public static void setNUMBER_OF_HOBBIES_PER_COMMUNIST(int hobbies){
		NUMBER_OF_HOBBIES_PER_COMMUNIST = hobbies;
	}
	
	public static int getNUMBER_OF_HOBBIES_PER_COMMUNIST(){
		return NUMBER_OF_HOBBIES_PER_COMMUNIST;
	}
	
	
}
