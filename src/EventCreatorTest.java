import static org.junit.Assert.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class EventCreatorTest {

	//string reader test
	@Test
	public void testStringReader() {
		String[] iData = { "a", "ab", "abc" };
		for (int i = 0; i < iData.length; i++) {
			Scanner sc = new Scanner(iData[i]);
			String result = EventCreator.stringReader(sc);
			assertEquals(iData[i], result);
		}
		try {
			Scanner sc = new Scanner("");
			EventCreator.stringReader(sc);
		} catch (Exception e) {
			// Nothing means there is an exception
		}
	}

	//date reader test
	@Test
	public void testDateReader() {
		// Leap year
		Scanner sc = new Scanner("2016/02/29");
		String result = EventCreator.dateReader(sc);
		assertEquals("2016/02/29", result);
	}

	//time reader test
	@Test
	public void testTimeReader() {
		// Correct time
		String[] ctime = { "00:00:00", "01:59:00", "12:00:00" };
		// Wrong time
		String[] wtime = { "24:00:00", "25:01:00", "-12:00:00", "a/a/a", "a" };
		for (int i = 0; i < ctime.length; i++) {
			assertTrue(EventCreator.isValidTime(ctime[i]));
		}
		for (int i = 0; i < wtime.length; i++) {
			assertFalse(EventCreator.isValidTime(wtime[i]));
		}
	}

	//date validation test
	@Test
	public void testIsVaildDate() {
		// Correct dates
		String[] cdate = { "2016/01/31", "2016/02/29", "2016/03/31", "2016/05/31", "2016/07/31", "2016/08/31",
				"2016/10/31", "2016/12/31", "2400/02/29" };
		// Wrong date
		String[] wdate = { "2016/04/31", "2016/06/31", "2016/09/31", "2016/11/31", "2015/02/29", "2100/02/29", "a/a/a", "a" };

		for (int i = 0; i < cdate.length; i++) {
			assertTrue(EventCreator.isValidDate(cdate[i]));
		}
		for (int i = 0; i < wdate.length; i++) {
			assertFalse(EventCreator.isValidDate(wdate[i]));
		}
	}

	//time validation test
	@Test
	public void testIsVaildTime() {
		// Correct time
		String[] ctime = { "00:00:00", "01:59:00", "12:00:00" };
		// Wrong time
		String[] wtime = { "24:00:00", "25:01:00", "-12:00:00" };
		for (int i = 0; i < ctime.length; i++) {
			assertTrue(EventCreator.isValidTime(ctime[i]));
		}
		for (int i = 0; i < wtime.length; i++) {
			assertFalse(EventCreator.isValidTime(wtime[i]));
		}
	}

	//event validation test
	@Test
	public void testIsValidEvent() {
		// Correct event
		String[][] cevent = { { "2016/01/01", "00:00:00", "2016/01/01", "00:01:00" },
				{ "2016/01/01", "01:00:00", "2016/01/02", "00:00:00" } };
		String[][] wevent = { { "2016/01/01", "01:00:00", "2016/01/01", "00:00:00" },
				{ "2016/01/02", "12:00:00", "2016/01/01", "15:00:00" } };

		for (int i = 0; i < cevent.length; i++) {
			assertTrue(EventCreator.isValidEvent(cevent[i][0], cevent[i][1], cevent[i][2], cevent[i][3]));
		}

		for (int i = 0; i < wevent.length; i++) {
			assertFalse(EventCreator.isValidEvent(wevent[i][0], wevent[i][1], wevent[i][2], wevent[i][3]));
		}
	}

	//class reader test
	@Test
	public void testClassReader() {
		Scanner sc = new Scanner("\n");
		String result = EventCreator.classReader(sc);
		assertEquals("PUBLIC", result);

		sc = new Scanner("p");
		result = EventCreator.classReader(sc);
		assertEquals("PRIVATE", result);

		sc = new Scanner("c");
		result = EventCreator.classReader(sc);
		assertEquals("CONFIDENTIAL", result);
		try {
			sc = new Scanner("fafaf");
		} catch (Exception e) {
			// Nothing means there is an exception
		}
	}

	//latitude reader test
	@Test
	public void testLatReader() {
		String testLat[] = { "-91.0", "-90.0", "-45.5", "0.0", "30.0", "60.123456", " 90.0", "90.1" };
		for (int i = 0; i < testLat.length; i++) {
			try {
				Scanner sc = new Scanner(testLat[i]);
				String result = EventCreator.latReader(sc);
				assertEquals(testLat[i], result);
			} catch (Exception e) {
				// Nothing means there is an exception
			}
		}
	}

	//longitude reader test
	@Test
	public void testLonReader() {
		String testLon[] = { "-191.0", "-180.0", "-78.6", "0.0", "66.0", "60.123456", " 180.0", "180.1" };
		for (int i = 0; i < testLon.length; i++) {
			try {
				Scanner sc = new Scanner(testLon[i]);
				String result = EventCreator.lonReader(sc);
				assertEquals(testLon[i], result);
			} catch (Exception e) {
				// Nothing means there is an exception
			}
		}
	}

	//geo data input option
	@Test
	public void testMakeOptional() {
		Scanner sc = new Scanner("1");
		boolean result = EventCreator.makeOptional(sc, "add the geographical position of your event");
		assertEquals(true, result);

		sc = new Scanner("2");
		result = EventCreator.makeOptional(sc, "add the geographical position of your event");
		assertEquals(false, result);
	}
	
	//test a function that appends 0's to the correct locations for dates and times
	@Test
	public void testCompleteString() {
		String testDateandTime[] = 
			{ "1/1/1", "1111/11/11", "1:1:1", "11:11:11"};
		assertEquals(EventCreator.completeString(testDateandTime[0], "/"),"00010101");
		assertEquals(EventCreator.completeString(testDateandTime[1], "/"),"11111111");
		assertEquals(EventCreator.completeString(testDateandTime[2], ":"),"010101");
		assertEquals(EventCreator.completeString(testDateandTime[3], ":"),"111111");
	}

	//current date
	@Test
	public void testCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd/HHmmss");
		String result = EventCreator.currentDate();

		// compare date
		Date current = Calendar.getInstance().getTime();
		String date = df.format(current);

		// "T" character check
		if (result.contains("T")) {
			assertEquals(true, true);
		} else {
			assertEquals(false, true);
		}

		String r[] = result.split("T");
		String d[] = date.split("/");

		// date check
		assertEquals(r[0], d[0]);
		// time check (run time is under 0.05 seconds, so we can check second
		// too.
		assertEquals(r[1], d[1]);
	}
}