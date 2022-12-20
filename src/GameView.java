import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameView extends JFrame implements ActionListener{
	
	JButton buttonBack;
	
	public GameView() {
		
		//Source gambar
		ImageIcon button = new ImageIcon("src/resources/back.png");
				
		//Tampilan tombol back
		buttonBack = new JButton();
		buttonBack.setBounds(200, 350, 100, 50);
		buttonBack.addActionListener(this);
		buttonBack.setIcon(button);
		buttonBack.setFocusable(false);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(500, 520);
		this.setResizable(false);
		
		
		Keyboard keyboard = Keyboard.getInstance();
		this.addKeyListener(keyboard);
		
		GamePanel panel = new GamePanel();
		
		//Menambahkan panel game dan button back
		this.add(buttonBack);
		this.add(panel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Action apabila menekan tombol back
		if(e.getSource() == buttonBack) {
				
			new Menu();
			this.setVisible(false);
			
		}
		
	}

}
