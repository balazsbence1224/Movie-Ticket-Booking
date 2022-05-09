package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import Database.ReadAndWriteMovies;
import Logic.GuiHelper;
import Logic.Movie;

/**
 * Az osztály egy olyan gui oldalt jelenít meg amelyben a felhasználó választhat a filmek és időpontok között.
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class TitlesAndTimesPage implements ActionListener, ItemListener{
	
	// Címkék, gombok, ComboBoxok inicializálása
	JFrame titleFrame = new JFrame("Selecting page");
	JButton backB = new JButton();
	JButton okButton = new JButton();
	JButton nexButton = new JButton();
	JButton backButton = new JButton();
	
	JLabel titleLabel = new JLabel();
	JLabel time = new JLabel("Time:");
	JLabel movie = new JLabel("Movie:");
	JLabel wrong1 = new JLabel("Movie not selected");
	JLabel wrong2 = new JLabel("Time not selected");
	JLabel desJLabel = new JLabel();
	
	JComboBox cBox;
	JComboBox timeBox;
	
	// A Logicban található class meghívása, amelyben a fontosabb getter,setter függvények vannak.
	GuiHelper guiHelper = new GuiHelper();

	// Az aktuálisan kiválasztott adatok és a hozzá tartozó leírás inicializálása.
	public String movieString;
	public String timeString;
	public String des;

	//Lista a filmeknek és az időpontoknak
	ArrayList<Movie> list = new ArrayList<>();
	ArrayList<String> timeStrings;
	
	public TitlesAndTimesPage()  {
		
		//Filmes lista letöltése
		try {
			list = guiHelper.getMovies();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//Filmes ComboBox feltöltése
		try {
			cBox = new JComboBox(guiHelper.GetMovieTitles(list).toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Cimkék, gombok, Comboboxok és a frame beállításai, elhelyezése, formázása és hozzáadása a frame-hez
		cBox.setBounds(180,175,100,100);
		cBox.addItemListener(this);
		
		titleLabel.setBounds(180,175,200,100);
		time.setBounds(120,200,100,100);
		movie.setBounds(120,175,100,100);
		
		JLabel mainLabel = new JLabel("Booking");
		mainLabel.setBounds(250,150,300,50);
		mainLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		wrong1.setBounds(200,250,100,40);
		wrong2.setBounds(200,250,100,40);
		
		wrong1.setForeground(Color.RED);
		wrong2.setForeground(Color.RED);
		
		okButton.setText("OK");
		okButton.setBounds(400,400,100,50);
		okButton.addActionListener(this);
		
		nexButton.setText("Next");
		nexButton.setBounds(400,400,100,50);
		nexButton.addActionListener(this);
		
		backB.setText("Back");
		backB.setBounds(100,400,100,50);
		backB.addActionListener(this);
		
		backButton.setText("Back");
		backButton.setBounds(100,400,100,50);
		backButton.addActionListener(this);
		
		titleFrame.add(backB);
		titleFrame.add(okButton);
		titleFrame.add(mainLabel);
		titleFrame.add(movie);
		titleFrame.add(cBox);
		titleFrame.add(wrong1);
		titleFrame.add(wrong2);
		
		wrong1.setVisible(false);
		wrong2.setVisible(false);
		
		timeBox = new JComboBox();
		timeBox.setBounds(280,200,100,100);
		timeBox.addItemListener(this);
		
		((JFrame) titleFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		titleFrame.setSize(600,600);  
		titleFrame.setLayout(null);  
		titleFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Az "ok" gombra kattintva
		if(e.getSource() == okButton) {
			
			//Ellenörzi, hogy kilett-e választva film, ha nem akkor beállítja a ComboBoxban szereplő első filmet.  
			if(movieString == null) {
				cBox.setSelectedIndex(0);
				movieString = cBox.getSelectedItem().toString();
			}
			
			// A film leírásának meghatározása
			des = guiHelper.getDes(movieString);
			
			// A leírás címke beállításai
			desJLabel.setText("Category: " + des);
			desJLabel.setBounds(100,220,550,100);
			titleFrame.add(desJLabel);
			desJLabel.setVisible(true);
			
			// Az előző lépésben látható gombok és a Combobox eltüntetése
			okButton.setVisible(false);;
			backB.setVisible(false);
			cBox.setVisible(false);
			movie.setVisible(false);
				
			//A következő lépéshez szükséges gombok, címkék és az időpontokat tároló ComboBox frame-hez hozzáadása
			titleFrame.add(nexButton);
			titleFrame.add(backButton);
			titleFrame.add(time);
			titleFrame.add(timeBox);
			titleLabel.setText("Selected movie: " + movieString);
			titleFrame.add(titleLabel);
			
			//Ezeknek a megjelenítése
			nexButton.setVisible(true);
			backButton.setVisible(true);
			time.setVisible(true);
			timeBox.setVisible(true);
			titleLabel.setVisible(true);
			
			//Az időpontokat tartalmazó lista feltöltése
			timeStrings = guiHelper.GetTimes(list, movieString);
			
			//Az időpontokat tartalmazó Combobox feltöltése
			for (int i = 0; i < timeStrings.size(); i++) {
				timeBox.addItem(timeStrings.get(i));
			}

		}else if (e.getSource() == backB) {
			
			//Ha a "Back" gombra kattintunk, amikor Filmet kellene választani, akkor visszalép a kezdő oldalra és bezárja ezt.
			titleFrame.dispose();
			LaunchPage back = new LaunchPage();
			
		//A "Next" gombra kattintva
		}else if (e.getSource() == nexButton) {
			
			//Ellenörzi, hogy van-e választva időpont, ha nincs akkor beállítja az első elemét a ComboBox-nak.
			if (timeString == null) {
				timeBox.setSelectedIndex(0);
				timeString = timeBox.getSelectedItem().toString();
				
			//Aztán beállítja a foglaláshoz a kiválasztott adatokat, továbblép a jegy mennyiség választó oldara és bezárja ezt.
			}else {
				guiHelper.setTitleAndTime(movieString, timeString);
				
				titleFrame.dispose();
				TicketNumPage nexPage = new TicketNumPage();
			}
			
		// Ha a "Back" gombra kattint miközben időpontot kellene választani
		}else if (e.getSource() == backButton) {
			
			//A ComboBox-ból ami az időpontokat tárolta törli az elemeket.
			for (int i = 0; i < timeStrings.size(); i++) {
				timeBox.removeItem(timeStrings.get(i));
			}
			//A listából is töröl minden időpontot
			timeStrings = guiHelper.removeTimes(timeStrings);
			
			//A gombok, címkék...láthatóságának átállítása false-ra
			desJLabel.setVisible(false);
			nexButton.setVisible(false);
			backButton.setVisible(false);
			time.setVisible(false);
			timeBox.setVisible(false);
			titleLabel.setVisible(false);
			
			//Az előző lépésben használt gombok, címkék... átállítása true-ra
			backB.setVisible(true);
			okButton.setVisible(true);
			cBox.setVisible(true);
			movie.setVisible(true);
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		//Aktuálisan kiválasztott Film
		if (e.getSource() == cBox) {
			
			//Film változó meghatározása
			movieString = cBox.getSelectedItem().toString();
		}
		//Aktuálisan kiválasztott időpont
		if(e.getSource() == timeBox) {
			
			//Időpont változó meghatározása
			if (timeBox.getSelectedItem() == null) {
				timeString = null;
			}else {
				timeString = timeBox.getSelectedItem().toString();
			}
		}
	}
}
