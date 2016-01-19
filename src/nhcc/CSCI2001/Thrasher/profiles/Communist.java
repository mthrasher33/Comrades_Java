package nhcc.CSCI2001.Thrasher.profiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Communist extends Profile{
	
	private String comradeMatch = "";
	private String wikiURL; //the web address of their wiki page

	public Communist(String firstName, String lastName, String userName, int age, ArrayList<Hobby> hobbies, String wikiURL) {
		super(firstName, lastName, userName, age, hobbies);
		this.wikiURL = wikiURL;		
	}
	//selects a random sentence from the communist's wikipedia page and sends it as a chat
	public String chat() throws IOException{
		ArrayList<String> paragraphSplitter = new ArrayList<>();
		System.out.printf("%s sent you a message!%n", getUserName());

		Document doc = Jsoup.connect(getWikiURL()).get();
		Random rand = new Random();
		Elements paragraphs = doc.select("p");
		String randomParagraph = paragraphs.get(rand.nextInt(paragraphs.size())).text();
       
       for(String sentence : randomParagraph.split("(?<=[.!?])\\s* ")){
    	   paragraphSplitter.add(sentence);
       }
       
       String chosenSentence = paragraphSplitter.get(rand.nextInt(paragraphSplitter.size())).replaceAll("\\[.*?\\]", " ");
       return chosenSentence;		 
 
	}
	
	//getters and setters
	public String getWikiURL(){
		return wikiURL;
	}
	
	public void setWikiURL(String string){
		this.wikiURL = string;
	}
	
	public String getComradeMatch(){
		return comradeMatch;
	}
	
	public void setComradeMatch(String isAComrade){
		this.comradeMatch = isAComrade;
	}
}
