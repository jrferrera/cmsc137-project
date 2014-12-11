import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;


public class Wizard extends Character {
	public Wizard() {
		super();
		setHp(100);
		setMaxHp(100);
		setMp(180);
		setMaxMp(100);
		setAttack(1);
		setDefense(8);
		setAttackRange(1);
		setWalkRange(2);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + WIZARD_IMAGE));

		initializeSkills();
	}
	
	public void initializeSkills() {
		getSkills().put(KeyEvent.VK_F1, new Skill("F1", "Firaga", "Offensive", 15, 28, 3, false));
		getSkills().put(KeyEvent.VK_F2, new Skill("F2", "Blizzaga", "Offensive", 13, 25, 3, false));
		getSkills().put(KeyEvent.VK_F3, new Skill("F3", "Thundaga", "Offensive", 17, 30, 3, false));
	}
}
