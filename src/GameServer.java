import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;

public class GameServer implements Runnable, Constants {
	private DatagramSocket serverSocket;
	private DatagramPacket packet;
	private InetAddress address;
	private byte[] buffer;
	private GameState gameState;
	private Thread gameThread;
	private int gameStateFlag;
	private int playerCount = 0;
	private String clientData;
	private Player player;
	private HashMap<String, String> hashData;
	
	public GameServer() {
		try {
			serverSocket = new DatagramSocket(PORT);
			serverSocket.setSoTimeout(100);
		}catch(IOException e) {
			System.err.println("Could not listen on port: " + PORT);
            System.exit(-1);
		}catch(Exception e) { }
		
		gameState = new GameState();
		
		gameThread = new Thread(this);
		clientData = "";
		gameStateFlag = WAITING_FOR_PLAYERS;
		gameThread.start();
		System.out.println("Server Started");
	}
	
	public void broadcast(String message) {
		String playerName;
		
		for(Iterator<?> i = gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player player = (Player)gameState.getPlayers().get(playerName);			
			GameUtility.sendToClient(player, message);
		}
	}

	public void run() {
		while(true){
			buffer = new byte[256];
			packet = new DatagramPacket(buffer, buffer.length);			

			try {
     			serverSocket.receive(packet);
     			clientData = new String(buffer);
			} catch(Exception e){ }
			
			switch(gameStateFlag) {
				case WAITING_FOR_PLAYERS:
					if(clientData.startsWith("CONNECT")) {
						System.out.println(clientData);
						hashData = GameUtility.parser(clientData);
						
						try {
							player = new Player(hashData.get("username"), packet.getPort(), packet.getAddress());
							gameState.updatePlayer(player.getUsername(), player);
							playerCount++;
							
							GameUtility.sendToClient(player, "CONNECTED|" + player.toString());
						}catch(Exception e) { }
						
						if(playerCount == MINIMUM_PLAYER_COUNT || playerCount == MAXIMUM_PLAYER_COUNT) {
							
						}
					}else if(clientData.startsWith("CHAT_ALL")) {
						System.out.println("Message received");
						
						hashData = GameUtility.parser(clientData);
						broadcast("CHAT_ALL|" + "chatMessage=" + hashData.get("chatMessage"));
					}
			}
		}
	}

	public static void main(String[] args) {
		new GameServer();
	}
}
