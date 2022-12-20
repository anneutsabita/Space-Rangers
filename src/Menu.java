import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame implements ActionListener{
	
	JButton buttonPlay;
	JButton buttonCredit;
	JLabel background;
	
	public Menu(){
		
		ImageIcon play = new ImageIcon("src/resources/play.png");
		ImageIcon credit = new ImageIcon("src/resources/credit.png");
		ImageIcon back = new ImageIcon("src/resources/background2.png");
		
		//Membuat background dengan bantuan label
		background = new JLabel();
		background.setSize(500, 520);
		background.setIcon(back);
		
		//Tampilan tombol play
		buttonPlay = new JButton();
		buttonPlay.setBounds(200, 230, 100, 50);
		buttonPlay.addActionListener(this);
		buttonPlay.setIcon(play);
		
		//Tampilan tombol credit
		buttonCredit = new JButton();
		buttonCredit.setBounds(200, 300, 100, 50);
		buttonCredit.addActionListener(this);
		buttonCredit.setIcon(credit);
		
		//Tampilan frame menu
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(500, 520);
		this.setResizable(false);
		
		//Menambahkan background dan button
		this.add(buttonPlay);
		this.add(buttonCredit);
		this.add(background);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Action apabila menekan tombol play
		if(e.getSource() == buttonPlay) {
			
			new GameView();
	        this.setVisible(false);
			
		}
		
		//Action apabila menekan tombol credit
		if(e.getSource() == buttonCredit) {
			
			new Credit();
			this.setVisible(false);
			
		}
		
	}

}
