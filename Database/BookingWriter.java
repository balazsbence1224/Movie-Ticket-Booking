package Database;

import java.io.*;
import java.util.ArrayList;

/**
 * A booking.txt kezelését irányító osztály
 * @author Bogdán Balázs
 * @version 2022.05.08.
 *
 */
public class BookingWriter {
	
	/**	A booking.txt fájlt írja újra a program,
	 * 	először beolvassa és elmenti az adatokat egy listában, 
	 * 	majd törli ezt a fájlt.
	 * 	A következő lépésben hozzáadja az új foglalást a listához.
	 * 	Újrahozza ugyanazzal a névvel a fájlt és ebbe beleírja a már frissített
	 * 	listát.
	 * @param b Egy új foglalás 
	 * @throws IOException Sikertelen I/O művelet
	 */
	public void reWrite(String b) throws IOException {
		ArrayList<String> bookings = new ArrayList<>();
		String line;
		
		BufferedReader br = new BufferedReader(
				new FileReader("//Users//balazs//java//nagyHazi//src//booking.txt"));
		while ((line = br.readLine()) != null) {
			bookings.add(line);
			
		}
		br.close();
		
		File f = new File("//Users//balazs//java//nagyHazi//src//booking.txt");
		f.delete();
		
		bookings.add(b);
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("//Users//balazs//java//nagyHazi//src//booking.txt"));
		for (int i = 0; i < bookings.size(); i++) {
			bw.write(bookings.get(i) + "\n");
		}
		bw.close();
	}
}
