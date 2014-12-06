import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JPanel;


public class Battlefield extends JPanel implements Constants {
	private int height;
	private int width;
	private Character[][] blocks;
	private Character activeCharacter;
	
	public Battlefield(Player player) {
		height=640;
		width=640;
		this.setPreferredSize(new Dimension(width,height));
		setLayout(new GridLayout(16,16));
		blocks = new Character[16][16];
		
		refreshField();
	}
	
	
	public void refreshField(){
		this.removeAll();
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				blocks[i][j] = new Character();
				blocks[i][j].setXPosition(i);
				blocks[i][j].setYPosition(j);
				blocks[i][j].setPreferredSize(new Dimension(width/16, height/16));
			}
		}
		
		String playerName;
		for(Iterator<?> i = GameClient.gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player p = (Player)GameClient.gameState.getPlayers().get(playerName);
			
			for(int j = 0; j < MAXIMUM_CHARACTER_COUNT; j++){
				Character temp = p.getCharacters()[j];
				temp.setPreferredSize(new Dimension(width/16,height/16));
				blocks[temp.getXPosition()][temp.getYPosition()] = temp;
			}
		}
		
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				add(blocks[i][j]);
			}
		}
		revalidate();
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
		
	 
	}
	
	public void removeHighlights(){
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				blocks[i][j].setBackground(new Color(Color.TRANSLUCENT));
				blocks[i][j].setOpaque(false);
			}
		}
	}
	
	public void highlightBlock(int x, int y) {
		try {
			blocks[x][y].setBackground(new Color(0,150,150,40));
			blocks[x][y].setOpaque(true);
		} catch(Exception e) {
		}
	}
	
	public void highlightField(int xOrigin, int yOrigin, int range) {
		removeHighlights();
		for(int i = xOrigin-range; i < xOrigin+range+1; i ++) {
			for(int j = yOrigin-range; j < yOrigin+range+1; j++) {
				highlightBlock(i,j);
			}
		}
	}


	public Character getActiveCharacter() {
		return activeCharacter;
	}


	public void setActiveCharacter(Character activeCharacter) {
		this.activeCharacter = activeCharacter;
	}
}