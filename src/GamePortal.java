import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GamePortal extends JPanel implements Constants, ActionListener {
	private JPanel gamePortalPanel;
	
	private JButton startBattleButton;
	
	public GamePortal() {
		startBattleButton = new JButton("Start Battle");
		startBattleButton.addActionListener(this);
		
		gamePortalPanel = new JPanel();
		
		gamePortalPanel.setPreferredSize(new Dimension(300, 300));
		gamePortalPanel.setLayout(new GridLayout(1, 1));
		gamePortalPanel.add(startBattleButton);
		
		add(gamePortalPanel);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + GAME_PORTAL_BACKGROUND), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		GameUtility.changeScreen(new Battlefield());
	}
}
