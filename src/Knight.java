import javax.swing.ImageIcon;


public class Knight extends Character{
	public Knight() {
		super();	
		setAttack(15);
		setDefense(10);
		setAttackRange(1);
		setWalkRange(4);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + KNIGHT_IMAGE));
	}
}
