import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class BattleScreen extends JPanel implements Constants {
	private Player player;
	private JPanel battleScreenPanel;
	private JPanel fieldPanel;
	private Battlefield battlefield;
	private GameStatistics gameStatistics;
	private GameMenu gameMenu;
	private CharacterProfile characterProfile;
		
	public CharacterProfile getCharacterProfile() {
		return characterProfile;
	}

	public void setCharacterProfile(CharacterProfile characterProfile) {
		this.characterProfile = characterProfile;
	}

	public GameStatistics getGameStatistics() {
		return gameStatistics;
	}

	public void setGameStatistics(GameStatistics gameStatistics) {
		this.gameStatistics = gameStatistics;
	}

	public BattleScreen(Player player) {
		this.player = player;
		gameMenu = new GameMenu();
		battlefield = new Battlefield(player);
		gameStatistics = new GameStatistics(player);
		characterProfile = new CharacterProfile();
		
		setPreferredSize(new Dimension(800, 700));
		this.setLayout(new BorderLayout());
		
		JPanel battleScreenSidePanel=new JPanel();
		battleScreenSidePanel.setPreferredSize(new Dimension(160,300));
		battleScreenSidePanel.add(player.getChatBox());
		battleScreenSidePanel.add(characterProfile);
		add(battleScreenSidePanel,BorderLayout.EAST);
		add(battlefield,BorderLayout.CENTER);
		add(gameMenu, BorderLayout.SOUTH);
		add(gameStatistics, BorderLayout.NORTH);
	}
	
	public Battlefield getBattlefield() {
		return battlefield;
	}

	public void setBattlefield(Battlefield battlefield) {
		this.battlefield = battlefield;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + BATTLEFIELD_BACKGROUND), 0, 0, null);
	}
}
