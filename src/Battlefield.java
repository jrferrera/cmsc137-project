import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;


public class Battlefield extends JPanel implements Constants {
	private JPanel battlefieldPanel;
	private ChatBox chatBox;
	private GameMenu gameMenu;
	
	public Battlefield() {
		chatBox = new ChatBox();
		gameMenu = new GameMenu();
		
		battlefieldPanel = new JPanel(new BorderLayout());
		battlefieldPanel.add(chatBox, BorderLayout.CENTER);
		battlefieldPanel.add(gameMenu, BorderLayout.SOUTH);
		
		add(battlefieldPanel);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + BATTLEFIELD_BACKGROUND), 0, 0, null);
	}

	public void actionPerformed(ActionEvent arg0) {
		GameUtility.changeScreen(new Battlefield());
	}
}
