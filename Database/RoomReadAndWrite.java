package Database;

import java.io.*;

/**
 * A mozitermek olvasását, törlését és írását végrahsjtó osztály
 * @author Bogdán Balázs
 * @version 2022.05.08.
 */
public class RoomReadAndWrite {
	
	/**A "write" a kapott adatok használatával létrehoz egy nevet,
	 * majd ezzel a névvel létrehoz egy .txt fájlt,
	 * ebbe soronként(5) és oszloponként(5) feltölti "0"-sokkal.
	 * @param m Film címe
	 * @param t Film időpontja
	 * @throws IOException Sikertelen I/O művelet
	 */
	public void write(String m, String t) throws IOException {
		String name = m + "_" + t;
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("//Users//balazs//java//nagyHazi//src//rooms//" + name + ".txt"));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(j != 4) {
					bw.write("0" + " ");
				}else {
					bw.write("0");
				}
			}
			if (i != 4) {
				bw.write("\n");
			}else {
				;
			}
		}
		bw.close();
	}
	
	/**
	 * A "read" függvény a kapott adatokkal (ugyanazzal a technikával mint az előbbi függvényben)
	 * létrehoz egy nevet, ezzel a névvel ellátott fájlt beolvassa soronként és oszloponként.
	 * Az adatokat eltárolja egy 2 dimenziós tömbben amit végül a függvény visszaad.
	 * @param m Film címe
	 * @param t Film időpontja
	 * @return Terem adatok 2 dimenzios tömbben
	 * @throws IOException Sikertelen I/O művelet
	 * @throws FileNotFoundException File nem található
	 */
	public String[][] read(String m, String t) throws IOException, FileNotFoundException{
		
		String name = m + "_" + t;
		BufferedReader br = new BufferedReader(
				new FileReader("//Users//balazs//java//nagyHazi//src//rooms//" + name + ".txt"));
		String[][] free= new String[5][5];
		String line;
		
		for (int i = 0; i < 5; i++) {
			line = br.readLine();
			String[] content = line.split(" ");
			for (int j = 0; j < 5; j++) {
				free[i][j] = content[j];
			}
		}
		
		br.close();
		return free;
	}
	
	/**
	 * A "reWrite" a kapott adatokkal ugyanazzal a technikával mint az előbbi függvényben)
	 * létrehoz egy nevet, ezzel a névvel ellátott fájlt törli.
	 * Ugyanezzel a névvel létrehoz egy fájlt, amibe a kapott 2 dimenziós tömböt írja bele.
	 * @param list 2 dimenziós tömb a terem foglaltságáról
	 * @param m Film címe
	 * @param t Film időpontja
	 * @throws IOException Sikertelen I/O művelet
	 */
	public void reWrite(String[][] list, String m, String t) throws IOException {
		
		String name = m + "_" + t;
		File f = new File("//Users//balazs//java//nagyHazi//src//" + name + ".txt");
		f.delete();
		
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("//Users//balazs//java//nagyHazi//src//rooms//" + name + ".txt"));
		for (int i = 0; i < 5; i++) {
			if (i != 4) {
				for (int j = 0; j < 5; j++) {
					if (j != 4) {
						bw.write(list[i][j] + " ");
					}else {
						bw.write(list[i][j]);
					}	
				}
				bw.write("\n");
			}else {
				for (int j = 0; j < 5; j++) {
					if (j != 4) {
						bw.write(list[i][j] + " ");
					}else {
						bw.write(list[i][j]);
					}	
				}
			}
		}
		bw.close();
	}
	/**
	 * A "removeRoom" a kapott adatokkal ugyanazzal a technikával mint az előbbi függvényben)
	 * létrehoz egy nevet, ezzel a névvel ellátott fájlt törli.
	 * @param n Film címe
	 * @param t Film időpontja
	 * @throws IOException Sikertelen I/O művelet
	 */
	public void removeRoom(String n,String t) throws IOException{
		String[] timeStrings = t.split(":");
		String fname = n + "_" + timeStrings[0] + "/" + timeStrings[1];
		File f = new File("//Users//balazs//java//nagyHazi//src//" + fname + ".txt");
		f.delete();
	}
}
