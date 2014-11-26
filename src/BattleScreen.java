import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;


public class BattleScreen extends JPanel implements Constants {
	public GameClient ui;
	public Player player;
	private JPanel battlefieldPanel;
	private GameMenu gameMenu;
	
	public BattleScreen(Player player) {
		this.ui = ui;
		this.player = player;
		gameMenu = new GameMenu();
		
		battlefieldPanel = new JPanel(new BorderLayout());
		battlefieldPanel.add(gameMenu, BorderLayout.SOUTH);
		
		add(battlefieldPanel);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + BATTLEFIELD_BACKGROUND), 0, 0, null);
	}
}
