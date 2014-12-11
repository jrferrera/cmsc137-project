import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameManual extends JPanel implements ActionListener{
	private JButton instructionsButton;
	private JButton characterClassesButton;
	private JButton backToMainMenuButton;
	private JButton previousButton;
	private JButton nextButton;
	
	private CardLayout gameManualPanelLayout;
	private CardLayout characterClassesPanelLayout;
	
	private JPanel gameManualPanel;
	private JPanel instructionsMainPanel;
	private JPanel characterClassesMainPanel;
	
	public GameManual(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		gameManualPanelLayout = new CardLayout();
		gameManualPanel = new JPanel(gameManualPanelLayout);
		
		instructionsMainPanel = new JPanel();
		instructionsMainPanel.setLayout(new BoxLayout(instructionsMainPanel, BoxLayout.Y_AXIS));
		initializeInstuctions();
		
		characterClassesPanelLayout = new CardLayout();
		characterClassesMainPanel = new JPanel();
		characterClassesMainPanel.setLayout(new BoxLayout(characterClassesMainPanel, BoxLayout.Y_AXIS));
		initializeCharacterClasses();
		
		backToMainMenuButton = new JButton("Back to Main Menu");
		backToMainMenuButton.addActionListener(this);
		
		previousButton = new JButton();
		previousButton.addActionListener(this);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		
		instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(this);
		
		characterClassesButton = new JButton("Character Classes");
		characterClassesButton.addActionListener(this);
		
		gameManualPanel.add(instructionsMainPanel, "Instructions");
		gameManualPanel.add(characterClassesMainPanel, "Character Classes");
		
		add(gameManualPanel);
		add(instructionsButton);
		add(characterClassesButton);
		add(backToMainMenuButton);
	}
	
	public void initializeInstuctions() {
		int numberOfPages = 3;
		
		JPanel[] instructions = new JPanel[numberOfPages];
		JPanel navigationPanel = new JPanel();
		
		final CardLayout instructionsPanelLayout = new CardLayout();
		final JPanel instructionsPanel = new JPanel(instructionsPanelLayout);

		for(int i = 0; i < numberOfPages; i++){
			instructions[i] = new JPanel();
		}
		
		instructions[0].setLayout(new BoxLayout(instructions[0], BoxLayout.Y_AXIS));
		instructions[0].add(new JLabel("Overview"));
		instructions[0].add(new JLabel("Ragnatactics is a multi-player role-playing game based on Ragnarok Online and Final Fantasy Tactics."));
		instructions[0].add(new JLabel("Each player can choose 5 characters with different classes."));
		instructions[0].add(new JLabel("The minimum number of players is 2."));
		instructions[0].add(new JLabel("The maximum number of players is 4."));
		instructions[0].add(new JLabel("It is turn-based where each player can move all characters each turn."));
		instructions[0].add(new JLabel("The goal of each player is to defeat all the other characters."));
		
		instructions[1].setLayout(new BoxLayout(instructions[1], BoxLayout.Y_AXIS));
		instructions[1].add(new JLabel("How To Play"));
		instructions[1].add(new JLabel("1.) Select your characters. For character descriptions, see Character Classes."));
		instructions[1].add(new JLabel("2.) Type the IP address of the server in the Host field."));
		instructions[1].add(new JLabel("3.) Type your username in the Username field."));
		instructions[1].add(new JLabel("4.) Click Join Game Button."));
		instructions[1].add(new JLabel("5.) Wait until there are at least two players."));
		instructions[1].add(new JLabel("If there are at least 2 players, the Warp to Battlefield Button will be enabled."));
		instructions[1].add(new JLabel("Note that the maximum number of players is 4."));
		instructions[1].add(new JLabel("6.) Once enabled, click Warp to Battlefield Button."));
		instructions[1].add(new JLabel("7.) In the battlefield screen, see the upper left corner to see the player taking the turn."));
		instructions[1].add(new JLabel("8.) Wait until it's your turn."));
		
		instructions[2].setLayout(new BoxLayout(instructions[2], BoxLayout.Y_AXIS));
		instructions[2].add(new JLabel("Player Controls"));
		instructions[2].add(new JLabel("1.) If it's your turn. Click any of your character."));
		instructions[2].add(new JLabel("2.) You can move your character within the highlighted range."));
		instructions[2].add(new JLabel("If you want your character to stay in its current position, click the character itself."));
		instructions[2].add(new JLabel("3.) Press A to attack or use the skill hotkeys to use a skill."));
		instructions[2].add(new JLabel("See Character Classes for the skill hotkeys."));
		instructions[2].add(new JLabel("4.) Select the character within the range."));
		instructions[2].add(new JLabel("Note: You can see the character profile below the chat box/rightmost part of the screen."));
		instructions[2].add(new JLabel("Warning: There is no turning back once you selected your character."));
		instructions[2].add(new JLabel("The game does not allow changing characters or actions."));
		
		JButton previous = new JButton("Previous");
		previous.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				instructionsPanelLayout.previous(instructionsPanel);
			}
		});
		
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				instructionsPanelLayout.next(instructionsPanel);
			}
		});
		
		navigationPanel.add(previous);
		navigationPanel.add(next);
		
		for(int i = 0; i < numberOfPages; i++) {
			instructionsPanel.add(instructions[i]);
		}
		
		instructionsMainPanel.add(navigationPanel);
		instructionsMainPanel.add(instructionsPanel);
	}
	
	public void initializeCharacterClasses() {
		int numberOfPages = 4;
		
		JPanel[] characterClasses = new JPanel[numberOfPages];
		JPanel navigationPanel = new JPanel();
		
		final CardLayout characterClassesPanelLayout = new CardLayout();
		final JPanel characterClassesPanel = new JPanel(characterClassesPanelLayout);

		for(int i = 0; i < numberOfPages; i++){
			characterClasses[i] = new JPanel();
		}
		
		characterClasses[0].setLayout(new BoxLayout(characterClasses[0], BoxLayout.Y_AXIS));
		characterClasses[0].add(new JLabel("Knight"));
		characterClasses[0].add(new JLabel("This is a character class for melee combat."));
		characterClasses[0].add(new JLabel("HP: 200"));
		characterClasses[0].add(new JLabel("MP: 50"));
		characterClasses[0].add(new JLabel("Attack: 20"));
		characterClasses[0].add(new JLabel("Defense: 15"));
		characterClasses[0].add(new JLabel("Walk Range: 4 cells"));
		characterClasses[0].add(new JLabel("Attack Range: 1 cell"));
		
		characterClasses[1].setLayout(new BoxLayout(characterClasses[1], BoxLayout.Y_AXIS));
		characterClasses[1].add(new JLabel("Hunter"));
		characterClasses[1].add(new JLabel("This is a character class for long-range combat."));
		characterClasses[1].add(new JLabel("HP: 180"));
		characterClasses[1].add(new JLabel("MP: 60"));
		characterClasses[1].add(new JLabel("Attack: 15"));
		characterClasses[1].add(new JLabel("Defense: 11"));
		characterClasses[1].add(new JLabel("Walk Range: 3 cells"));
		characterClasses[1].add(new JLabel("Attack Range: 3 cells"));
		
		characterClasses[2].setLayout(new BoxLayout(characterClasses[2], BoxLayout.Y_AXIS));
		characterClasses[2].add(new JLabel("Wizard"));
		characterClasses[2].add(new JLabel("This is a character class for long-range magic combat."));
		characterClasses[2].add(new JLabel("HP: 100"));
		characterClasses[2].add(new JLabel("MP: 180"));
		characterClasses[2].add(new JLabel("Attack: 1"));
		characterClasses[2].add(new JLabel("Defense: 8"));
		characterClasses[2].add(new JLabel("Walk Range: 2 cells"));
		characterClasses[2].add(new JLabel("Attack Range: 1 cell"));
		characterClasses[2].add(new JLabel("Skills"));
		characterClasses[2].add(new JLabel("F1 - Firaga"));
		characterClasses[2].add(new JLabel("     MP Cost: 15"));
		characterClasses[2].add(new JLabel("     Damage: 28"));
		characterClasses[2].add(new JLabel("     Range: 3"));
		characterClasses[2].add(new JLabel("F2 - Blizzaga"));
		characterClasses[2].add(new JLabel("     MP Cost: 13"));
		characterClasses[2].add(new JLabel("     Damage: 25"));
		characterClasses[2].add(new JLabel("     Range: 3"));
		characterClasses[2].add(new JLabel("F3 - Thundaga"));
		characterClasses[2].add(new JLabel("     MP Cost: 17"));
		characterClasses[2].add(new JLabel("     Damage: 30"));
		characterClasses[2].add(new JLabel("     Range: 3"));
		
		characterClasses[3].setLayout(new BoxLayout(characterClasses[3], BoxLayout.Y_AXIS));
		characterClasses[3].add(new JLabel("Priest"));
		characterClasses[3].add(new JLabel("This is a character class for support"));
		characterClasses[3].add(new JLabel("HP: 150"));
		characterClasses[3].add(new JLabel("MP: 200"));
		characterClasses[3].add(new JLabel("Attack: 1"));
		characterClasses[3].add(new JLabel("Defense: 10"));
		characterClasses[3].add(new JLabel("Walk Range: 2 cells"));
		characterClasses[3].add(new JLabel("Attack Range: 1 cell"));
		characterClasses[3].add(new JLabel("Skills"));
		characterClasses[3].add(new JLabel("F1 - Curaga"));
		characterClasses[3].add(new JLabel("     MP Cost: 30"));
		characterClasses[3].add(new JLabel("     Heal Amount: 25"));
		characterClasses[3].add(new JLabel("     Range: 3"));
		
		JButton previous = new JButton("Previous");
		previous.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				characterClassesPanelLayout.previous(characterClassesPanel);
			}
		});
		
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				characterClassesPanelLayout.next(characterClassesPanel);
			}
		});
		
		navigationPanel.add(previous);
		navigationPanel.add(next);
		
		for(int i = 0; i < numberOfPages; i++) {
			characterClassesPanel.add(characterClasses[i]);
		}
		
		characterClassesMainPanel.add(navigationPanel);
		characterClassesMainPanel.add(characterClassesPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl;
		
		if(e.getSource() == backToMainMenuButton) {
			GameElement.gameClient.changeScreen(new MainMenu(GameElement.gameClient.getPlayer()));
		}else if(e.getSource() == instructionsButton) {
			cl = (CardLayout) gameManualPanel.getLayout();
			
			cl.show(gameManualPanel, "Instructions");
		}else if(e.getSource() == characterClassesButton) {
			cl = (CardLayout) gameManualPanel.getLayout();
			
			cl.show(gameManualPanel, "Character Classes");
		}
	}

}
