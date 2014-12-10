import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.JButton;


public class Character extends JButton implements Constants, ActionListener, KeyListener {
	private int characterIndex;
	private float hp;
	private float maxHp;
	private int skillToUse;
	
	public int getSkillToUse() {
		return skillToUse;
	}

	public void setSkillToUse(int skillToUse) {
		this.skillToUse = skillToUse;
	}

	public float getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(float maxHp) {
		this.maxHp = maxHp;
	}

	public float getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(float maxMp) {
		this.maxMp = maxMp;
	}

	private float mp;
	private float maxMp;
	private float attack;
	private float defense;
	private int xPosition;
	private int yPosition;
	private int attackRange;
	private int walkRange;
	
	private boolean onDefend;
	private HashMap<Integer, Skill> skills;

	private Icon characterImage;
	private int state;
	private Player owner;
	
	public Character() {
		setHp(200);
		setMaxHp(200);
		setMp(100);
		setMaxMp(100);
		setBorderPainted(false);
		setWalkRange(0);
		setBackground(new Color(Color.TRANSLUCENT));
		setOpaque(false);
		setOnDefend(false);
		state=MOVE;
		addActionListener(this);
		setPreferredSize(new Dimension(Battlefield.width/16,Battlefield.height/16));
		setSkills(new HashMap<Integer, Skill>());
	}
	
	public boolean hasSkill(int keyCode) {
		if(getSkills().containsKey(keyCode)) {
			return true;
		}else{
			return false;
		}
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
		Character cht = (Character) e.getSource();
		
		try{
			if(cht == null) {
				GameElement.gameClient.getBattleScreen().getCharacterProfile().displayNoCharacterSelected();
			}else{
				GameElement.gameClient.getBattleScreen().getCharacterProfile().displayCharacterProfile(cht);
			}
		}catch(Exception e1) {}
		
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
			Character ch = (Character) e.getSource();
			switch(bf.getActiveCharacter().getState()){
					case ATTACK:
						if(isEnemyCharacter(bf.getActiveCharacter().getOwner(),ch.getOwner())) {
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
						this.removeKeyListener(this);
						break;
					case ACTION:
						break;
					case USE_SKILL:
						if(isEnemyCharacter(bf.getActiveCharacter().getOwner(),ch.getOwner())) {
							System.out.println(ch.getHp());
							bf.getActiveCharacter().useSkill(bf.getActiveCharacter().getSkills().get(bf.getActiveCharacter().getSkillToUse()), ch);
							
							String message1 = "UPDATE_PLAYERS" + "|username=" + bf.getActiveCharacter().getOwner().getUsername() + "|characterIndex=" + bf.getActiveCharacter().getCharacterIndex() + "|mp=" + bf.getActiveCharacter().getMp() + "|enemyUsername=" + ch.getOwner().getUsername() + "|enemyCharacterIndex=" + ch.getCharacterIndex() + "|enemyHp=" + ch.getHp();  
							GameElement.gameClient.sendToServer(message1);
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
			if(GameElement.gameClient.getPlayer().movedCharacters==GameElement.gameClient.getPlayer().aliveCharacters){
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
		
	}
	
	public boolean isWalkable(int x, int y) {
		if(GameUtility.getDistance(xPosition, yPosition, x, y) <= walkRange) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean withinAttackRange(Character character) {
		if(GameUtility.getDistance(xPosition, yPosition, character.getXPosition(), character.getYPosition()) <= attackRange) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean withinSkillRange(Skill skill, Character character) {
		if(GameUtility.getDistance(xPosition, yPosition, character.getXPosition(), character.getYPosition()) <= skill.getRange()) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isEnemyCharacter(Player player1, Player player2) {
		if(player1 != player2 && player2 != null) {
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
			bf.getActiveCharacter().setState(END_TURN);
			bf.getActiveCharacter().setOnDefend(true);
			String message = "DEFEND|username=" + bf.getActiveCharacter().getOwner().getUsername() + "|characterIndex=" + bf.getActiveCharacter().getCharacterIndex() + "|isOnDefend=" + bf.getActiveCharacter().isOnDefend(); 
			GameElement.gameClient.sendToServer(message);
			bf.setActiveCharacter(null);
			GameElement.gameClient.getPlayer().movedCharacters++;
			this.removeKeyListener(this);
		}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			bf.removeHighlights();
			bf.getActiveCharacter().setState(END_TURN);
			bf.setActiveCharacter(null);
			GameElement.gameClient.getPlayer().movedCharacters++;
			this.removeKeyListener(this);
		}else if(bf.getActiveCharacter().hasSkill(e.getKeyCode())) {
			bf.highlightField(xPosition, yPosition, bf.getActiveCharacter().getSkills().get(e.getKeyCode()).getRange());
			bf.getActiveCharacter().setState(USE_SKILL);
			bf.getActiveCharacter().setSkillToUse(e.getKeyCode());
		}
		else{
			return;
		}
		
		
		if(GameElement.gameClient.getPlayer().movedCharacters==GameElement.gameClient.getPlayer().aliveCharacters){
			String message = "END_TURN";
			GameElement.gameClient.sendToServer(message);
		}
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

	public HashMap<Integer, Skill> getSkills() {
		return skills;
	}

	public void setSkills(HashMap<Integer, Skill> skills) {
		this.skills = skills;
	}

	public void useSkill(Skill skill, Character character) {
		if(mp >= skill.getMpCost()) {
			setMp(mp - skill.getMpCost());
			
			float damage = skill.getDamage();
			
			if(skill.isOffensive()) {
				if(character.isOnDefend() && skill.isDefensible()) {
					damage -= character.getDefense();
				}
				
				character.setHp(character.getHp() - damage);
				
				if(isDead(character)) {
					character = new Character();
					GameElement.gameClient.getBattleScreen().getBattlefield().revalidate();
				}
			}else if(skill.isSupport()) {
				if(character.getHp() + damage > character.getMaxHp()) {
					character.setHp(character.getMaxHp());
				}else {
					character.setHp(character.getHp() + damage);
				}
			}
			
			setSkillToUse(-1);
		}
	}
}
