import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.JButton;


public class Character extends JButton implements Constants, ActionListener, KeyListener {
	private float hp;
	private float mp;
	private float attack;
	private float defense;
	private int xPosition;
	private int yPosition;
	private int attackRange;
	private int walkRange;
	private Icon characterImage;
	private int state;
	private Player owner;
	
	public Character() {
		setHp(200);
		setMp(100);
		setBorderPainted(false);
		setWalkRange(0);
		setBackground(new Color(Color.TRANSLUCENT));
		setOpaque(false);
		state=MOVE;
		addActionListener(this);
	}
	
	public float getDefense() {
		return defense;
	}
	public void setDefense(float defense) {
		this.defense = defense;
	}
	public int getXPosition() {
		return xPosition;
	}
	public void setXPosition(int x) {
		this.xPosition = x;
	}
	public int getYPosition() {
		return yPosition;
	}
	public void setYPosition(int y) {
		this.yPosition = y;
	}
	
	public float getHp() {
		return hp;
	}
	public void setHp(float hp) {
		this.hp = hp;
	}
	public float getMp() {
		return mp;
	}
	public void setMp(float mp) {
		this.mp = mp;
	}
	public float getAttack() {
		return attack;
	}
	public void setAttack(float attack) {
		this.attack = attack;
	}
	
	public Icon getCharacterImage() {
		return characterImage;
	}

	public void setCharacterImage(Icon characterImage) {
		this.characterImage = characterImage;
	}

	public String getCharacterStringData() {
		String data;
		
		data = "x=" + this.xPosition + "|y=" + this.yPosition + "|hp=" + this.hp + "|mp=" + this.mp + "|attack=" + this.attack + "|defense=" + this.defense;
		return data;
	}

	public void actionPerformed(ActionEvent e) {
		if(GameElement.gameClient.getBattleScreen().getBattlefield().getActiveCharacter() == null && this.getState()!=ACTION){
			if(this.owner!=null){
				if(this.owner.getUsername().equals(GameElement.gameClient.getPlayer().getUsername())){
					GameElement.gameClient.getBattleScreen().getBattlefield().highlightField(xPosition, yPosition, walkRange);
					GameElement.gameClient.getBattleScreen().getBattlefield().setActiveCharacter(this);
				}
			}
		}
		else if(GameElement.gameClient.getBattleScreen().getBattlefield().getActiveCharacter().getClass()==Character.class && this.getState()!=ACTION){
			GameElement.gameClient.getBattleScreen().getBattlefield().highlightField(xPosition, yPosition, walkRange);
			GameElement.gameClient.getBattleScreen().getBattlefield().setActiveCharacter(this);
		}
		else{
			if(this.getClass() != Character.class && this.getState()!=ACTION){
				if(this.owner!=null){
					if(this.owner.getUsername().equals(GameElement.gameClient.getPlayer().getUsername())){
						GameElement.gameClient.getBattleScreen().getBattlefield().highlightField(xPosition, yPosition, walkRange);
						GameElement.gameClient.getBattleScreen().getBattlefield().setActiveCharacter(this);
					}
				}
			}
			else if(this.isOpaque()){
				Battlefield bf = GameElement.gameClient.getBattleScreen().getBattlefield();
				switch(bf.getActiveCharacter().getState()){
				case MOVE:
					bf.getActiveCharacter().setXPosition(this.xPosition);
					bf.getActiveCharacter().setYPosition(this.yPosition);
					bf.refreshField();
					bf.getActiveCharacter().setState(ACTION);
					bf.setActiveCharacter(null);
					break;
				case ACTION:
					bf.getActiveCharacter().addKeyListener(bf.getActiveCharacter());
					bf.getActiveCharacter().setState(END_TURN);
					bf.setActiveCharacter(null);
					break;
				}
			}
		}
		
	}
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getWalkRange() {
		return walkRange;
	}

	public void setWalkRange(int walkRange) {
		this.walkRange = walkRange;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		this.removeKeyListener(this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	
}
