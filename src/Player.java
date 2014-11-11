import java.net.InetAddress;


public class Player {
	private String username;
	private int port;
	private InetAddress address;
	private ChatBox chatBox;
	
	public Player(String username, int port, InetAddress address) {
		this.setUsername(username);
		this.setPort(port);
		this.setAddress(address);
		
		chatBox = new ChatBox();
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
}
