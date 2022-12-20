import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Credit extends JFrame implements ActionListener{
	
	JLabel background;
	JButton buttonBack;
	
	public Credit() {
		
		//Source gambar
		ImageIcon back = new ImageIcon("src/resources/credity.png");
		ImageIcon button = new ImageIcon("src/resources/back.png");
		
		background = new JLabel();
		background.setSize(500, 520);
		background.setIcon(back);
		
		//Tampilan tombol back
		buttonBack = new JButton();
		buttonBack.setBounds(200, 350, 100, 50);
		buttonBack.addActionListener(this);
		buttonBack.setIcon(button);
		
		//Tampilan frame credit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(500, 520);
		this.setResizable(false);
		
		//Menambahkan background
		this.add(buttonBack);
		this.add(background);
		
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
