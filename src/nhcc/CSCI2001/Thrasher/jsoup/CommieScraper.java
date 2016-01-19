package nhcc.CSCI2001.Thrasher.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class CommieScraper {
	
	private String url = "https://en.wikipedia.org/wiki/List_of_contributors_to_Marxist_theory";
	private String firstName, lastName, userName, wikiUrl;
	private int age;
	private ArrayList<String> firstNames = new ArrayList<String>();
	private ArrayList<String> lastNames = new ArrayList<String>();
	private ArrayList<String> userNames = new ArrayList<String>();
	private ArrayList<Integer> ages = new ArrayList<Integer>();
	private ArrayList<String> wikiURLs = new ArrayList<String>();
	
		public CommieScraper() throws IOException{
			generate();
		}

	    public  void generate() throws IOException {
	       System.out.println("Welcome to Comrades! The First International Dating Site for Communists.");
	       System.out.println("Please take the time to fill out your registration.");

	       Document doc = Jsoup.connect(url).get();
	       Elements table = doc.select("table.wikitable");
	        
	        for (Element tableOfCommunists : table){
	        	for (Element row : tableOfCommunists.select("tr")){
	        		Elements tds = row.select("td");
	    
	        		if (tds.size()>0 && tds.get(0)!=null){
	        			generateNames(tds);
	        			generateAge(tds);
	        			generateUserName(tds);
	        			
	        			//add all the scraped information to array lists so the model can instantiate communist objects from the information
	        			firstNames.add(getFirstName());
	        			lastNames.add(getLastName());
	        			userNames.add(getUserName());
	        			ages.add(getAge());
	        			wikiURLs.add(wikiUrl);

	        			}
	        		}
	        	}
	        }
	    
	    //finds first and last names of all communists
	    private void generateNames(Elements tds){
	    	String fullNameString;
	    	String[] nameSplitter;
	    	fullNameString =cleanUpString(tds.get(0).text());
    		nameSplitter = fullNameString.split(" ");
    		setFirstName(nameSplitter[0]);
    		lastName ="";
    		
    		for(int i =1; i<nameSplitter.length; i++){
    			if(nameSplitter[i].length()>1){//cuts out middle initials
    			setLastName(lastName + " "+ nameSplitter[i]);
    			}
    		}	    
    	}
	    
	    //finds the age of all communists
	    private void generateAge(Elements tds){  	
	    	String yearsAlive = tds.get(4).text();	
	    	String [] yearSplitter;	
	    	int birthYear, currentYear;
	    	
	    	if(yearsAlive.indexOf('-')>0){
	    		yearSplitter = yearsAlive.split("-");
	    	}
	    	else{
	        	yearSplitter = yearsAlive.split("–");
	    	}
		
	    	yearsAlive.split("\\-");
    		birthYear = Integer.parseInt(yearSplitter[0]);
    		currentYear = Calendar.getInstance().get(Calendar.YEAR);
    		setAge(currentYear-birthYear);
	    }
	    
	    //removes annotations and other non-essential characters from screen names
	    private String cleanUpScreenName(String string){
	    	return string.replaceAll("\\p{P}","").replaceAll("\\d", "")
			.replaceAll("citation needed", "").replaceAll("discuss","").replaceAll("[^a-zA-Z ]", "").replaceAll(" ", "");
	    }
	    //removes annotations and other non-essential characters from first names and last names
	    private String cleanUpString(String string){
	    	return string.replaceAll("\\p{P}","").replaceAll("\\d", "")
			.replaceAll("citation needed", "").replaceAll("discuss","");
	    }
	   
	    //randomly creates a screen name for communist based on their first and last name, a random number, and a random word from their wiki article
	    public  void generateUserName(Elements tds) throws IOException {
	    	Random rand = new Random();
	    	setWikiUrl("https://en.wikipedia.org"+tds.select("a").attr("href"));
	    	Document doc = Jsoup.connect(wikiUrl).get();
	    	Elements links = doc.select("p");
	    	Element randomParagraph = links.get(rand.nextInt(links.size()));
	    	
	    	String [] splitParagraph = randomParagraph.text().split(" ");
	    	String randomWord = "";
	    	int randomWordIndex =0;
	    	
	    	if (splitParagraph.length > 2){
	    		while (randomWord.length()<3){
	    			randomWordIndex = rand.nextInt(splitParagraph.length);
	    			randomWord = splitParagraph[randomWordIndex];
	    		}
	    	}
	    	else{
	    		randomWordIndex = rand.nextInt(splitParagraph.length);
    			randomWord = splitParagraph[randomWordIndex];
	    	}
	    	 	
	    	cleanUpScreenName(randomWord);
	    	int setUp = rand.nextInt(4)+1;
	    	switch (setUp){	    	
	    	case 1:	    		
	    		setUserName(randomWord+getLastName());	    		
	    		break;	    		
	    	case 2:
	    		setUserName(randomWord+getFirstName());
	    		break;
	    	case 3:
	    		setUserName(getLastName()+randomWord);
	    	case 4:
	    		setUserName(getFirstName()+randomWord);	
	    	}
	    	setUserName(cleanUpScreenName(getUserName())+rand.nextInt(1000)+1);	
	    }
	    
	    //getters and setters
		public String getUrl() {
			return url;
		}


		public void setUrl(String url) {
			this.url = url;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getWikiUrl() {
			return wikiUrl;
		}


		public void setWikiUrl(String wikiUrl) {
			this.wikiUrl = wikiUrl;
		}


		public int getAge() {
			return age;
		}


		public void setAge(int age) {
			this.age = age;
		}


		public ArrayList<String> getFirstNames() {
			return firstNames;
		}


		public void setFirstNames(ArrayList<String> firstNames) {
			this.firstNames = firstNames;
		}


		public ArrayList<String> getLastNames() {
			return lastNames;
		}


		public void setLastNames(ArrayList<String> lastNames) {
			this.lastNames = lastNames;
		}


		public ArrayList<String> getUserNames() {
			return userNames;
		}


		public void setUserNames(ArrayList<String> userNames) {
			this.userNames = userNames;
		}


		public ArrayList<Integer> getAges() {
			return ages;
		}


		public void setAges(ArrayList<Integer> ages) {
			this.ages = ages;
		}


		public ArrayList<String> getWikiURLs() {
			return wikiURLs;
		}


		public void setWikiURLs(ArrayList<String> wikiURLs) {
			this.wikiURLs = wikiURLs;
		}

}
