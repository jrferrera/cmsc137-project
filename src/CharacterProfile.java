import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CharacterProfile extends JPanel implements Constants {
	private JLabel noCharacterSelectedLabel;
	private JLabel characterOwner;
	private JLabel characterClass;
	private JLabel characterHp;
	private JLabel characterMp;
	
	public CharacterProfile() {
		noCharacterSelectedLabel = new JLabel();
		
		characterOwner = new JLabel();
		characterClass = new JLabel();
		characterHp = new JLabel();
		characterMp = new JLabel();
		
		setSize(new Dimension(70, 200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(noCharacterSelectedLabel);
		add(characterOwner);
		add(characterClass);
		add(characterHp);
		add(characterMp);
		
		displayNoCharacterSelected();
	}
	
	public void displayNoCharacterSelected() {
		characterOwner.setText("");
		characterClass.setText("");
		characterHp.setText("");
		characterMp.setText("");
		
		noCharacterSelectedLabel.setText("No Character Selected");
		
		revalidate();
	}
	
	public void displayCharacterProfile(Character character) {
		noCharacterSelectedLabel.setText("");
		
		characterOwner.setText("Player: " + character.getOwner().getUsername());
		characterClass.setText("Class: " + character.getClass().toString().substring(6));
		characterHp.setText("HP: " + Float.toString(character.getHp()) + "/" + Float.toString(character.getMaxHp()));
		characterMp.setText("MP: " + Float.toString(character.getMp()) + "/" + Float.toString(character.getMaxMp()));
	
		revalidate();
	}
}
