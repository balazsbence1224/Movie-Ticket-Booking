package Logic;

import java.util.ArrayList;

/**
 * A foglalásal foglalkozó függvény
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class Booking {

	private String email;
	private ArrayList<String> seats = new ArrayList<>();;
	private Integer seatNum;
	private String movie;
	private String time;
	private String[][] seatsforB;

	
	/**
	 * Email beállítása
	 * @param mail Beállítandó email
	 */
	public void setEmail(String mail) {email = mail;}
	
	/**
	 * Film beállíta
	 * @param mo Beállítandó film címe
	 */
	public void setMovie(String mo) {movie = mo;}
	
	/**
	 * Időpont beállítása
	 * @param ti Beállítandó Időpont
	 */
	public void setTime(String ti) {time =ti;}
	
	/**
	 * Ülőhely mennyiségének beállítása
	 * @param num Beállítandó mennyiség
	 */
	public void setSeatNum(int num) {seatNum = num;}
	
	/**
	 * Ülőhelyek beállítása
	 * @param se Lista a kiválasztott ülőhelyekről
	 */
	public void setSeats(ArrayList<String> se) {
		for (int i = 0; i < se.size(); i++) {
			seats.add(se.get(i));
		}
	}
	
	/**
	 * Ülőhelyek beállítása a Foglalás/újraírás részére
	 * @param b 2D tömb az ülőhelyek értékével (0/1)
	 */
	public void setSeatsforB(String[][] b) {seatsforB = b;}
	
	
	/**
	 * Get ülőhelyek a foglalás/úrjaírás részére
	 * @return 2D lista az ülőhelyek értékével (0/1)
	 */
	public String[][] getSeatsForB(){return seatsforB;}
	
	/**
	 * Get Email
	 * @return A beállított email
	 */
	public String getEmail() {return email;}
	
	/**
	 * Get Movie
	 * @return A beállított film címe
	 */
	public String getMovie() {return movie;}
	
	/**
	 * Get Time
	 * @return A beállított, filmhez tartozó időpont
	 */
	public String getTime() {return time;}
	
	/**
	 * Get Ülőhely mennyisége
	 * @return Ülőhelyek mennyisége
	 */
	public int getSeatNum() {return seatNum;}
	
	/**
	 * Get Ülőhelyek
	 * @return Ülöhelyek egy listában
	 */
	public ArrayList<String> getSeats(){
		return seats;
	}
}
