package Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Logic.GuiHelper;
import Logic.Main;

/**
 * Az osztály egy olyan gui oldalt valósít meg,
 * amiben a felhasználónak ki kell választania a foglalni kívánt ülőhelyek mennyiségét.
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class TicketNumPage implements ActionListener, ItemListener{
	
	//Címkék, gombok és ComboBox inicializálása
	JFrame numFrame = new JFrame("Ticket number page");
	JButton backButton = new JButton();
	JButton nextButton = new JButton();
	JComboBox numBox;
	
	//Választható mennyiségek megadása
	String[] numbers = {"1","2","3","4","5"};
	
	//Választott szám
	public int num;
	
	// A Logicban található class meghívása, amelyben a fontosabb getter,setter függvények vannak.
	GuiHelper helper = new GuiHelper();
	
	public TicketNumPage() {
		/**
		 * A gombok, címkék és Combobox elhelyezése, beállításai.
		 * A frame beállításai, gombok és címkék hozzáadása a framehez.
		 * Láthatóság beállítása.
		 */
		JLabel mainJLabel;
		mainJLabel = new JLabel("Tickets");
		mainJLabel.setBounds(250,150,300,50);
		mainJLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JLabel label;
		label = new JLabel("Normal:");
		label.setBounds(225,200,75,40);
		
		numBox = new JComboBox(numbers);
		numBox.setBounds(300, 200, 100, 40);
		numBox.addItemListener(this);
		
		nextButton.setText("Next");
		nextButton.setBounds(400,400,100,50);
		nextButton.addActionListener(this);
		
		backButton.setText("Back");
		backButton.setBounds(100,400,100,50);
		backButton.addActionListener(this);
		
		numFrame.add(label);
		numFrame.add(mainJLabel);
		numFrame.add(numBox);
		numFrame.add(backButton);
		numFrame.add(nextButton);
		
		
		((JFrame) numFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		numFrame.setSize(600,600);  
		numFrame.setLayout(null);  
		numFrame.setVisible(true);
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		//Ha a Combobox értéke változik, akkor a Választott szám is.
		if (e.getSource() == numBox) {
			String s = numBox.getSelectedItem().toString();
			num = Integer.parseInt(s);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//A "Back" gombra kattintva ez az oldal eltünik és megjelenik az előző, a film és időpont választó. 
		if(e.getSource() == backButton) {
			numFrame.dispose();
			TitlesAndTimesPage back = new TitlesAndTimesPage();
			
		//Ha a "Next" gombra kattint.
		}else if (e.getSource() == nextButton) {
			String s = numBox.getSelectedItem().toString();
			num = Integer.parseInt(s);
			
			if(s == null) {
				num = numBox.getSelectedIndex();
			}
			
			//A foglalni kívánt mennyiség beállítása.
			helper.setNum(num);
			
			//A következő oldal megnyitása, ahol a helyeket tudja kijelőlni a felhasználó, ennek az oldalnak a bezárása.
			numFrame.dispose();
			RoomPage nextPage = new RoomPage();
		}
	}
}
