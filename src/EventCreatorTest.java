import static org.junit.Assert.*;
import java.util.Scanner;

import org.junit.Test;

public class EventCreatorTest {
    
	@Test
	public void testStringReader() {
		String[] iData = {"a", "ab", "abc"};
		for(int i = 0; i < iData.length; i++) {
			Scanner sc = new Scanner(iData[i]);
			String result = EventCreator.stringReader(sc);
			assertEquals(iData[i], result);
		}
		try {
			Scanner sc = new Scanner("");
			EventCreator.stringReader(sc);
		} catch (Exception e ) {
		}
	}
	
	
	@Test
	public void testDateReader() {
		// Leaf year
		Scanner sc = new Scanner("2016/02/29");
		String result = EventCreator.dateReader(sc);
		assertEquals("2016/02/29", result);
	}

	@Test
	public void testTimeReader() {
		// Correct time
		String[] ctime = {"00:00:00", "01:59:00", "12:00:00"};
		// Wrong time
		String[] wtime = {"24:00:00", "25:01:00", "-12:00:00"};
		for(int i = 0; i < ctime.length; i++){
			assertTrue(EventCreator.isValidTime(ctime[i]));
		}
		for(int i = 0; i < wtime.length; i++){
			assertFalse(EventCreator.isValidTime(wtime[i]));
		}	
	}

	@Test
	public void testIsVaildDate(){
		// Correct dates
		String[] cdate = {"2016/01/31", "2016/02/29", "2016/03/31", "2016/05/31", "2016/07/31",
				"2016/08/31", "2016/10/31", "2016/12/31", "2400/02/29"};
		// Wrong date
		String[] wdate = {"2016/04/31", "2016/06/31", "2016/09/31", "2016/11/31", "2015/02/29", 
				"2100/02/29"};

		for(int i = 0; i < cdate.length; i++){
			assertTrue(EventCreator.isValidDate(cdate[i]));
		}
		for(int i = 0; i < wdate.length; i++){
			assertFalse(EventCreator.isValidDate(wdate[i]));
		}
	}

	@Test
	public void testIsVaildTime(){
		// Correct time
		String[] ctime = {"00:00:00", "01:59:00", "12:00:00"};
		// Wrong time
		String[] wtime = {"24:00:00", "25:01:00", "-12:00:00"};
		for(int i = 0; i < ctime.length; i++){
			assertTrue(EventCreator.isValidTime(ctime[i]));
		}
		for(int i = 0; i < wtime.length; i++){
			assertFalse(EventCreator.isValidTime(wtime[i]));
		}	
	}

	@Test
	public void testIsValidEvent(){
		// Correct event
		String[][] cevent = {{"2016/01/01", "00:00:00", "2016/01/01", "00:01:00"},
				{"2016/01/01", "01:00:00", "2016/01/02", "00:00:00"}};
		String[][] wevent = {{"2016/01/01", "01:00:00", "2016/01/01", "00:00:00"},
				{"2016/01/02", "12:00:00", "2016/01/01", "15:00:00"}};
		
		for(int i = 0; i < cevent.length; i++){
			assertTrue(EventCreator.isValidEvent(cevent[i][0], cevent[i][1], cevent[i][2], cevent[i][3]));		
		}
		
		for(int i = 0; i < wevent.length; i++){
			assertFalse(EventCreator.isValidEvent(wevent[i][0], wevent[i][1], wevent[i][2], wevent[i][3]));	
		}
	}
	
	@Test
	public void testClassReader(){
		Scanner sc = new Scanner("\n");
		String result = EventCreator.classReader(sc);
		assertEquals("PUBLIC", result);

		sc = new Scanner("p");
		result = EventCreator.classReader(sc);
		assertEquals("PRIVATE", result);
		
		sc = new Scanner("c");
		result = EventCreator.classReader(sc);
		assertEquals("CONFIDENTIAL", result);
	}

}
