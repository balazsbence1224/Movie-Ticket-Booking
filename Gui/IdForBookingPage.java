package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import Database.BookingWriter;
import Database.RoomReadAndWrite;
import Logic.GuiHelper;

/**
 * Az osztály egy olyan oldalt valósít meg amiben a felhasználónak megkell adnia az email címét, 
 * erre az email címre kerül a foglalás.
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class IdForBookingPage implements ActionListener{
	// Címkék, gombok inicializálása
	JFrame idFrame = new JFrame("Email for booking page");
	JButton BackB = new JButton();
	JButton nextButton = new JButton();
	JTextField idField = new JTextField();

	// A Logicban található class meghívása, amelyben a fontosabb getter,setter függvények vannak.
	GuiHelper guiHelper = new GuiHelper();
	
	IdForBookingPage() {
		
		//Címkék, gombok beállításai, méretezése, formázása, elhelyezése.
		//Ezek hozzáadása a frame-hez.
		JLabel Label;
		Label = new JLabel("Foglalás");
		Label.setBounds(250,150,300,50);
		Label.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel emaiLabel;
		emaiLabel = new JLabel("Email:");
		emaiLabel.setBounds(225,200,50,40);
		
		idField.setBounds(275, 200, 225, 40);
		
		nextButton.setText("OK");
		nextButton.setBounds(400,400,100,50);
		nextButton.addActionListener(this);
		
		BackB.setText("Back");
		BackB.setBounds(100,400,100,50);
		BackB.addActionListener(this);
		
		((JFrame) idFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		idFrame.setSize(600,600);  
		idFrame.setLayout(null);  
		idFrame.setVisible(true);
		
		idFrame.add(emaiLabel);
		idFrame.add(Label);
		idFrame.add(BackB);
		idFrame.add(nextButton);
		idFrame.add(idField);
	}

	public void actionPerformed(ActionEvent e) {
		
		// ID változó meghatározása, egy esemény hatására
		String emailString = idField.getText();
		
		/*
		 * Ha a "Back"- re kattint a felhasználó, akkor ez az oldal eltűnik és létrehoz egy új oldalt,
		 * ahol újból választhat helyeket.
		 */
		if(e.getSource() == BackB) {
			idFrame.dispose();
			RoomPage backPage = new RoomPage();
		}
		/*
		 * Ha a "Next" -re kattint a felhasználó, akkor ellenörzi, hogy az email címben megtatálható-e a "@" karakter,
		 * illetve hogy elég hosszú- e az email cím.
		 * pl: "@gmail.com"--9 karakter
		 * Ezek után beállítja a az email-t id-nek a helper-en keresztül, meghívja azokat a függvényeket, amelyek:
		 * -újra írja a filmehz,és időponthoz tartozó szobának a foglaltságát
		 * -újra írja a "booking.txt"-t, úgy, hogy ez a foglalás ár szerepeljen benne.
		 * Végül megnyítja a megeősítő oldalt, és bezárja ezt.
		 */
		if (e.getSource() == nextButton) {
			if(emailString.contains("@") && 10 <= emailString.length()) {
				guiHelper.setId(emailString);
				
				
				try {
					guiHelper.reWriteRoom(guiHelper.getSeatsForReWrite(), guiHelper.getTitle(), guiHelper.getTime());
					guiHelper.reWriteBooking(guiHelper.getBookingWriter());
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				idFrame.dispose();
				BookingConfirmPage nextPage =new BookingConfirmPage();
			}
		}
	}
}
