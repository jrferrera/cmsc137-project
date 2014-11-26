import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;


public class BattleScreen extends JPanel implements Constants {
	private Player player;
	private JPanel battleScreenPanel;
	private JPanel fieldPanel;
	private Battlefield battlefield;
	private GameStatistics gameStatistics;
	private GameMenu gameMenu;
	
	public BattleScreen(Player player) {
		this.player = player;
		gameMenu = new GameMenu();
		battlefield = new Battlefield();
		gameStatistics = new GameStatistics(player);
		
		battleScreenPanel = new JPanel(new BorderLayout());
		battleScreenPanel.add(player.getChatBox(), BorderLayout.EAST);
		battleScreenPanel.add(gameMenu, BorderLayout.SOUTH);
		battleScreenPanel.add(gameStatistics, BorderLayout.NORTH);
		battleScreenPanel.add(battlefield, BorderLayout.CENTER);
		
		add(battleScreenPanel);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + BATTLEFIELD_BACKGROUND), 0, 0, null);
	}
}
