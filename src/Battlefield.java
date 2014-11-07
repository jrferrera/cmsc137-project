import java.awt.BorderLayout;

import javax.swing.JPanel;


public class Battlefield extends JPanel implements Constants {
	private Game ui;
	private ChatBox chatBox;
	
	public Battlefield(Game ui) {
		this.ui = ui;
		
		chatBox = new ChatBox();

		init();
	}
	
	public void init() {
		add(chatBox, BorderLayout.EAST);
	}
}
