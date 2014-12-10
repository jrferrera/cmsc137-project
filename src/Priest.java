import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Priest extends Character {
	public Priest() {
		super();
		setMp(100);
		setAttack(10);
		setDefense(7);
		setAttackRange(1);
		setWalkRange(1);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + PRIEST_IMAGE));

		initializeSkills();
	}
	
	public void initializeSkills() {
		getSkills().put(KeyEvent.VK_F1, new Skill("Curaga", "Support", 30, 15, 3, false));
	}
}
