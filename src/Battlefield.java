import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Battlefield extends JPanel implements Constants {
	private int height;
	private int width;
	private JLabel[][] blocks;

	public Battlefield(Player player) {
		height=640;
		width=640;
		this.setPreferredSize(new Dimension(width,height));
		setLayout(new GridLayout(16,16));
		blocks = new JLabel[16][16];
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				blocks[i][j] = new JLabel();
				blocks[i][j].setSize(new Dimension(width/16, height/16));
				add(blocks[i][j]);
			}
		}
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
		
		drawScene(g);
	}
	
	private void drawScene(Graphics g){
		String playerName;
		for(Iterator<?> i = GameClient.gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player p = (Player)GameClient.gameState.getPlayers().get(playerName);
			
			for(int j = 0; j < MAXIMUM_CHARACTER_COUNT; j++){
				Character temp = p.getCharacters()[j];
				temp.setSize(new Dimension(width/16,height/16));
				remove(blocks[temp.getX()][temp.getY()]);
				blocks[temp.getX()][temp.getY()] = temp;
				add(blocks[temp.getX()][temp.getY()]);
			}
		}
		
		revalidate();
	}
	
}