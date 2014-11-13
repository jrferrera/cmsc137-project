import java.net.InetAddress;


public class Player {
	private GameClient gameClient;
	private String username;
	private int port;
	private InetAddress address;
	private ChatBox chatBox;
	
	public Player(String username, int port, InetAddress address) {
		setUsername(username);
		setPort(port);
		setAddress(address);
		
		chatBox = new ChatBox(gameClient, username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public ChatBox getChatBox() {
		return chatBox;
	}

	public void setChatBox(ChatBox chatBox) {
		this.chatBox = chatBox;
	}
	
	public String toString() {
		String playerData;
		
		playerData = "username=" + username + "|" + "port=" + port + "|" + "address=" + address;
		
		return playerData;
	}
}
