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
	private JTextArea messageBox;
	private JTextField textfield;
	private JButton sendButton;
	private JLabel username;
	private JScrollPane scrollbar;
	
	//UI, Player Name, Host Name
	public ChatBox() {
		messageBox = new JTextArea(10,10);
		textfield = new JTextField();
		sendButton = new JButton("Send");
		username = new JLabel("Username");
		scrollbar = new JScrollPane(messageBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		messageBox.setFocusable(false);
		sendButton.addActionListener(this);
		textfield.addKeyListener(this);
		
		setLayout(new BorderLayout());
		setSize(200, 200);

		add(scrollbar, BorderLayout.NORTH);
		add(sendButton, BorderLayout.EAST);
		add(textfield, BorderLayout.SOUTH);
		add(username, BorderLayout.WEST);
	}
	
	public void actionPerformed(ActionEvent e) {
		messageBox.append(username.getText() + ": " + textfield.getText()+ "\n");
		textfield.setText("");
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			messageBox.append(username.getText() + ": " + textfield.getText()+ "\n");
			textfield.setText("");
		}
	}
	
	public void	keyReleased(KeyEvent e){ }
	
	public void	keyTyped(KeyEvent e) { }
}
