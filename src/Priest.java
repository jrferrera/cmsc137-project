import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Priest extends Character {
	public Priest() {
		super();
		setHp(150);
		setMaxHp(150);
		setMp(200);
		setMaxMp(200);
		setAttack(1);
		setDefense(10);
		setAttackRange(1);
		setWalkRange(2);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + PRIEST_IMAGE));

		initializeSkills();
	}
	
	public void initializeSkills() {
		getSkills().put(KeyEvent.VK_F1, new Skill("F1", "Curaga", "Support", 30, 25, 3, false));
	}
}
