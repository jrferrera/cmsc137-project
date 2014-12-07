import java.awt.Color;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameStatistics extends JPanel implements Constants {
	private Player player;
	private JLabel enemyCharactersKilledLabel;
	private JLabel enemyCharactersKilledCountLabel;
	private JLabel ownCharactersLabel;
	private JLabel ownCharactersCountLabel;
	private JLabel playerTurn;
	
	public GameStatistics(Player player) {
		setSize(new Dimension(200, 200));
		
		this.player = player;
		playerTurn=new JLabel();
		enemyCharactersKilledLabel = new JLabel("Characters Killed: ");
		enemyCharactersKilledCountLabel = new JLabel(Integer.toString(player.getEnemyCharactersDefeated()));
		ownCharactersLabel = new JLabel("Dead Characters: ");
		ownCharactersCountLabel = new JLabel(Integer.toString(player.getOwnCharacterDeathCount()));
		
		add(playerTurn);
		add(enemyCharactersKilledLabel);
		add(enemyCharactersKilledCountLabel);
		add(ownCharactersLabel);
		add(ownCharactersCountLabel);
	}

	public JLabel getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(JLabel playerTurn) {
		this.playerTurn = playerTurn;
	}

}
