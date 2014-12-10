import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.ImageIcon;


public class Wizard extends Character {
	public Wizard() {
		super();
		setMp(100);
		setAttack(10);
		setDefense(7);
		setAttackRange(1);
		setWalkRange(1);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + WIZARD_IMAGE));

		initializeSkills();
	}
	
	public void initializeSkills() {
		getSkills().put(KeyEvent.VK_F1, new Skill("Firaga", "Offensive", 15, 20, 3, false));
		getSkills().put(KeyEvent.VK_F2, new Skill("Blizzaga", "Offensive", 13, 18, 3, false));
		getSkills().put(KeyEvent.VK_F3, new Skill("Thundaga", "Offensive", 17, 21, 3, false));
	}
}
