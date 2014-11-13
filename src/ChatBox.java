import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatBox extends JPanel implements ActionListener, KeyListener {
	public GameClient gameClient;
	private JTextArea messageBox;
	private JTextField textfield;
	private JLabel usernameLabel;
	private JButton sendButton;
	private JScrollPane scrollbar;
		
	public ChatBox(GameClient gameClient, String username) {
		this.gameClient = gameClient;
		
		messageBox = new JTextArea(10,10);
		textfield = new JTextField();
		usernameLabel = new JLabel(username);
		sendButton = new JButton("Send");
		scrollbar = new JScrollPane(messageBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		messageBox.setFocusable(false);
		sendButton.addActionListener(this);
		textfield.addKeyListener(this);
		
		setLayout(new BorderLayout());
		setSize(200, 200);

		add(scrollbar, BorderLayout.NORTH);
		add(sendButton, BorderLayout.EAST);
		add(textfield, BorderLayout.SOUTH);
		add(usernameLabel, BorderLayout.WEST);
	}
	
	public void actionPerformed(ActionEvent e) {
		sendMessage();
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			sendMessage();
		}
	}
	
	public void sendMessage() {
		gameClient.sendToServer("CHAT_ALL|" + "chatMessage=" + usernameLabel.getText() + ": " + textfield.getText());
	}
	
	public void appendMessage(String message) {
		messageBox.append(message + "\n");
		textfield.setText("");
	}
	
	public void	keyReleased(KeyEvent e){ }
	
	public void	keyTyped(KeyEvent e) { }
}
