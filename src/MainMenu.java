import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenu extends JPanel implements Constants, ActionListener {
	public GameClient gameClient;
	public Player player;
	
	private JPanel mainMenuPanel;
	
	private JLabel characterLimitMessage;
	private JLabel hostLabel;
	private JLabel usernameLabel;
	private JLabel knightCountLabel;
	private JLabel priestCountLabel;
	private JLabel wizardCountLabel;
	private JLabel hunterCountLabel;
	
	private JTextField hostField;
	private JTextField usernameField;
	private JTextField knightCountField;
	private JTextField priestCountField;
	private JTextField wizardCountField;
	private JTextField hunterCountField;
	
	private JButton joinGameButton;
	private JButton quitButton;
	
	public MainMenu(GameClient gameClient, Player player) {
		this.gameClient = gameClient;
		this.player = player;
		
		characterLimitMessage = new JLabel("Maximum of 5 characters.");
		hostLabel = new JLabel("Host");
		usernameLabel = new JLabel("Username");
		knightCountLabel = new JLabel("Number of Knight/s");
		priestCountLabel = new JLabel("Number of Priest/s");
		wizardCountLabel = new JLabel("Number of Wizard/s");
		hunterCountLabel = new JLabel("Number of Hunter/s");
		
		hostField = new JTextField();
		usernameField = new JTextField();
		knightCountField = new JTextField();
		priestCountField = new JTextField();
		wizardCountField = new JTextField();
		hunterCountField = new JTextField();
		
		joinGameButton = new JButton("Join Game");
		joinGameButton.addActionListener(this);
		
		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		
		mainMenuPanel = new JPanel();
		
		mainMenuPanel.setPreferredSize(new Dimension(300, 300));
		mainMenuPanel.setLayout(new GridLayout(15, 2));
		
		mainMenuPanel.add(characterLimitMessage);
		mainMenuPanel.add(knightCountLabel);
		mainMenuPanel.add(knightCountField);
		mainMenuPanel.add(priestCountLabel);
		mainMenuPanel.add(priestCountField);
		mainMenuPanel.add(wizardCountLabel);
		mainMenuPanel.add(wizardCountField);
		mainMenuPanel.add(hunterCountLabel);
		mainMenuPanel.add(hunterCountField);
		mainMenuPanel.add(hostLabel);
		mainMenuPanel.add(hostField);
		mainMenuPanel.add(usernameLabel);
		mainMenuPanel.add(usernameField);
		mainMenuPanel.add(joinGameButton);
		mainMenuPanel.add(quitButton);
		
		add(mainMenuPanel);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + MAIN_MENU_BACKGROUND), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Join Game"	:	gameClient.sendToServer("CONNECT|" + "username=" + usernameField.getText());
									break;
			case "Quit"			:	System.exit(0);
									break;
		}
	}
}
