package Gui;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

import Logic.GuiHelper;

/**
 * Az osztály egy olyan oldalt valósít meg, ahol a foglalással kapcsolatos adatokat jeleníti meg.
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class BookingConfirmPage implements ActionListener {
	// Címkék, gombok inicializálása
	JFrame confirmFrame = new JFrame("Booking confirmation page");
	JButton closeButton = new JButton();
	JLabel confirm = new JLabel();
	JLabel confirm2 = new JLabel();
	JLabel confirm3 = new JLabel();
	
	// A Logicban található class meghívása, amelyben a fontosabb getter,setter függvények vannak.
	GuiHelper helper = new GuiHelper();
	
	public BookingConfirmPage() {
		
		//Címkék, gombok beállításai, méretezése, formázása, elhelyezése.
		//Ezek hozzáadása a frame-hez.
		confirm.setText("Confimation for: " + helper.getId());
		confirm.setFont(new Font("Verdena",30,20));
		confirm.setBounds(50,100,350,40);
		
		confirm2.setText("Movie: " + helper.getTitle() + ", Time: " + helper.getTime());
		confirm2.setBounds(60,150,300,20);
		
		confirm3.setText("Seats: " + helper.getSeatsToString());
		confirm3.setBounds(60,180,510,20);
		
		closeButton.setText("Close");
		closeButton.setBounds(200,400,100,50);
		closeButton.addActionListener(this);
		
		((JFrame) confirmFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		confirmFrame.setSize(600,600);  
		confirmFrame.setLayout(null);  
		confirmFrame.setVisible(true);
		
		confirmFrame.add(closeButton);
		confirmFrame.add(confirm);
		confirmFrame.add(confirm2);
		confirmFrame.add(confirm3);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// A "Close" gombra kattintva bezárja az oldalt
		if (e.getSource() == closeButton) {
			confirmFrame.dispose();
		}
	}
}
