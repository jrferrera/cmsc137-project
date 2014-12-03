
public interface Constants {
	// Game Name
	public static final String GAME_TITLE = "Game";
	
	// Server
	public static final String SERVER = "localhost";
		
	// Port Number
	public static final int PORT = 4445;
	
	// Game Screen Width
	public static final int GAME_SCREEN_WIDTH = 600;
	
	// Game Screen Height
	public static final int GAME_SCREEN_HEIGHT = 600;
	
	// Game State Flags
	public static final int WAITING_FOR_PLAYERS = 1;
	public static final int START_BATTLE = 2;
	
	//Minimum Number of Players
	public static final int MINIMUM_PLAYER_COUNT = 2;
	
	//Maximum Number of Players
	public static final int MAXIMUM_PLAYER_COUNT = 4;
	
	//Maximum Number of Character per Player
	public static final int MAXIMUM_CHARACTER_COUNT = 5;
	
	// File Locations
	public static final String BACKGROUND_LOCATION = "assets/images/backgrounds";
	public static final String ICON_LOCATION = "assets/images/icons";
	public static final String BUTTON_LOCATION = "assets/images/buttons";
	public static final String CHARACTER_LOCATION = "assets/images/characters";
	
	// Background Images
	public static final String MAIN_MENU_BACKGROUND = "MainMenuBackground.jpg";
	public static final String GAME_PORTAL_BACKGROUND = "MainMenuBackground.jpg";
	public static final String BATTLEFIELD_BACKGROUND = "BattlefieldBackground.jpg";
	
	// Character Images
	public static final String KNIGHT_IMAGE = "knight.png";
	public static final String PRIEST_IMAGE = "priest.png";
	public static final String WIZARD_IMAGE = "wizard.png";
	public static final String HUNTER_IMAGE = "hunter.png";
}
