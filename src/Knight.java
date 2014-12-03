import javax.swing.ImageIcon;


public class Knight extends Character{
	public Knight() {
		super();	
		setAttack(15);
		setDefense(10);
		
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + KNIGHT_IMAGE));
	}
}
