import java.util.HashMap;


public class GameState {
	private HashMap<String, Player> players = new HashMap<String, Player>();
	
	public GameState() { }
	
	public String toString() {
		return null;
	}
	
	public void updatePlayer(String name, Player player) {
		players.put(name, player);
	}
	
	public HashMap<String, Player> getPlayers() {
		return players;
	}
}
