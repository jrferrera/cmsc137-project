import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Iterator;

public class GameServer implements Runnable, Constants {
	private DatagramSocket serverSocket;
	private DatagramPacket packet;
	private byte[] buffer;
	private GameState gameState;
	private Thread gameThread;
	private int gameStateFlag;
	private int playerCount = 0;
	private String clientData;
	private Player player;
	
	public GameServer() {
		try {
			serverSocket = new DatagramSocket(PORT);
		}catch(Exception e) { }
		
		gameState = new GameState();
		
		gameThread = new Thread(this);
		gameStateFlag = WAITING_FOR_PLAYERS;
		gameThread.start();
		System.out.println("Server Started");
	}
	
	public void broadcast(String message) {
		String playerName;
		
		for(Iterator<?> i = gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player player = (Player)gameState.getPlayers().get(playerName);			
			send(player, message);
		}
	}
	
	public void send(Player player, String message){
		buffer = message.getBytes();
		packet = new DatagramPacket(buffer, buffer.length, player.getAddress(), player.getPort());
		
		try{
			serverSocket.send(packet);
		}catch(Exception e){ }
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
				case WAITING_FOR_PLAYERS	:	if(clientData.startsWith("CONNECT")) {
													System.out.println(clientData);
												}
				}
		}
	}

	public static void main(String[] args) {
		new GameServer();
	}
}
