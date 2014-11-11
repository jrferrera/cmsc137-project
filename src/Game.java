import java.awt.Dimension;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game extends JFrame implements Constants, Runnable {
	private Thread gameThread;
	private DatagramSocket clientSocket;
	private DatagramPacket packet;
	private byte[] buffer;
	private String serverData;
	private int gameStateFlag;
	boolean connected;
	
	public Game() {
		setTitle(GAME_TITLE);
		setPreferredSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));	
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
				GameUtility.changeScreen(new GamePortal());
			}else if(!connected && serverData.startsWith("CONNECTION FAILED")) {
				JOptionPane.showMessageDialog(null, "Connection Failed", "Message", JOptionPane.ERROR_MESSAGE);
			}else if(connected) {
				
			}
		}
	}
	
	public static void main(String args[]) {
		new Game();
	}
}


