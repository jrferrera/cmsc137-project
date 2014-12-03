import java.awt.Image;

import javax.swing.Icon;
import javax.swing.JLabel;


public class Character extends JLabel implements Constants {
	private float hp;
	private float mp;
	private float attack;
	private float defense;
	private int x;
	private int y;
	private Icon characterImage;
	
	public Character() {
		setHp(200);
		setMp(100);
	}
	
	public float getDefense() {
		return defense;
	}
	public void setDefense(float defense) {
		this.defense = defense;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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
		
		data = "x=" + this.x + "|y=" + this.y + "|hp=" + this.hp + "|mp=" + this.mp + "|attack=" + this.attack + "|defense=" + this.defense;
		return data;
	}
	
}
