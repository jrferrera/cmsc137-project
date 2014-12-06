import javax.swing.ImageIcon;


public class Priest extends Character {
	public Priest() {
		super();	
		setAttack(15);
		setDefense(10);
		setAttackRange(1);
		setWalkRange(1);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + PRIEST_IMAGE));
	}
}
