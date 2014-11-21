import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenu extends JPanel implements Constants, ActionListener {
	public Player player;
	
	private JPanel mainMenuPanel;
	
	private JLabel hostLabel;
	private JLabel usernameLabel;
	private JLabel characterOneLabel;
	private JLabel characterTwoLabel;
	private JLabel characterThreeLabel;
	private JLabel characterFourLabel;
	private JLabel characterFiveLabel;
	
	private JTextField hostField;
	private JTextField usernameField;
	private JComboBox characterOneList;
	private JComboBox characterTwoList;
	private JComboBox characterThreeList;
	private JComboBox characterFourList;
	private JComboBox characterFiveList;
	
	private JButton joinGameButton;
	private JButton quitButton;
	
	public MainMenu(Player player) {
		this.player = player;
		
		hostLabel = new JLabel("Host");
		usernameLabel = new JLabel("Username");
		
		characterOneLabel = new JLabel("Character 1:");
		characterTwoLabel = new JLabel("Character 2:");
		characterThreeLabel = new JLabel("Character 3:");
		characterFourLabel = new JLabel("Character 4:");
		characterFiveLabel = new JLabel("Character 5:");
		
		hostField = new JTextField();
		usernameField = new JTextField();
		String[] characterChoices = { "Knight", "Priest", "Wizard", "Hunter" };
		characterOneList = new JComboBox(characterChoices);
		characterTwoList = new JComboBox(characterChoices);
		characterThreeList = new JComboBox(characterChoices);
		characterFourList = new JComboBox(characterChoices);
		characterFiveList = new JComboBox(characterChoices);
		
		joinGameButton = new JButton("Join Game");
		joinGameButton.addActionListener(this);
		
		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		
		mainMenuPanel = new JPanel();
		
		mainMenuPanel.setMaximumSize(new Dimension(300, 300));
		mainMenuPanel.setLayout(new GridLayout(17, 1));
		mainMenuPanel.setBackground(new Color(0f, 0f, 0f, 0f));
		
		mainMenuPanel.add(characterOneLabel);
		mainMenuPanel.add(characterOneList);
		
		mainMenuPanel.add(characterTwoLabel);
		mainMenuPanel.add(characterTwoList);
		
		mainMenuPanel.add(characterThreeLabel);
		mainMenuPanel.add(characterThreeList);
		
		mainMenuPanel.add(characterFourLabel);
		mainMenuPanel.add(characterFourList);
		
		mainMenuPanel.add(characterFiveLabel);
		mainMenuPanel.add(characterFiveList);
		
		mainMenuPanel.add(hostLabel);
		mainMenuPanel.add(hostField);
		mainMenuPanel.add(usernameLabel);
		mainMenuPanel.add(usernameField);
		mainMenuPanel.add(joinGameButton);
		mainMenuPanel.add(quitButton);
		
		
		setLayout(new BorderLayout(100, 100));
		setMaximumSize(new Dimension(600, 600));
		add(new JPanel(), BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.SOUTH);
		add(new JPanel(), BorderLayout.WEST);
		add(mainMenuPanel, BorderLayout.CENTER);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + MAIN_MENU_BACKGROUND), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "Join Game"	:	GameElement.gameClient.setHost(hostField.getText());
									GameElement.gameClient.sendToServer("CONNECT|" + "username=" + usernameField.getText() + "|character0=" + characterOneList.getSelectedItem() + "|character1=" + characterTwoList.getSelectedItem() + "|character2=" + characterThreeList.getSelectedItem() + "|character3=" + characterFourList.getSelectedItem() + "|character4=" + characterFiveList.getSelectedItem());
									break;
			case "Quit"			:	System.exit(0);
									break;
		}
	}
}
