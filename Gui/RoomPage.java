package Gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

import Database.RoomReadAndWrite;
import Logic.GuiHelper;

/**
 * Az osztály egy olyan gui oldalt valósít meg, ahol egy moziteremben lévő székeket lehet kijelölni/lefoglalni.
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class RoomPage implements ActionListener {
	
	// Címkék, gombok és checkbox inicializálása
	JFrame roomFrame = new JFrame("Room Page");
	JButton backB = new JButton();
	JButton nextButton = new JButton();
	JLabel more = new JLabel();
	JLabel less = new JLabel();
	JCheckBox[][] cb = new JCheckBox[5][5];
	
	/*
	 * Ebben a listában van tárolva a filmehez és időponthoz tartozó moziterem értékei
	 * "0"-üres
	 * "1"-foglalt.
	 */
	String[][] free = new String[5][5]; 
	
	// A listában a mát elnevezett ülöhelyek lesznek, pl: 1.line/3.chair.
	ArrayList<String> seats = new ArrayList<>();
	
	// A megfelelő ülöhely kiválasztásának ellenörzéséhez szükséges változók.
	int max;
	int count = 0;
	
	// A Logicban található class meghívása, amelyben a fontosabb getter,setter függvények vannak.
	GuiHelper helper = new GuiHelper();
	
	public RoomPage(){
		
		//A maximum ülöhelyek megadása.
		max = helper.getNum();
		
		//foglaltság meghatározása
		try {
			free = helper.readRoom(helper.getTitle(), helper.getTime());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//checkBox feltöltése az értékekkel
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				String v = free[i][j];
				cb[i][j] = helper.getValue(v);
			}
		}
		
		// Segéd változók a checkBox-ok frame-hez történő hozzáadásához.
		int x = 175;
		int y = 150;
		
		//CheckBoxok hozzáadása a frame-hez
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cb[i][j].setBounds(x,y,35,35);
				x += 50;
				String v = free[i][j];
	
				if(v.equals("0")) {
					
					//Ha üres még a hely akkor a CheckBox egy Icon lesz, amely a green.png
					cb[i][j].setIcon(new ImageIcon("//Users//balazs//java//nagyHazi//src//green.png"));
					cb[i][j].addActionListener(this);
					cb[i][j].setSelected(false);
					
				}else{
					
					/*
					 * Ha már korábban lefoglalták a helyet akkor a CheckBox egy Icon lesz, amely a g.png,
					 * ez szürke színű, illetve az engedélyezését false-ra állítja, így ez a checkBox nem lesz kattintható.
					 */
					cb[i][j].setIcon(new ImageIcon("//Users//balazs//java//nagyHazi//src//g.png"));
					cb[i][j].setEnabled(false);
				}
				
				roomFrame.add(cb[i][j]);
			}
			x = 175;
			y += 50;
		}
		
		//Címkék, gombok beállításai, méretezése, formázása, elhelyezése.
		//Ezek hozzáadása a frame-hez.
		JLabel screen = new JLabel(".-------Screen here-------.");
		screen.setBounds(200,70,200,20);
		screen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JLabel title = new JLabel(helper.getTitle());
		title.setBounds(25,70,100,20);
		
		JLabel time = new JLabel(helper.getTime());
		time.setBounds(25,95,75,20);
		
		JLabel seatnum = new JLabel("Choose " + max + " pc of seat(s)");
		seatnum.setBounds(25,120,150,20);
		
		more.setBounds(25,145,100,20);
		more.setForeground(Color.RED);
		
		less.setBounds(25,145,100,20);
		less.setForeground(Color.RED);
		
		nextButton.setText("Next");
		nextButton.setBounds(400,400,100,50);
		nextButton.addActionListener(this);
		
		backB.setText("Back");
		backB.setBounds(100,400,100,50);
		backB.addActionListener(this);
		
		((JFrame) roomFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		roomFrame.setSize(600,600);  
		roomFrame.setLayout(null);  
		roomFrame.setVisible(true);
		
		roomFrame.add(backB);
		roomFrame.add(nextButton);
		roomFrame.add(screen);
		roomFrame.add(title);
		roomFrame.add(time);
		roomFrame.add(seatnum);
		roomFrame.add(more);
		roomFrame.add(less);
		
		more.setVisible(false);
		less.setVisible(false);	
	}

	public void actionPerformed(ActionEvent e) {
		
		// Ha a "Back" gombra kattint a felhasználó, akkor visszalép a jegy mennyiség kiválasztó oldalra és bezárja ezt az oldalt.
		if(e.getSource() == backB) {
			TicketNumPage back = new TicketNumPage();
			roomFrame.dispose();
		}
		
		// Ha a "Next" gombra kattint, ellenőrzi a kiválasztott helyek számát és összeveti az előző oladlon megadott helyek számával.
		if(e.getSource() == nextButton) {
			less.setVisible(false);
			more.setVisible(false);
			
			// Ha több helyet jelölt ki, mint amennyit megadott az előző oldalon, akkor jelzi, hogy adjon meg annyival kevesebbet, amennyivel többet jelölt ki.
			if (max-count < 0) {
				less.setText("Choose " + Math.abs(max-count) + " less");
				less.setVisible(true);
			
			// Ha kevesebbet jelölt ki, akkor jelzi, hogy adjon meg annyival többet, amennyi hiányzik még.
			}else if(0 < max-count){
				more.setText("Choose " + (max-count) + " more");
				more.setVisible(true);
			
			/*
			 *  Ha a kijelöltek száma megegyezik, a korábbi oldalon megadott számmal, akkor:
			 *  -beállítja a foglalni kívánt székeket
			 *  -beállítja a székek újra írásához szükséges információkat
			 *  -megynitja  akövetkező oldalt, amiben az email címet kell megadni
			 *  -bezárja ezt az oldalt.
			 */
			}else if (count-max == 0) {
				roomFrame.dispose();
				IdForBookingPage next = new IdForBookingPage();
				
				helper.setSeats(seats);
				helper.setSeatsForReWrite(free);	
			}
		}
		
		// A székek mentéséhez szükséges segéd változó.
		String s = new String();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
				// Amennyiben egy checkBox-ra kattint a felhasználó.
				if (e.getSource() == cb[i][j] ) {
					
					/*
					 * Ha a kattintás után ez a CheckBox nincs kiválasztva, tehát, ha előzőleg ki volt választva, 
					 * de a felhasználó úgy döntött hogy mégsem ide szeretne foglalni
					 */
					if(!cb[i][j].isSelected()) {
						
						//Vissza vált a green.png -re,zöld színűre az Icon.
						cb[i][j].setIcon(new ImageIcon("//Users//balazs//java//nagyHazi//src//green.png"));
						
						//A count számlálóból levon 1-et.
						count--;
						
						//Visszaállítja free tömb értékét "0"-ra.
						free[i][j] = "0";
						
						//Megkeresi és törli a listából az ülőhelyet.
						s = (i+1) + ".line/" + (j+1) + ".chair";
						for (int k = 0; k < seats.size(); k++) {
							if (seats.get(k).equals(s)) {
								seats.remove(k);
							}
						}						
					}
					//Ha kattintás után a CheckBox kivan választva.
					if (cb[i][j].isSelected()) {
						
						//Akkor az Icon a y.png re vált, sárga színt kap.
						cb[i][j].setIcon(new ImageIcon("//Users//balazs//java//nagyHazi//src//y.png"));
						
						// A count számlálóhoz hozzáad 1-et
						count ++;
						
						//A free tömb értékét "1"-re, foglaltra állítja
						free[i][j] = "1";
						
						//Hozzáadja a listához az ülőhelyet
						s = (i+1) + ".line/" + (j+1) + ".chair";
						seats.add(s);
					}
				}
			}
		}
	}
}
