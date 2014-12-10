import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameServer implements Runnable, Constants {
	private DatagramSocket serverSocket;
	private String clientData;
	
	private Thread gameThread;
	private GameState gameState;
	private int gameStateFlag;
	
	private Player player;
	private int playerCount = 0;
	private int readyPlayers=0;
	private HashMap<String, String> hashData;
	private String[] playerNames;
	private int turn=0;
	
	public GameServer() {
		try {
			serverSocket = new DatagramSocket(PORT);
		}catch(IOException e) {
			System.err.println("Could not listen on port: " + PORT);
            System.exit(-1);
		}
		
		clientData = "";
		gameState = new GameState();
		gameThread = new Thread(this);
		
		gameStateFlag = WAITING_FOR_PLAYERS;

		gameThread.start();
		System.out.println("Server Started");
	}
	
	public void sendToClient(Player player, String message) {
		byte[] buffer = new byte[256];
		buffer = message.getBytes();
		
		try{
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, player.getAddress(), player.getPort());
        	serverSocket.send(packet);
        }catch(Exception e) { }
	}
	
	public void broadcast(String message) {
		String playerName;
		
		for(Iterator<?> i = gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player player = (Player)gameState.getPlayers().get(playerName);			
			sendToClient(player, message);
		}
	}

	public void run() {
		while(true){
			byte[] buffer = new byte[512];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			
			try {
     			serverSocket.receive(packet);
     			clientData = new String(buffer);
     			System.out.println(clientData);
			} catch(Exception e){ }
			
			switch(gameStateFlag) {
				case WAITING_FOR_PLAYERS:
					if(clientData.startsWith("CONNECT")) {
						System.out.println(clientData);
						hashData = GameUtility.parser(clientData);
						
						try {
							player = new Player(hashData.get("username"), packet.getPort(), packet.getAddress());
							
							if(gameState.getPlayers().containsKey(hashData.get("username"))){
								sendToClient(player, "CONNECTION_FAILED|message=Username already exists.");
							}else{
							for(int index = 0; index < MAXIMUM_CHARACTER_COUNT; index++) {
								String characterType = hashData.get("character" + index);
								player.addCharacter(characterType, index);
							}
							
							if(playerCount == 0) {
								player.getCharacters()[0].setXPosition(0);
								player.getCharacters()[0].setYPosition(0);
								
								player.getCharacters()[1].setXPosition(2);
								player.getCharacters()[1].setYPosition(0);
								
								player.getCharacters()[2].setXPosition(1);
								player.getCharacters()[2].setYPosition(1);
								
								player.getCharacters()[3].setXPosition(0);
								player.getCharacters()[3].setYPosition(2);
								
								player.getCharacters()[4].setXPosition(2);
								player.getCharacters()[4].setYPosition(2);
							}else if(playerCount == 1) {
								player.getCharacters()[0].setXPosition(13);
								player.getCharacters()[0].setYPosition(13);
								
								player.getCharacters()[1].setXPosition(15);
								player.getCharacters()[1].setYPosition(13);
								
								player.getCharacters()[2].setXPosition(14);
								player.getCharacters()[2].setYPosition(14);
								
								player.getCharacters()[3].setXPosition(13);
								player.getCharacters()[3].setYPosition(15);
								
								player.getCharacters()[4].setXPosition(15);
								player.getCharacters()[4].setYPosition(15);
							}else if(playerCount == 2) {
								player.getCharacters()[0].setXPosition(13);
								player.getCharacters()[0].setYPosition(0);
								
								player.getCharacters()[1].setXPosition(15);
								player.getCharacters()[1].setYPosition(0);
								
								player.getCharacters()[2].setXPosition(14);
								player.getCharacters()[2].setYPosition(1);
								
								player.getCharacters()[3].setXPosition(13);
								player.getCharacters()[3].setYPosition(2);
								
								player.getCharacters()[4].setXPosition(15);
								player.getCharacters()[4].setYPosition(2);
							}else if(playerCount == 3) {
								player.getCharacters()[0].setXPosition(0);
								player.getCharacters()[0].setYPosition(13);
								
								player.getCharacters()[1].setXPosition(2);
								player.getCharacters()[1].setYPosition(13);
								
								player.getCharacters()[2].setXPosition(1);
								player.getCharacters()[2].setYPosition(14);
								
								player.getCharacters()[3].setXPosition(0);
								player.getCharacters()[3].setYPosition(15);
								
								player.getCharacters()[4].setXPosition(2);
								player.getCharacters()[4].setYPosition(15);
							}
							
							System.out.println(clientData);
							gameState.updatePlayer(player.getUsername(), player);
							
							playerCount++;
							sendToClient(player, "CONNECTED|"+"playerCount="+playerCount+"|"+ player.toString());
							
							if(playerCount >= MINIMUM_PLAYER_COUNT && playerCount <= MAXIMUM_PLAYER_COUNT) {
								broadcast("ENOUGH_PLAYERS");
							}
							String playerName;
							for(Iterator<?> i = gameState.getPlayers().keySet().iterator(); i.hasNext();) {
								playerName = (String)i.next();
								broadcast("PLAYER_JOIN|username="+playerName);
							}
							}
						}catch(Exception e) { }
						
					}else if(clientData.startsWith("CHAT_ALL")) {
						System.out.println("Message received");
						
						hashData = GameUtility.parser(clientData);
						broadcast("CHAT_ALL|" + "chatMessage=" + hashData.get("chatMessage"));
					}else if(clientData.startsWith("READY")){
						readyPlayers++;
						if(readyPlayers==playerCount){
							String playerName;
							this.playerNames = new String[playerCount];
							int k=0;
							for(Iterator<?> i = gameState.getPlayers().keySet().iterator(); i.hasNext();) {
								String message = "INITIALIZE_PLAYERS|";
								playerName = (String)i.next();
								playerNames[k]=playerName;
								k++;
								Player p = (Player)gameState.getPlayers().get(playerName);
								message += "username=" + playerName;
								
								for(int j = 0; j < MAXIMUM_CHARACTER_COUNT; j++){
									message += "|character" + j + "X=" + p.getCharacters()[j].getXPosition() + "|character" + j + "Y=" + p.getCharacters()[j].getYPosition() + "|character" + j + "=" + p.getCharacters()[j].getClass().toString().substring(6);
									System.out.println(p.getUsername()+" "+p.getCharacters()[j].getXPosition()+" "+p.getCharacters()[j].getYPosition());
								}
								
								broadcast(message);
							}
							
							broadcast("START_BATTLE|");
							broadcast("TURN|username="+playerNames[turn]);
						}
					}else if(clientData.startsWith("END_TURN")){
						turn++;
						if(turn==playerCount){
							turn=0;
							broadcast("RESET");
						}
						
						broadcast("TURN|username="+playerNames[turn]);
					}else if(clientData.startsWith("MOVE")){
						hashData = GameUtility.parser(clientData);
						gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setXPosition(Integer.parseInt(hashData.get("xPosition")));
						gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setYPosition(Integer.parseInt(hashData.get("yPosition")));
						
						broadcast(clientData);
					}else if(clientData.startsWith("ATTACK")){
						hashData = GameUtility.parser(clientData);
						gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setHp(Float.parseFloat(hashData.get("hp")));
						if(gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].getHp()<=0.0){
							broadcast("KILL|username="+hashData.get("username")+"|characterIndex="+hashData.get("characterIndex"));
							gameState.getPlayers().get(hashData.get("username")).aliveCharacters--;
						}
						
						broadcast(clientData);
						String winner=checkWinner();
						if(!winner.equals("none")){
							broadcast("WINNER|username="+winner);
						}
					}else if(clientData.startsWith("DEFEND")){
						hashData = GameUtility.parser(clientData);
						gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setOnDefend(Boolean.parseBoolean(hashData.get("isOnDefend")));
						
						broadcast(clientData);
					}else if(clientData.startsWith("UPDATE_PLAYERS")){
						hashData = GameUtility.parser(clientData);
						
						gameState.getPlayers().get(hashData.get("enemyUsername")).getCharacters()[Integer.parseInt(hashData.get("enemyCharacterIndex"))].setHp(Float.parseFloat(hashData.get("enemyHp")));
						gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setMp(Float.parseFloat(hashData.get("mp")));
					
						if(gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].getHp()<=0.0){
							gameState.getPlayers().get(hashData.get("enemyUsername")).aliveCharacters--;
							broadcast("KILL|username="+hashData.get("enemyUsername")+"|characterIndex="+hashData.get("enemyCharacterIndex"));
						}
						
						broadcast(clientData);
						String winner=checkWinner();
						if(!winner.equals("none")){
							broadcast("WINNER|username="+winner);
						}
						
						broadcast(clientData);
					}
			}
		}
	}
	
	public String checkWinner(){
		String playerName;
		int playersRemaining=0;
		String winner = "";
		for(Iterator<?> i = gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player p = (Player)gameState.getPlayers().get(playerName);
			
			if(p.aliveCharacters>0){
				playersRemaining++;
				winner=p.getUsername();
			}
		}
		if(playersRemaining==1){
			return winner;
		}
		else{
			return "none";
		}
	}

	public static void main(String[] args) {
		new GameServer();
	}
}
