import java.net.InetAddress;

import javax.xml.stream.events.Characters;


public class Player implements Constants {
	private String username;
	private int port;
	private InetAddress address;
	private ChatBox chatBox;
	private Character[] characters;
	public int movedCharacters=0;
	public int aliveCharacters=MAXIMUM_CHARACTER_COUNT;
	private int enemyCharactersDefeated;
	private int ownCharacterDeathCount;
	private int score;
	private GameStatistics gameStatistics;
	private boolean isDefeated=false;
	
	public Player(String username, int port, InetAddress address) {
		setUsername(username);
		setPort(port);
		setAddress(address);
		setScore(0);
		
		characters = new Character[MAXIMUM_CHARACTER_COUNT];
		chatBox = new ChatBox(username);
		gameStatistics = new GameStatistics(this);
		
		setEnemyCharactersDefeated(0);
		setOwnCharacterDeathCount(0);
	}
	
	public GameStatistics getGameStatistics() {
		return gameStatistics;
	}

	public void setGameStatistics(GameStatistics gameStatistics) {
		this.gameStatistics = gameStatistics;
	}

	public Player(String username) {
		setUsername(username);
		
		characters = new Character[MAXIMUM_CHARACTER_COUNT];
		
		setEnemyCharactersDefeated(0);
		setOwnCharacterDeathCount(0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public ChatBox getChatBox() {
		return chatBox;
	}

	public void setChatBox(ChatBox chatBox) {
		this.chatBox = chatBox;
	}
	
	public void addCharacter(String characterType, int index) {
		if(characterType.equals("Knight")) {
			this.characters[index] = new Knight();
		}else if(characterType.equals("Priest")) {
			this.characters[index] = new Priest();
		}else if(characterType.equals("Hunter")) {
			this.characters[index] = new Hunter();
		}else if(characterType.equals("Wizard")) {
			this.characters[index] = new Wizard();
		}
		this.characters[index].setOwner(this);
		this.characters[index].setCharacterIndex(index);
	}
	
	public String toString() {
		String playerData;
		
		playerData = "port=" + port + "|" + "address=" + address + "|" + "username=" + username;
		
		return playerData;
	}
	
	public String getPlayerStringData() {
		String playerData;
		
		playerData = "username=" + username;
		
		
		
		return playerData;
	}

	public Character[] getCharacters() {
		return characters;
	}

	public void setCharacters(Character[] character) {
		this.characters = character;
	}

	public int getOwnCharacterDeathCount() {
		return ownCharacterDeathCount;
	}

	public void setOwnCharacterDeathCount(int ownCharacterDeathCount) {
		this.ownCharacterDeathCount = ownCharacterDeathCount;
	}

	public int getEnemyCharactersDefeated() {
		return enemyCharactersDefeated;
	}

	public void setEnemyCharactersDefeated(int enemyCharactersDefeated) {
		this.enemyCharactersDefeated = enemyCharactersDefeated;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isDefeated() {
		return isDefeated;
	}

	public void setDefeated(boolean isDefeated) {
		this.isDefeated = isDefeated;
	}
	
}
