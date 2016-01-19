package nhcc.CSCI2001.Thrasher.profiles;

public enum HobbyDirectory {
	ACTING("Acting"),
	BATON_TWIRLING("Baton twirling"),
	BIKING("Biking"),
	BOARD_GAMES("Board Games"),
	CABARET("Cabaret"),
	CALLIGRAPHY("Calligraphy"),
	CANDLE_MAKING("Candle making"),
	COOKING("Cooking"),
	COUPONING("Couponing"),
	CREATIVE_WRITING("Creative writing"),
	DOCUMENTARY_FILM_WATHING("Documentary film watching"),
	EMBROIDERY("Embroidery"),
	FLAG_FOOTBALL("Flag football"),
	GAMBLING("Gambling"),
	GENEALOGY("Genealogy"),
	GLASSBLOWING("Glassblowing"),
	HANG_GLIDING ("Hang gliding"),
	HOMEBREWING("Homebrewing"),
	ICE_SKATING("Ice skating"),
	JIGSAW_PUZZLES("Jigsaw puzzles"),
	JOGGING("Jogging"),
	KAYAKING("Kayaking"),
	KITE_FLYING("Kite flying"),
	READING("Reading"),
	SAND_ART("Sand art"),
	SCOUTING("Scouting"),
	SNOWSHOEING("Snowshoeing"),
	TAEKWONDO("Taekwondo"),
	TAXIDERMY("Taxidermy"),
	VIDEO_GAMES("Video games"),
	WHITTLING("Whittling"),
	YOYOING("Yo-yoing");
	
	
	private String name;

	HobbyDirectory(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
