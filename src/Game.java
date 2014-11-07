import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JFrame implements Constants {
	private JPanel framePanel;
	private CardLayout frameLayout;
	private JPanel[] gameScreens;
	private MainMenu mainMenu;
	private GamePortal gamePortal;
	private Battlefield battlefield; 
	
	public Game() {
		setTitle(GAME_TITLE);

		framePanel = new JPanel();
		frameLayout = new CardLayout();
		gameScreens = new JPanel[GAME_SCREEN_COUNT];
		
		// Initialize game screens
		mainMenu = new MainMenu(this);
		gamePortal = new GamePortal(this);
		battlefield = new Battlefield(this);
		
		init();
	}
	
	public void init() {
		framePanel.setLayout(frameLayout);
		
		// Add game screens to frame
		for(int i = 0; i < GAME_SCREEN_COUNT; i++) {
			gameScreens[i] = new JPanel();
		}
		
		// Assign display to each game screen 
		gameScreens[0].add(mainMenu);
		gameScreens[1].add(gamePortal);
		gameScreens[2].add(battlefield);
		
		// Add component to card layout table of names
		frameLayout.addLayoutComponent(gameScreens[0], MAIN_MENU);
		frameLayout.addLayoutComponent(gameScreens[1], GAME_PORTAL);
		frameLayout.addLayoutComponent(gameScreens[2], START_BATTLE);

		// Add each game screen to frame panel
		framePanel.add(gameScreens[0]);
		framePanel.add(gameScreens[1]);
		framePanel.add(gameScreens[2]);
		
		// Add frame components
		add(framePanel);
		
		// Set frame attributes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void changeScreen(String screenName) {
		switch(screenName){
			case MAIN_MENU		:	frameLayout.show(framePanel, MAIN_MENU);
									break;
			case GAME_PORTAL	:	frameLayout.show(framePanel, GAME_PORTAL);
									break;
			case START_BATTLE	:	frameLayout.show(framePanel, START_BATTLE);
									break;
		}
	}
	
	public static void main(String args[]) {
		new Game();
	}
}


