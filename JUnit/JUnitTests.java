package JUnit;

import Logic.Booking;
import Logic.GuiHelper;
import Logic.Movie;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import Database.ReadAndWriteMovies;
import Database.RoomReadAndWrite;

/**
 * @author Bogdán Balázs
 * @version 2022.05.09.
 */
public class JUnitTests {
	Booking booking = new Booking();
	GuiHelper helper = new GuiHelper();
	ReadAndWriteMovies moviesIO = new ReadAndWriteMovies();
	RoomReadAndWrite roomIO = new RoomReadAndWrite();
	

	@Test
	public void testId_Email() {
		String email = booking.getEmail();
		String id = helper.getId();
		
		assertEquals(email, id);
	}
	@Test
	public void Title_Movie() {
		String title = helper.getTitle();
		String movie = booking.getMovie();
		
		assertEquals(title, movie);
	}

	@Test
	public void Time() {
		boolean time = helper.checkTime(22, 05);
		boolean thatsTrue = true;
		
		assertEquals(time,thatsTrue);
	}
	@Test
	public void IdIsString() {
		String id = helper.getId();
		boolean s = false;
		try {
		    int v = Integer.parseInt(id);
		    s = false;
		} catch (NumberFormatException e) {
		    s = true;
		}
		assertTrue(s);
	}
	@Test
	public void TimeGet() {
		Movie movie = new Movie("Batman", "18:30", "action");
		String time = "18:30";
		
		assertEquals(movie.getTime(), time);
	}
	@Test
	public void TitleGet(){
		Movie movie = new Movie("Batman", "18:30", "action");
		String title =	"Batman";
		
		assertEquals(title, movie.getTitle());
	}
	@Test
	public void DesGet() {
		Movie movie = new Movie("Batman", "18:30", "action");
		String des = "action";
		
		assertEquals(des, movie.getDes());
	}
	@Test
	public void TitleIsString() {
		String title = helper.getTitle();
		boolean s = false;
		try {
		    int v = Integer.parseInt(title);
		    s = false;
		} catch (NumberFormatException e) {
		    s = true;
		}
		assertTrue(s);
	}
	@Test
	public void set_getTime() {
		booking.setTime("18:10");
		String time = "18:10";
		assertEquals(time, booking.getTime());
	}
	@Test
	public void set_getMovie() {
		booking.setMovie("Batman");
		String movie = "Batman";
		
		assertEquals(booking.getMovie(), movie);
	}
}
