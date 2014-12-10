import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GamePortal extends JPanel implements Constants, ActionListener {
	private Player player;
	private JPanel gamePortalPanel;
	private JButton startBattleButton;
	private ChatBox chatBox;
	private JTextArea connectedPlayers;
	
	public GamePortal(Player player) {
		this.player = player;
		
		chatBox = new ChatBox(player.getUsername());
		player.setChatBox(chatBox);
		startBattleButton = new JButton("Warp to Battlefield");
		startBattleButton.addActionListener(this);
		startBattleButton.setEnabled(false);
		gamePortalPanel = new JPanel();
		
		connectedPlayers = new JTextArea("Connected Players:\n");
		connectedPlayers.setFocusable(false);
		
		connectedPlayers.setPreferredSize(new Dimension(150,100));
		gamePortalPanel.setPreferredSize(new Dimension(200, 300));
		this.setLayout(new BorderLayout());
		gamePortalPanel.add(startBattleButton);
		gamePortalPanel.add(connectedPlayers);
		
		JPanel gamePortalSidePanel=new JPanel();
		gamePortalSidePanel.setPreferredSize(new Dimension(150,300));
		gamePortalSidePanel.add(player.getChatBox());
		add(gamePortalSidePanel,BorderLayout.EAST);
		add(gamePortalPanel,BorderLayout.CENTER);
	}

	public JTextArea getConnectedPlayers() {
		return connectedPlayers;
	}

	public void setConnectedPlayers(JTextArea connectedPlayers) {
		this.connectedPlayers = connectedPlayers;
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
		GameElement.gameClient.sendToServer("READY");
		this.startBattleButton.setEnabled(false);
		this.startBattleButton.setText("Ready");
	}
}
