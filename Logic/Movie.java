package Logic;

/**
 * Film osztály
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class Movie {
	private String title;
	private String time;
	private String description;
	
	/**
	 * Konstruktor
	 * @param t Film címe
	 * @param ti Film időpontja
	 * @param d Film leírása
	 */
	public Movie (String t, String ti, String d) {
		title = t;
		time = ti;
		description = d;
	}
	
	/**
	 * Get film címe
	 * @return Film címe
	 */
	public String getTitle() {return title;}
	
	/**
	 * Get Időpont
	 * @return Időpont
	 */
	public String getTime() {return time;}
	
	/**
	 * Get leírás
	 * @return Leírás
	 */
	public String getDes() {return description;}
	
	/**
	 * Formázott kiírás
	 */
	public String toString() {
		return title + "/" + time + "/" + description;
	}
}
