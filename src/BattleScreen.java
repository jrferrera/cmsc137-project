import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;


public class BattleScreen extends JPanel implements Constants {
	private Player player;
	private JPanel battlefieldPanel;
	private GameStatistics gameStatistics;
	private GameMenu gameMenu;
	
	public BattleScreen(Player player) {
		this.player = player;
		gameMenu = new GameMenu();
		
		battlefieldPanel = new JPanel(new BorderLayout());
		gameStatistics = new GameStatistics(player);
		
		battlefieldPanel.add(gameStatistics, BorderLayout.NORTH);
		battlefieldPanel.add(gameMenu, BorderLayout.SOUTH);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + BATTLEFIELD_BACKGROUND), 0, 0, null);
	}
}
