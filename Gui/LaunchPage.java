package Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Az osztálya egy olyan gui oldalt valósít meg, amelyben egy főképernyő látszodik.
 * @author Bogdán Balázs
 * @verzion 2022.05.08.
 */
public class LaunchPage extends JFrame implements ActionListener{
	
	// Címkék gombok inicializálása
	Frame f = new JFrame("Launch page");
	JButton bookingb = new JButton("Booking");
	JButton adminb = new JButton("Admin");  
	
	public LaunchPage(){
		
		//Címkék, gombok beállításai, méretezése, formázása, elhelyezése.
		//Ezek hozzáadása a frame-hez.
		JLabel l1;
		l1=new JLabel("Movie ticket booking");  
		l1.setBounds(220,150,300,50);
		l1.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		bookingb.setBounds(250,200,100, 40);  
		bookingb.setFocusable(false);
		bookingb.addActionListener(this);
		
		adminb.setBounds(250,250,100, 40);
		adminb.setFocusable(false);
		adminb.addActionListener(this);
		
		JLabel l2;
		l2 = new JLabel("Booking");
		l2.setBounds(220,150,300,50);
		
		f.add(bookingb);
		f.add(adminb);
		f.add(l1);
		
		((JFrame) f).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600,600);  
		f.setLayout(null);  
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		// Az "Admin" gombra kattintva, bezárja ezt az oldalt és megnyitja az Admin oldalt.
		if(e.getSource() == adminb) {
			f.dispose();
			AdminPWPage pwPage = new AdminPWPage();
		}
		
		// A "Booking" gombra kattinva megnyitja azt az oldalt ahol a felhasználó választhat filmet és időpontot.
		if(e.getSource() == bookingb) {
			f.dispose();
			TitlesAndTimesPage next = new TitlesAndTimesPage();
		}
	}
}
