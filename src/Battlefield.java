import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Battlefield extends JPanel implements Constants {
	public Battlefield() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + BATTLEFIELD_BACKGROUND), 0, 0, null);
	}
}