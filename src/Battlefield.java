import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Battlefield extends JPanel implements Constants {
	private int height;
	private int width;

	public Battlefield(Player player) {
		height=640;
		width=640;
		this.setPreferredSize(new Dimension(width,height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + BATTLEFIELD_BACKGROUND), 0, 0, null);
		g.setColor(Color.GRAY);
		for(int i=0;i<width;i+=width/16){
			g.drawLine(i,0, i,height);
		}
		for(int i=0;i<height;i+=height/16){
			g.drawLine(0, i, width, i);
		}
	}
}