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
	private String serverData;
	private HashMap<String, String> hashData;
	private String host;
	boolean connected;
	
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
			byte[] buffer = new byte[256];
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
					changeScreen(new GamePortal(player));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}else if(!connected && serverData.startsWith("CONNECTION_FAILED")) {
				JOptionPane.showMessageDialog(null, "Connection Failed", "Message", JOptionPane.ERROR_MESSAGE);
			}else if(connected && serverData.startsWith("CHAT_ALL")) {
				hashData = GameUtility.parser(serverData);
				
				player.getChatBox().appendMessage(hashData.get("chatMessage"));
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


