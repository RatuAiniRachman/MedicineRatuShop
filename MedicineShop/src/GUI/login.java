package GUI;

import java.awt.*;
import java.sql.*;

import javax.swing.*;

import database.*;


@SuppressWarnings("serial")
public class login extends JFrame {
    private static koneksi con = new koneksi();
	private JPanel GL = new JPanel(new GridLayout(2,8,8,15));
	private JTextField txtUsr = new JTextField(12);
	private JPasswordField txtPw = new JPasswordField(12);
	
	public login(){
		
		JLabel user = new JLabel("Username");
		JLabel pass = new JLabel("Password");
		
		txtUsr.setPreferredSize(new Dimension(70,24));
		
		GL.add(user); GL.add(txtUsr);
		GL.add(pass); GL.add(txtPw);
		
		
	}
	
	public Component getGL(){
		return GL;
	}
	
	public int ask(){
		int y=0;
		try {
			int a = 0;
			String username=txtUsr.getText();
			@SuppressWarnings("deprecation")
			String password=txtPw.getText();
			Statement stmt = con.getConnection().createStatement();
			ResultSet r = stmt.executeQuery("select count(IDU) from Users where IDU='"+username+"' and Pass='"+password+"'");
			while(r.next()){
				a=r.getInt(1);
			}
			if(a>0){
				r = stmt.executeQuery("select Jenis from Users where IDU='"+username+"'");
				while(r.next()){
					y=r.getInt(1);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return y;
	}

	public String getUsername() {
		return txtUsr.getText();
	}
}
