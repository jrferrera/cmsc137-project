import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.JButton;


public class Character extends JButton implements Constants, ActionListener, KeyListener {
	private int characterIndex;
	private float hp;
	private float mp;
	private float attack;
	private float defense;
	private int xPosition;
	private int yPosition;
	private int attackRange;
	private int walkRange;
	private int skillRange;
	private boolean onDefend;
	
	public int getSkillRange() {
		return skillRange;
	}

	public void setSkillRange(int skillRange) {
		this.skillRange = skillRange;
	}

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
		setOnDefend(false);
		state=MOVE;
		addActionListener(this);
		setPreferredSize(new Dimension(Battlefield.width/16,Battlefield.height/16));
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
		Battlefield bf=GameElement.gameClient.getBattleScreen().getBattlefield();
		// Instantiate active character
		if(GameElement.gameClient.getPlayer().getUsername().equals(GameElement.gameClient.getPlayerTurn())){
		if(bf.getActiveCharacter() == null){
			if(this.owner!=null){
				if(this.owner.getUsername().equals(GameElement.gameClient.getPlayer().getUsername())){
					if(this.getState()==MOVE){
					bf.highlightField(xPosition, yPosition, walkRange);
					bf.setActiveCharacter(this);
					}
				}
			}
		}
		// Action on selected character
		else if(this.isOpaque() && this.owner==null){
				if(bf.getActiveCharacter().getState()==MOVE){
						bf.getActiveCharacter().requestFocus();
						bf.getActiveCharacter().setXPosition(this.xPosition);
						bf.getActiveCharacter().setYPosition(this.yPosition);
						
						bf.getActiveCharacter().addKeyListener(bf.getActiveCharacter());
						bf.getActiveCharacter().setState(ACTION);
						bf.refreshField();
						
						String message = "MOVE|username=" + bf.getActiveCharacter().getOwner().getUsername() + "|characterIndex=" + bf.getActiveCharacter().getCharacterIndex() + "|xPosition=" + bf.getActiveCharacter().getXPosition() + "|yPosition=" + bf.getActiveCharacter().getYPosition(); 
						GameElement.gameClient.sendToServer(message);
				}
		}
		else if(this==bf.getActiveCharacter()){
				if(bf.getActiveCharacter().getState()==MOVE){
						bf.getActiveCharacter().requestFocus();
						bf.getActiveCharacter().setXPosition(this.xPosition);
						bf.getActiveCharacter().setYPosition(this.yPosition);
						bf.getActiveCharacter().addKeyListener(bf.getActiveCharacter());
						bf.getActiveCharacter().setState(ACTION);
						bf.refreshField();
				}
		}
		else if(this.owner!=null){
			bf.getActiveCharacter().requestFocus();
			switch(bf.getActiveCharacter().getState()){
					case ATTACK:
						Character ch = (Character) e.getSource();

						if(bf.getActiveCharacter().getOwner() != ch.getOwner() && ch.getOwner() != null) {
							System.out.println(ch.getHp());
							bf.getActiveCharacter().attack(ch);
							
							// Update the state of enemy player
							String message = "ATTACK|username=" + ch.getOwner().getUsername() + "|characterIndex=" + ch.getCharacterIndex() + "|hp=" + ch.getHp(); 
							GameElement.gameClient.sendToServer(message);
							System.out.println(ch.getHp());
						}
						bf.removeHighlights();
						bf.getActiveCharacter().setState(END_TURN);
						bf.setActiveCharacter(null);
						GameElement.gameClient.getPlayer().movedCharacters++;
						break;
					case END_TURN:
						bf.setActiveCharacter(null);
						break;
			}
			if(GameElement.gameClient.getPlayer().movedCharacters==GameElement.gameClient.getPlayer().aliveCharacters--){
				String message = "END_TURN";
				GameElement.gameClient.sendToServer(message);
			}
		}
		}
		
	}
	
	public boolean isDead(Character character) {
		if(character.getHp() <= 0.0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void attack(Character character) {
		float damage = attack;
		
		if(character.isOnDefend()) {
			damage -= character.getDefense();
		}
		
		character.setHp(character.getHp() - damage);
		
		if(isDead(character)) {
			character.getOwner().aliveCharacters--;
		}
	}
	
	public boolean isWalkable(int x, int y) {
		if(GameUtility.getDistance(xPosition, yPosition, x, y) <= walkRange) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isAttackable(int x, int y) {
		if(GameUtility.getDistance(xPosition, yPosition, x, y) <= walkRange) {
			return true;
		}else {
			return false;
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
		Battlefield bf = GameElement.gameClient.getBattleScreen().getBattlefield();
		// Attack
		if(e.getKeyCode() == KeyEvent.VK_A) {
			bf.highlightField(xPosition, yPosition, attackRange);
			bf.getActiveCharacter().setState(ATTACK);
		// Defense
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			bf.removeHighlights();
			String message = "DEFEND|username=" + bf.getActiveCharacter().getOwner().getUsername() + "|characterIndex=" + bf.getActiveCharacter().getCharacterIndex() + "|isOnDefend=" + bf.getActiveCharacter().isOnDefend(); 
			GameElement.gameClient.sendToServer(message);
			bf.getActiveCharacter().setState(END_TURN);
			bf.getActiveCharacter().setOnDefend(true);
			GameElement.gameClient.getPlayer().movedCharacters++;
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			bf.getActiveCharacter().setState(ATTACK);
			GameElement.gameClient.getPlayer().movedCharacters++;
		}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			bf.removeHighlights();
			bf.getActiveCharacter().setState(END_TURN);
			GameElement.gameClient.getPlayer().movedCharacters++;
		}
		
		
		if(GameElement.gameClient.getPlayer().movedCharacters==MAXIMUM_CHARACTER_COUNT){
			String message = "END_TURN";
			GameElement.gameClient.sendToServer(message);
		}
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

	public int getCharacterIndex() {
		return characterIndex;
	}

	public void setCharacterIndex(int characterIndex) {
		this.characterIndex = characterIndex;
	}

	public boolean isOnDefend() {
		return onDefend;
	}

	public void setOnDefend(boolean onDefend) {
		this.onDefend = onDefend;
	}

	
}
