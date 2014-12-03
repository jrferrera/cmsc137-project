import javax.swing.ImageIcon;


public class Priest extends Character {
	public Priest() {
		super();	
		setAttack(15);
		setDefense(10);
		
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + PRIEST_IMAGE));
	}
}
