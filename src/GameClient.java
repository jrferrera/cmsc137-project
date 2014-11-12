import java.awt.Dimension;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameClient extends JFrame implements Constants, Runnable {
	private Thread gameThread;
	private DatagramSocket clientSocket;
	private DatagramPacket packet;
	private byte[] buffer;
	private String serverData;
	private HashMap<String, String> hashData;
	boolean connected;
	
	public GameClient() {
		setTitle(GAME_TITLE);
		setMinimumSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		connected = false;
		serverData = "";
		
		GameUtility.gameFrame = this;
		gameThread = new Thread(this);
		GameUtility.changeScreen(new MainMenu());
		
		gameThread.start();
	}	

	public void run() {
		while(true){
			buffer = new byte[256];
			packet = new DatagramPacket(buffer, buffer.length);			
			
			try {
				clientSocket.receive(packet);
     			serverData = new String(buffer);
			} catch(Exception e){ }
			
			if(!connected && serverData.startsWith("CONNECTED")) {
				connected = true;
				
				hashData = GameUtility.parser(serverData);
				InetAddress address;
				try {
					address = InetAddress.getByAddress(hashData.get("address"), buffer);
					GameUtility.player = new Player(hashData.get("username"), Integer.parseInt(hashData.get("port")), address);
				} catch (UnknownHostException e) { }
			}else if(!connected && serverData.startsWith("CONNECTION_FAILED")) {
				JOptionPane.showMessageDialog(null, "Connection Failed", "Message", JOptionPane.ERROR_MESSAGE);
			}else if(connected && serverData.startsWith("CHAT_ALL")) {
				hashData = GameUtility.parser(serverData);
				
				GameUtility.player.getChatBox().appendMessage(GameUtility.player.getUsername() + ": " + hashData.get("chatMessage"));
			}else if(connected) {
				GameUtility.changeScreen(new GamePortal());
			}
		}
	}
	
	public static void main(String args[]) {
		new GameClient();
	}
}


