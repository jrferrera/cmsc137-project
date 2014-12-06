import javax.swing.ImageIcon;


public class Hunter extends Character {
	public Hunter() {
		super();	
		setAttack(13);
		setDefense(9);
		setAttackRange(3);
		setWalkRange(1);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + HUNTER_IMAGE));
	}
}
