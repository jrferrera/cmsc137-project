import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GameMenu extends JPanel implements Constants, ActionListener {
	private JButton quitGameButton;
	
	public GameMenu() {
		setSize(200, 200);
		
		quitGameButton = new JButton("Quit Game");
		quitGameButton.addActionListener(this);
		
		add(quitGameButton);
	}

	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
			case "Quit Game"	:	
									break;
		}
	}
}
