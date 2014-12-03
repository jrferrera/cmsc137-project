import javax.swing.ImageIcon;


public class Wizard extends Character {
	public Wizard() {
		super();	
		setAttack(10);
		setDefense(7);
		
		setIcon(new ImageIcon(CHARACTER_LOCATION + "/" + WIZARD_IMAGE));
	}
}
