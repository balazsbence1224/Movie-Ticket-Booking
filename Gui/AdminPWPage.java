package Gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Az osztályban egy olyan page valósul meg ahol a felhasználótól kér egy jelszót.
 * A jelszó:"test"
 * @author Bogdán Balázs
 * @version 2022.05.08.
 *
 */
public class AdminPWPage implements ActionListener {
	
	// Címkék, gombok inicializálása
	JFrame pwFrame = new JFrame("Admin Login");
	JButton backToLaunchPageButton = new JButton();
	JButton loginButton = new JButton();
	JPasswordField pw = new JPasswordField();
	JLabel wrong;
	
	AdminPWPage() {
		
		//Címkék, gombok beállításai, méretezése, formázása, elhelyezése.
		//Ezek hozzáadása a frame-hez.
		JLabel adminJLabel;
		adminJLabel = new JLabel("Admin");
		adminJLabel.setBounds(250,150,300,50);
		adminJLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel label;
		label = new JLabel("Password:");
		label.setBounds(225,200,75,40);
		
		wrong = new JLabel("Incorrect password");
		wrong.setBounds(250,250,200,40);
		wrong.setForeground(Color.RED);
		
		pw.setBounds(300, 200, 100, 40);
		
		loginButton.setText("Login");
		loginButton.setBounds(400,400,100,50);
		loginButton.addActionListener(this);
		
		backToLaunchPageButton.setText("Back");
		backToLaunchPageButton.setBounds(100,400,100,50);
		backToLaunchPageButton.addActionListener(this);
		
		pwFrame.add(label);
		pwFrame.add(adminJLabel);
		pwFrame.add(pw);
		pwFrame.add(backToLaunchPageButton);
		pwFrame.add(loginButton);
		pwFrame.add(wrong);
		wrong.setVisible(false);
		
		((JFrame) pwFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pwFrame.setSize(600,600);  
		pwFrame.setLayout(null);  
		pwFrame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 *  Ha a "login" gombra nyom a felhasználó, akkor ellenőrzésre kerül a beírt szöveg,
		 *  ha nem egyezik akkor megjeleník egy címke ami jelzi ezt,
		 *  ellentétes esetben pedig tovább ugrik az Admin oldalra.
		 */
		if(e.getSource() == loginButton) {
			
			String pwString = pw.getText();
			
			if (pwString.equals("test")) {
				
				pwFrame.dispose();
				AdminPage apage = new AdminPage();
			}else {
				
				wrong.setVisible(true);
			}
		}
		//Ha a "Back" gomba nyom a felhasználó, akkor ez az oldal eltünik és megnyit egy új főoldalt
		if(e.getSource() == backToLaunchPageButton) {
			pwFrame.dispose();
			LaunchPage back = new LaunchPage();
		}
	}
}
