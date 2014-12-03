import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;

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
		
		drawScene(g);
	}
	
	private void drawScene(Graphics g){
		String playerName;
		for(Iterator<?> i = GameClient.gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player p = (Player)GameClient.gameState.getPlayers().get(playerName);
			for(int j=0;j<GameUtility.MAXIMUM_CHARACTER_COUNT;j++){
			Character temp=p.getCharacters()[j];
			g.drawImage(GameUtility.getImage(CHARACTER_LOCATION+"/"+KNIGHT_IMAGE),temp.getX()*width/16,temp.getY()*height/16,width/16,height/16,null);
			
			}
		}
	}
	
}