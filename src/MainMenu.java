import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainMenu extends JPanel implements Constants, ActionListener {
	private Game ui;
	
	private JLabel characterLimitMessage;
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

	public MainMenu(Game ui) {
		this.ui = ui; 
		
		characterLimitMessage = new JLabel("Maximum of 5 characters.");
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
		
		joinGameButton = new JButton(GAME_PORTAL);
		
		init();
	}
	
	public void init() {
		setSize(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		joinGameButton.addActionListener(this);
		
		// Add the components
		add(characterLimitMessage);
		
		add(knightCountLabel);
		add(knightCountField);
		
		add(priestCountLabel);
		add(priestCountField);
		
		add(wizardCountLabel);
		add(wizardCountField);
		
		add(hunterCountLabel);
		add(hunterCountField);
		
		add(joinGameButton);
	}

	public void actionPerformed(ActionEvent e) {
		ui.changeScreen(e.getActionCommand());
	}
}
