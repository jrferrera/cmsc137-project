import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class GamePortal extends JPanel implements Constants, ActionListener {
	private Player player;
	private JPanel gamePortalPanel;
	private JButton startBattleButton;
	private ChatBox chatBox;
	
	public GamePortal(Player player) {
		this.player = player;
		
		chatBox = new ChatBox(player.getUsername());
		player.setChatBox(chatBox);
		startBattleButton = new JButton("Warp to Battlefield");
		startBattleButton.addActionListener(this);
		startBattleButton.setEnabled(false);
		gamePortalPanel = new JPanel();
		
		gamePortalPanel.setSize(new Dimension(300, 300));
		this.setLayout(new BorderLayout());
		gamePortalPanel.setLayout(new GridLayout());
		gamePortalPanel.add(startBattleButton);
		
		JPanel gamePortalSidePanel=new JPanel();
		gamePortalSidePanel.setPreferredSize(new Dimension(150,300));
		gamePortalSidePanel.add(player.getChatBox());
		add(gamePortalSidePanel,BorderLayout.EAST);
		add(gamePortalPanel,BorderLayout.CENTER);
	}

	public JButton getStartBattleButton() {
		return startBattleButton;
	}

	public void setStartBattleButton(JButton startBattleButton) {
		this.startBattleButton = startBattleButton;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(GameUtility.getImage(BACKGROUND_LOCATION + '/' + GAME_PORTAL_BACKGROUND), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
		GameElement.gameClient.sendToServer("READY");
		this.startBattleButton.setEnabled(false);
		this.startBattleButton.setText("Ready");
=======
		GameElement.gameClient.changeScreen(new BattleScreen(player));
>>>>>>> origin/jeff
	}
}
