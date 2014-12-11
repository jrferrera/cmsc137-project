import javax.swing.ImageIcon;


public class Hunter extends Character {
	public Hunter() {
		super();
		setHp(180);
		setMaxHp(180);
		setMp(60);
		setMaxMp(60);
		setAttack(15);
		setDefense(11);
		setAttackRange(3);
		setWalkRange(3);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + HUNTER_IMAGE));
	}
}
