package Logic;

import java.io.*;
import java.util.*;
import javax.swing.*;

import Database.RoomReadAndWrite;
import Database.BookingWriter;
import Database.ReadAndWriteMovies;

/**
 * Az osztályban getter, setter, check-er függvények vannak amelyek elősegitík a gui mükődését 
 * és kapcsolatban van az adatbázis package osztályaival.
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class GuiHelper {
	
	//Adatbázis osztályainak példányosítása
	ReadAndWriteMovies readAndWrite = new ReadAndWriteMovies();
	RoomReadAndWrite roomWrite = new RoomReadAndWrite();
	BookingWriter bookingWriter = new BookingWriter();
	
	
	
	/**
	 * A Booking osztályból hívja meg a foglaláshoz/újraíráshoz szükséges settert
	 * @param l 2D tömb a mozi terem foglaltságáról
	 */
	public void setSeatsForReWrite(String[][] l) {
		Main.booking.setSeatsforB(l);
	}
	
	/**
	 * A Booking osztályból hívja meg az ülőhely számát beállító settert
	 * @param num ülőhelyek száma
	 */
	public void setNum(int num) {
		Main.booking.setSeatNum(num);
	}
	
	/**
	 * A booking osztályból hívja meg az ülőhelyeket beállító settert
	 * @param s ülőhelyek lista
	 */
	public void setSeats(ArrayList<String> s) {
		Main.booking.setSeats(s);
	}
	
	/**
	 * A booking osztályból hívja meg az email-t/id-t beállító settert
	 * @param s email
	 */
	public void setId(String s) {
		Main.booking.setEmail(s);
	}
	
	/**
	 * A booking osztályból hívja meg a film címet és az időpontot beállító settert
	 * @param movieString Film címe
	 * @param timeString Időpont
	 */
	public void setTitleAndTime(String movieString, String timeString) {
		Main.booking.setMovie(movieString);
		Main.booking.setTime(timeString);
	}
	
	/**
	 * Stringbe rendezi az adatokat a foglalás txt-be írásához
	 * @return Az írandó sor
	 */
	public String getBookingWriter() {
		return getId() + " - " + getTitle() + " - " + getTime() + " - " + getSeatsToString();
	}
	
	/**
	 * Meghívja a booking osztály Film címét eredményül kapó getterét
	 * @return Film címe
	 */
	public String getTitle() {
		return Main.booking.getMovie();
	}
	
	/**
	 * Meghívja a booking osztály Film időpontját eredményül kapó getterét
	 * @return Film időpontja
	 */
	public String getTime() {
		return Main.booking.getTime();
	}
	
	/**
	 * Meghívja a booking osztály jegyek számát eredményül kapó getterét
	 * @return Jegyek száma
	 */
	public int getNum() {
		return Main.booking.getSeatNum();
	}
	
	/**
	 * Meghívja a booking osztály email címét eredményül kapó getterét
	 * @return Email cím
	 */
	public String getId() {
		return Main.booking.getEmail();
	}
	
	/**
	 * Meghívja a booking osztály azon getterét amely eredményül visszaadja az ülőhelyek ujraírásához szükséges adatokat
	 * @return 2d tömb az újraírandó adatokról
	 */
	public String[][] getSeatsForReWrite(){
		return Main.booking.getSeatsForB();
	}
	
	/**
	 * A kapott értéket átalakítja CheckBox típusura
	 * @param v érték
	 * @return CheckBox v értékkel
	 */
	public JCheckBox getValue(String v) {
		JCheckBox value = new JCheckBox(v);
		return value;
	}
	
	/**
	 * Egy listát hoz létre amibe beolvassa a filmeket
	 * @return Film tipusú lista
	 */
	public ArrayList<Movie> getMovies(){
		ArrayList<Movie> list = new ArrayList<>();
		try {
			list = readAndWrite.readMovies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Egy String-be helyezi a bookingban lefoglalt ülőhelyeket
	 * @return String amelyben az összes ülőhely szerepel
	 */
	public String getSeatsToString(){
		ArrayList<String> seats = Main.booking.getSeats();
 		String toString = new String();
		for (int i = 0; i < seats.size(); i++) {
			toString += seats.get(i) + ", ";
		}
		return toString;
	}
	
	/**
	 * Megkeresi a film leírását, ehhez beolvassa a filmekkel foglalkozó txt fájlt
	 * és megkeresi a megadott film címéhez tartozó leírást
	 * @param m Film címe
	 * @return Leírás
	 */
	public String getDes(String m) {
		ArrayList<Movie> ms = new ArrayList<>();
		
		try {
			ms = readAndWrite.readMovies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String des = null;
		int i = 0;
		while (des == null) {
			if(ms.get(i).getTitle().equals(m)) {
				des = ms.get(i).getDes();
				break;
			}else {
				i++;
			}
		}
		return des;
	}
	
	/**
	 * Mivel egy filmhez több időpont is tartozik, ezért többször szerepel a movies.txt fájlban,
	 * a függvény egy listába rendezi a film címeket, hogy mindegyik csak 1x szerepeljen
	 * @param movies Film lista
	 * @return Film címek (mindegyik 1x szerepel)
	 */
	public ArrayList<String> GetMovieTitles(ArrayList<Movie> movies) {
		ArrayList<String> title = new ArrayList<>();
		for (int i = 0; i < movies.size(); i++) {
			
			if(title.contains(movies.get(i).getTitle())) {
				;
			}else {
				title.add(movies.get(i).getTitle());
			}
		}
		return title;
	}
	
	/**
	 * Filmhez tartozó időpontok kigyüjtése és listába rendezése
	 * @param movies Filmek lista
	 * @param m Film címe
	 * @return A filmhez tartozó időpontok
	 */
	public ArrayList<String> GetTimes(ArrayList<Movie> movies,String m) {
		ArrayList<String> times = new ArrayList<>();
		for (int i = 0; i < movies.size(); i++) {
			if (movies.get(i).getTitle().equals(m)) {
				
				if (times.contains(movies.get(i).getTime())) {
					;
				}else {
					times.add(movies.get(i).getTime());
				}
			}
		}
		return times;
	}

	/**
	 * Mozi termeket újra író függvényt hívja meg a RoomReadAndWrite osztályból
	 * @param list 2D tömb a moziterem adataival (0-üres, 1-foglalt)
	 * @param m Film címe
	 * @param t Film időpontja
	 * @throws IOException Sikertelen I/O művelet
	 */
	public void reWriteRoom(String[][] list, String m, String t) throws IOException {
		roomWrite.reWrite(list,m,t);
	}
	
	/**
	 * Meghívja a RoomReadAndWrite osztályban lévő terem újraíró függvényt
	 * @param b Hozzáadandó sor
	 * @throws IOException Sikertelen I/O művelet
	 */
	public void reWriteBooking(String b) throws IOException {
		bookingWriter.reWrite(b);
	}
	
	/**
	 * Meghívja a RoomReadAndWrite osztályban lévő terem beolvasó függvényt
	 * @param m Film címe
	 * @param t Film időpontja
	 * @return Terem adatok 2 dimenzios tömbben
	 * @throws IOException Sikertelen I/O művelet
	 * @throws FileNotFoundException File nem található
	 */
	public String[][] readRoom(String m, String t) throws IOException, FileNotFoundException{
		String[][] back = new String[5][5];
		back = roomWrite.read(m, t);
		return back;
	}
	
	/**
	 * Megvizsgálja, hogy a megadott időpont helyes-e 
	 * @param h óra
	 * @param m perc
	 * @return Igaz ha helyes, Hamis ha helytelen
	 */
	public boolean checkTime(int h, int m) {
		if (24 < h || 60 < m) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Megvizsgálja, hogy van e ilyen nevű és időpontú film a Film listában
	 * @param l Filmek listája
	 * @param title Film címe
	 * @param time Film időpontja
	 * @return Igaz ha nincs ilyen film, hamis ha van ilyen film
	 */
	public boolean checkInTime(ArrayList<Movie> l, String title, String time ) {
		boolean check = true;
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getTitle().equals(title) && l.get(i).getTime().equals(time)) {
				check = false;
				break;
			}
		}
		return check;
	}
	
	/**
	 * Megvizsgálja, hogy van e ilyen nevű és időpontú film a Film listában
	 * @param l Filmek listája
	 * @param title Film címe
	 * @param time film időpontja
	 * @return Igaz van ilyen film, hamis ha nincs ilyen film
	 */
	public boolean MovieCheck(ArrayList<Movie> l, String title,String time) {
		boolean check =false;
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getTitle().equals(title) && l.get(i).getTime().equals(time)) {
				check = true;
				break;
			}
		}
		return check;
	}
	
	/**
	 * Hozzáadja a listához a filmet, és létrehoz a filmhez és időponthoz tartozó üres (0-kal feltöltött) termet
	 * @param l Filmek listája
	 * @param title Film címe
	 * @param time Film időpontja
	 * @param des Film leírása
	 */
	public void addMovie(ArrayList<Movie> l, String title, String time, String des) {
		try {
			Movie m = new Movie(title, time, des);
			l.add(m);
			readAndWrite.reWriteMovies(l);
			roomWrite.write(title, time);

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	/**
	 * Törli a filmet és hozzá tartozó időpontot, illetve törli a hozzá tartozó szobát
	 * @param l Filmek listája
	 * @param title Film címe
	 * @param time Film időpontja
	 */
	public void rmMovie(ArrayList<Movie> l, String title, String time) {
		String des = new String();
		
		try {
			roomWrite.removeRoom(title, time);
			
			for (int i = 0; i < l.size(); i++) {
				if (l.get(i).getTitle().equals(title) && l.get(i).getTime().equals(time)) {
					des = l.get(i).getDes();
				}
			}
			Movie m = new Movie(title, time, des);
			for (int i = 0; i < l.size(); i++) {
				if(l.get(i).getTitle().equals(m.getTitle()) && l.get(i).getTime().equals(m.getTime())) {
					l.remove(i);
					readAndWrite.reWriteMovies(l);
				}
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Törli a listában található összes időpontot
	 * @param t Időpontokat tartalmazó lista
	 * @return Üres lista
	 */
	public ArrayList<String> removeTimes(ArrayList<String> t) {
		ArrayList<String> newT = new ArrayList<>();
		t.clear();
		newT = t;
		
		return newT;
	}
}
