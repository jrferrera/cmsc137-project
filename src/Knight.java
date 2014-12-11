import javax.swing.ImageIcon;


public class Knight extends Character{
	public Knight() {
		super();
		setHp(1);
		setMaxHp(200);
		setMp(50);
		setMaxMp(50);
		setAttack(20);
		setDefense(15);
		setAttackRange(1);
		setWalkRange(4);
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + KNIGHT_IMAGE));
	}
}
