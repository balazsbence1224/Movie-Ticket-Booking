package Gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import Database.ReadAndWriteMovies;
import Logic.GuiHelper;
import Logic.Movie;

/**
 * Az osztályban az Adminpage szerepel, ennek is inkább a megjelenitése(gui) 
 * itt tud az admin felhasználó hozzáadni, törölni 1-1 filmet.
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class AdminPage implements ActionListener{

	// Címkék, gombok inicializálása
	JFrame adminFrame = new JFrame("Admin");
	JButton backToLaunchPageButton = new JButton();
	JButton okButton = new JButton();
	JTextField cmdField = new JTextField();
	JLabel notFound = new JLabel();;
	JLabel wrong = new JLabel();
	JLabel right = new JLabel();
	JLabel helper = new JLabel();
	JLabel inTime = new JLabel();
	JLabel wrongTime = new JLabel();
	
	// A Logicban található class meghívása, amelyben a fontosabb getter,setter függvények vannak.
	GuiHelper guiHelper = new GuiHelper();
	
	//Létrehozok egy listát amiben tárolom a filmeket, illetve egy string változót amiben a leírást.
	ArrayList<Movie> list = new ArrayList<>();
	String description;
	
	/**
	 * A gombok, címkék elhelyezése, beállításai.
	 * A frame beállításai, gombok és címkék hozzáadása a frame-hez.
	 * Láthatóság beállítása.
	 */
	AdminPage(){
		//lista feltöltése filmekkel
		list = guiHelper.getMovies();
		
		//Címkék, gombok beállításai, méretezése, formázása, elhelyezése.
		//Ezek hozzáadása a frame-hez.
		JLabel adminJLabel;
		adminJLabel = new JLabel("Admin cmd");
		adminJLabel.setBounds(250,150,300,50);
		adminJLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		helper.setText("<html>Add film: add/{Title}/{Time}/{Description}<br/>eg.: add/Sonic 2/18:20/the hedgehog<br/>Remove film: rm/{Title}/{Time}<br/>eg.: rm/Sonic 2/18:20</html>");
		helper.setBounds(75,280,500,90);
		
		wrongTime.setText("Incorrect Time");
		wrongTime.setBounds(100,250,100,20);
		wrongTime.setForeground(Color.RED);
		
		notFound.setText("There is no such movie");
		notFound.setBounds(100,250,200,20);
		notFound.setForeground(Color.RED);
		
		inTime.setText("There is film in this time");
		inTime.setBounds(100,250,200,20);
		inTime.setForeground(Color.RED);
		
		wrong.setText("Incorrect format");
		wrong.setBounds(100,250,150,20);
		wrong.setForeground(Color.RED);
		
		right.setText("Done");
		right.setBounds(100,250,100,20);
		right.setForeground(Color.GREEN);
		
		cmdField.setBounds(100,200,400,50);
		
		okButton.setText("OK");
		okButton.setBounds(400,400,100,50);
		okButton.addActionListener(this);
		
		backToLaunchPageButton.setText("LaunchPage");
		backToLaunchPageButton.setBounds(100,400,100,50);
		backToLaunchPageButton.addActionListener(this);
		
		((JFrame) adminFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.setSize(600,600);  
		adminFrame.setLayout(null);  
		adminFrame.setVisible(true);
		
		adminFrame.add(adminJLabel);
		adminFrame.add(backToLaunchPageButton);
		adminFrame.add(okButton);
		adminFrame.add(cmdField);
		adminFrame.add(wrong);
		adminFrame.add(right);
		adminFrame.add(inTime);
		adminFrame.add(notFound);
		adminFrame.add(wrongTime);
		adminFrame.add(helper);
		
		wrong.setVisible(false);
		right.setVisible(false);
		notFound.setVisible(false);
		inTime.setVisible(false);
		wrongTime.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		
		 //Ha a vissza gombra kattint a felhasználó akkor ez az oldal eltűnik és megnyílik egy új kezdőlap
		if(e.getSource() == backToLaunchPageButton) {
			adminFrame.dispose();
			LaunchPage backPage = new LaunchPage();
		}
		//Ha az "ok" gombra kattint.
		if(e.getSource() == okButton) {
			//címkék láthatóságának beállítása, minden "ok" gombnyomás után
			wrong.setVisible(false);
			right.setVisible(false);
			notFound.setVisible(false);
			inTime.setVisible(false);
			wrongTime.setVisible(false);
			
			//A mezőbe beírt szöveg feldarabolása
			String[] todo= cmdField.getText().split("/");
			
			
			
			/*
			 * Ha az első szó "add" és a segéd osztályban a checkkerek true értékkel tértnek vissza,
			 * akkor a filmet hozzáadja a listához a helper-ben lévő addMovies
			 */
			if (todo[0].equals("add") && todo[3] != null) {
				//Az idő értékeinek meghatározása, ellenőrzéshez szükséges
				String[] time = todo[2].split(":");
				if (guiHelper.checkTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]))) {
					if (guiHelper.checkInTime(list, todo[1], todo[2])) {
						try {
							guiHelper.addMovie(list,todo[1], todo[2], todo[3]);
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						wrong.setVisible(false);
						right.setVisible(true);
					}else {
						inTime.setVisible(true);
					}
					
				}else {
					wrongTime.setVisible(true);
				}
			/*
			 * Ha az első szó "rm" és a további szavak nem null-ok, illetve ha tétezik a listában a film,
			 * akkor a helperben lévő rmMovie törli a listából a megadott filmet
			 */
			}else if(todo[0].equals("rm") && todo[1] != null && todo[2] != null) {
				if (guiHelper.MovieCheck(list,todo[1], todo[2])) {
					try {
						guiHelper.rmMovie(list,todo[1], todo[2]);
						wrong.setVisible(false);
						right.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
						wrong.setVisible(true);
					}
				}else {
					notFound.setVisible(true);
				}
			}
			else {
				wrong.setVisible(true);				
			}
		}
	}
}

