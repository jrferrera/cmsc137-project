import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class GameManual extends JPanel implements ActionListener{
	
	private JTextArea instructions;
	private JButton back;
	
	public GameManual(){
		setLayout(new BorderLayout());
		instructions=new JTextArea();
		instructions.setFocusable(false);
		
		try{
			BufferedReader br = new BufferedReader(new FileReader("instructions.txt"));
			String line = br.readLine();
			while(line !=null){
				instructions.append(line+"\n");
				line =br.readLine();
			}
			br.close();
			
		}
		catch(Exception e){
			
		}
		
		back =  new JButton("Back");
		back.addActionListener(this);
		
		JScrollPane scroll=new JScrollPane(instructions,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll, BorderLayout.CENTER);
		
		JPanel pane=new JPanel();
		pane.add(back);
		
		this.add(pane, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GameElement.gameClient.changeScreen(new MainMenu(GameElement.gameClient.getPlayer()));
	}

}
