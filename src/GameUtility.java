import java.awt.Image;
import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameUtility implements Constants{
	public static JFrame gameFrame;
	
	public static InetAddress address;
	public static DatagramPacket packet;
	public static DatagramSocket socket;
	public static byte[] buffer;
	
	public static Image getImage(String filename) {
		try{
			return ImageIO.read(new File(filename));
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void changeScreen(JPanel gameScreen) {
		gameFrame.setContentPane(gameScreen);
		
		gameScreen.setFocusable(true);
		gameScreen.requestFocus();
		
		gameFrame.pack();
		GameUtility.gameFrame.repaint();
	}
	
	public static void send(String message) {
		buffer = new byte[256];
		buffer = message.getBytes();
		
		try{
			address = InetAddress.getByName(GAME_SERVER);
			packet = new DatagramPacket(buffer, buffer.length, address, PORT);
			socket = new DatagramSocket();
			
        	socket.send(packet);
        }catch(Exception e) { }
	}
	
	public static void receiveData() {
		try{
			socket = new DatagramSocket();
			buffer = new byte[256];
			packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
		}catch(Exception e) { }
	}
}