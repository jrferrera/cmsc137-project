import java.awt.Color;
import java.awt.Dimension;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameClient extends JFrame implements Constants, Runnable {
	private Thread gameThread;
	private DatagramSocket clientSocket;
	private Player player;
	private String playerTurn;
	private ArrayList<String> playernames;
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	static GameState gameState;
	private String serverData;
	private HashMap<String, String> hashData;
	private String host;
	boolean connected;
	private GamePortal gp;
	private BattleScreen battleScreen;
	
	public BattleScreen getBattleScreen() {
		return battleScreen;
	}

	public void setBattleScreen(BattleScreen battleScreen) {
		this.battleScreen = battleScreen;
	}

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
		
		playernames=new ArrayList<String>();
		
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
				hashData = GameUtility.parser(serverData);
				
				JOptionPane.showMessageDialog(null, hashData.get("message"), "Connection Failed", JOptionPane.ERROR_MESSAGE);
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
			}else if(connected && serverData.startsWith("PLAYER_JOIN")){
				hashData = GameUtility.parser(serverData);
				if(!playernames.contains(hashData.get("username"))){
				playernames.add(hashData.get("username"));
				}
				gp.getConnectedPlayers().setText("Connected players:\n");
				for(int i=0;i<playernames.size();i++){
					gp.getConnectedPlayers().append(playernames.get(i)+"\n");
				}
			}else if(connected && serverData.startsWith("START_BATTLE")){
				battleScreen=new BattleScreen(player);
				this.changeScreen(battleScreen);
			}else if(connected && serverData.startsWith("MOVE")){
				System.out.println(serverData);
				hashData = GameUtility.parser(serverData);
				if(!hashData.get("username").equals(player.getUsername())){
				gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setXPosition(Integer.parseInt(hashData.get("xPosition")));
				gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setYPosition(Integer.parseInt(hashData.get("yPosition")));
				
				battleScreen.getBattlefield().refreshField();
				}
			}else if(connected && serverData.startsWith("ATTACK")){
				hashData = GameUtility.parser(serverData);
				gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setHp(Float.parseFloat(hashData.get("hp")));
				
				battleScreen.getBattlefield().refreshField();
			}else if(connected && serverData.startsWith("DEFEND")){
				hashData = GameUtility.parser(serverData);

				if(!hashData.get("username").equals(player.getUsername())){
				gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setOnDefend(Boolean.parseBoolean(hashData.get("isOnDefend")));
				}
				battleScreen.getBattlefield().refreshField();
			}else if(connected && serverData.startsWith("TURN")){
				hashData = GameUtility.parser(serverData);
				this.setPlayerTurn(hashData.get("username"));
				this.battleScreen.getGameStatistics().getPlayerTurn().setText("Player Turn:"+hashData.get("username"));
			}else if(connected && serverData.startsWith("RESET")){
				String playerName;
				player.movedCharacters=0;
				
				for(Iterator<?> i = gameState.getPlayers().keySet().iterator(); i.hasNext();) {
					playerName = (String)i.next();
					Player p = (Player)GameClient.gameState.getPlayers().get(playerName);
					p.movedCharacters=0;
					for(int j = 0; j < MAXIMUM_CHARACTER_COUNT; j++){
						Character temp = p.getCharacters()[j];
						temp.setOnDefend(false);
						temp.setState(MOVE);
					}
				}
			}else if(connected && serverData.startsWith("WINNER")){
				hashData = GameUtility.parser(serverData);
				JOptionPane op=new JOptionPane();
				op.showMessageDialog(battleScreen,"Player "+hashData.get("username")+" wins!" );
				System.exit(0);
			}else if(connected && serverData.startsWith("UPDATE_PLAYERS")) {
				hashData = GameUtility.parser(serverData);
				
				gameState.getPlayers().get(hashData.get("enemyUsername")).getCharacters()[Integer.parseInt(hashData.get("enemyCharacterIndex"))].setHp(Float.parseFloat(hashData.get("enemyHp")));
				gameState.getPlayers().get(hashData.get("username")).getCharacters()[Integer.parseInt(hashData.get("characterIndex"))].setMp(Float.parseFloat(hashData.get("mp")));
				
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

	public String getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(String playerTurn) {
		this.playerTurn = playerTurn;
	}
}


