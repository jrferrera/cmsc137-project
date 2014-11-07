import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GamePortal extends JPanel implements Constants, ActionListener {
	private Game ui;
	private JButton startBattleButton;
	
	public GamePortal(Game ui) {
		this.ui = ui;
		
		startBattleButton = new JButton(START_BATTLE);
		
		init();
	}
	
	public void init() {
		startBattleButton.addActionListener(this);
		
		add(startBattleButton);
	}

	public void actionPerformed(ActionEvent e) {
		ui.changeScreen(e.getActionCommand());
	}
}
