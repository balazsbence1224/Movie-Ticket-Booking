package Database;

import java.io.*;
import java.util.*;

import Logic.Movie;

/**
 * A filmek a movies.txt fájlból történő olvasását,újraírását végrahjtó osztály
 * @author Bogdán Balázs
 * @version 2022.05.08.
 *
 */
public class ReadAndWriteMovies {
	
	/**
	 * Beolvassa soronként a movies.txt-t, majd hozzáadja egy listához, végül a függvény a listát adja vissza
	 * @return Film listát ad vissza
	 * @throws Exception Sikertelen művelet
	 */
	public ArrayList<Movie> readMovies() throws Exception{
	
		BufferedReader br = new BufferedReader(
				new FileReader("//Users//balazs//java//nagyHazi//src//movies.txt"));

		String line;
		ArrayList<Movie> movies = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			String[] content = line.split("/");
			
			Movie movie = new Movie(content[0],content[1],content[2]);
			movies.add(movie);
		}
		br.close();
		return movies;
	}

	
	/**
	 * Törli a movies.txt fájlt, majd létrhozza ugyanezzel a névvel és beleírja a kapott listát.
	 * @param m Film lista
	 * @throws IOException Sikertelen I/O művelet
	 */
	public void reWriteMovies(ArrayList<Movie> m) throws IOException{
		File f = new File("//Users//balazs//java//nagyHazi//src//movies.txt");
		f.delete();
		
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("//Users//balazs//java//nagyHazi//src//movies.txt"));
		for (int i = 0; i < m.size(); i++) {
			bw.write(m.get(i).toString() + "\n");
		}
		bw.close();
	}
}
