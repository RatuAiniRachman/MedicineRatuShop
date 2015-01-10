package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainUtama extends JFrame{
	
	private Dimension Layar = Toolkit.getDefaultToolkit().getScreenSize();
	private login log = new login();
	private JPanel lg = new JPanel();
	private Container k = getContentPane();
	private int cek=0;
	
	public MainUtama(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(290, 150);
		setLocation((Layar.width - getWidth()) / 2, (Layar.height - getHeight()) / 2);
		setResizable(false);
		
		JButton BtnLg = new JButton("Login");
		
		lg.add(log.getGL());
		
		k.setLayout(new BorderLayout());
		k.add(lg,BorderLayout.CENTER);
		k.add(BtnLg,BorderLayout.SOUTH);
		
		
		BtnLg.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cek=log.ask();
				if(cek!=0){
					String un = log.getUsername();
					if(cek==1){
						Menu m = new Menu(un);
						m.setVisible(true);
					}else{
						Kasir m = new Kasir(un);
						m.setVisible(true);
					}
					dispose();
				}else{
					JOptionPane.showMessageDialog(null,"Username atau password salah","Login Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		);
	}
	public static void main (String []args){
		MainUtama x = new MainUtama();
		x.setVisible(true);
	}
}
