import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JPanel;


public class Battlefield extends JPanel implements Constants {
	static int height;
	static int width;
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
			}
		}
		
		String playerName;
		for(Iterator<?> i = GameClient.gameState.getPlayers().keySet().iterator(); i.hasNext();) {
			playerName = (String)i.next();
			Player p = (Player)GameClient.gameState.getPlayers().get(playerName);
			
			int k=0;
			for(int j = 0; j < MAXIMUM_CHARACTER_COUNT; j++){
				Character temp = p.getCharacters()[j];
				if(!GameElement.gameClient.getPlayer().getUsername().equals(playerName)){
					temp.setBackground(Color.RED);
					temp.setOpaque(true);
				}
				int x = temp.getXPosition();
				int y = temp.getYPosition();
				if(temp.isDead(temp)){
					k++;
					temp = new Character();
					temp.setXPosition(x);
					temp.setYPosition(y);
					p.getCharacters()[j]=temp;
				}
				blocks[x][y] = temp;
			}
			p.aliveCharacters-=k;
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
				if(blocks[i][j].getBackground()!=Color.RED){
				blocks[i][j].setBackground(new Color(Color.TRANSLUCENT));
				blocks[i][j].setOpaque(false);
				}
			}
		}
	}
	
	public void highlightBlock(int x, int y) {
		try {
			blocks[x][y].setBackground(Color.CYAN);
			blocks[x][y].setOpaque(true);
		} catch(Exception e) {
		}
	}
	
	public void highlightField(int xOrigin, int yOrigin, int range) {
		removeHighlights();
		for(int i = xOrigin-range; i < xOrigin+range+1; i ++) {
			for(int j = yOrigin-range; j < yOrigin+range+1; j++) {
				if(GameUtility.getDistance(xOrigin, yOrigin, i, j)<=range){
				highlightBlock(i,j);
				}
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