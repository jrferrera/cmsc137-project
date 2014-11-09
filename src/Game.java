import java.awt.Dimension;

import javax.swing.JFrame;


public class Game extends JFrame implements Constants, Runnable {
	Thread gameThread = new Thread(this);
	boolean connected = false;
	
	public Game() {
		setTitle(GAME_TITLE);
		setPreferredSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));	
		setMinimumSize(new Dimension(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		GameUtility.gameFrame = this;
		GameUtility.changeScreen(new MainMenu());
	}	

	public void run() {
		
	}
	
	public static void main(String args[]) {
		new Game();
	}
}


