import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class GameServer implements Runnable, Constants {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] buffer;
	
	public static void main(String[] args) {
		new GameServer();
	}

	public void run() {
		while(true){
			try{
				buffer = new byte[256];
				
				packet = new DatagramPacket(buffer, buffer.length);
				socket = new DatagramSocket(PORT);
     			socket.receive(packet);
     			
     			
			}catch(Exception e){ }
		}
	}

}
