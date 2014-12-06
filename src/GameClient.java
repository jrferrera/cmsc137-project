import java.awt.Dimension;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameClient extends JFrame implements Constants, Runnable {
	private Thread gameThread;
	private DatagramSocket clientSocket;
	private Player player;
	static GameState gameState;
	private String serverData;
	private HashMap<String, String> hashData;
	private String host;
	boolean connected;
	private GamePortal gp;
	
	public GameClient() {
		setTitle(GAME_TITLE);
		setMinimumSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) { }
		
		host = "127.0.0.1";
		connected = false;
		serverData = "";
		
		gameState = new GameState();
		gameThread = new Thread(this);
		changeScreen(new MainMenu(player));
		
		gameThread.start();
	}
	
	public void changeScreen(JPanel gameScreen) {
		setContentPane(gameScreen);
		setFocusable(true);
		requestFocus();
		pack();
		repaint();
	}
	
	public void sendToServer(String message){
		byte[] buffer = new byte[256];
		buffer = message.getBytes();
		
		try{
			InetAddress address = InetAddress.getByName(host);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
			
        	clientSocket.send(packet);
        }catch(Exception e) { }
	}

	public void run() {
		while(true){
			byte[] buffer = new byte[512];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);			
			
			try {
				clientSocket.receive(packet);
				System.out.println(new String(packet.getData()));
				serverData = new String(buffer);
			} catch(Exception e){ }
			
			if(!connected && serverData.startsWith("CONNECTED")) {
				connected = true;
				hashData = GameUtility.parser(serverData);
				
				InetAddress address;
				try {
					address = InetAddress.getByName(hashData.get("address").substring(1));
					player = new Player(hashData.get("username"), Integer.parseInt(hashData.get("port")), address);
					setTitle(GAME_TITLE + "::" + player.getUsername());
					gp=new GamePortal(player);
					changeScreen(gp);
					if(Integer.parseInt(hashData.get("playerCount"))>1){
						gp.getStartBattleButton().setEnabled(true);
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}else if(!connected && serverData.startsWith("CONNECTION_FAILED")) {
				JOptionPane.showMessageDialog(null, "Connection Failed", "Message", JOptionPane.ERROR_MESSAGE);
			}else if(connected && serverData.startsWith("CHAT_ALL")) {
				hashData = GameUtility.parser(serverData);
				
				player.getChatBox().appendMessage(hashData.get("chatMessage"));
			}else if(connected && serverData.startsWith("ENOUGH_PLAYERS")){
				gp.getStartBattleButton().setEnabled(true);
			}else if(connected && serverData.startsWith("INITIALIZE_PLAYERS")){
				hashData = GameUtility.parser(serverData);
				
				Player p = new Player(hashData.get("username"));
				System.out.println(hashData);
				for(int i = 0; i < MAXIMUM_CHARACTER_COUNT; i++) {
					p.addCharacter(hashData.get("character" + i), i);
					p.getCharacters()[i].setXPosition(Integer.parseInt(hashData.get("character" + i + "X")));
					p.getCharacters()[i].setYPosition(Integer.parseInt(hashData.get("character" + i + "Y")));
				}
				
				gameState.updatePlayer(p.getUsername(), p);
			}else if(connected && serverData.startsWith("START_BATTLE")){
				this.changeScreen(new BattleScreen(player));
			}else if(connected) {
				System.out.println("Connected");
			}
		}
	}
	
	public String getHost() {
		return this.host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public static void main(String args[]) {
		GameElement.gameClient = new GameClient();
	}
}


