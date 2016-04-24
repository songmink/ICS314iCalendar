import static org.junit.Assert.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class EventCreatorTest {

	// TODO E01. string reader test
	@Test
	public void testStringReader() {
		String[] iData = { "a", "ab", "abc" };
		for (int i = 0; i < iData.length; i++) {
			Scanner sc = new Scanner(iData[i]);
			String result = inputReader.string(sc);
			assertEquals(iData[i], result);
		}
		try {
			Scanner sc = new Scanner("");
			inputReader.string(sc);
		} catch (Exception e) {
			// Nothing means there is an exception
		}
	}

	// TODO E02. date reader test
	@Test
	public void testDateReader() {
		// Leap year
		Scanner sc = new Scanner("2016/02/29");
		String result = inputReader.date(sc);
		assertEquals("2016/02/29", result);
	}

	// TODO E03. time reader test
	@Test
	public void testTimeReader() {
		// Correct time
		String[] ctime = { "00:00:00", "01:59:00", "12:00:00" };
		// Wrong time
		String[] wtime = { "24:00:00", "25:01:00", "-12:00:00" };
		for (int i = 0; i < ctime.length; i++) {
			assertTrue(isValid.calTime(ctime[i]));
		}
		for (int i = 0; i < wtime.length; i++) {
			assertFalse(isValid.calTime(wtime[i]));
		}
	}

	// TODO E04. date validation test
	@Test
	public void testIsVaildDate() {
		// Correct dates
		String[] cdate = { "2016/01/31", "2016/02/29", "2016/03/31", "2016/05/31", "2016/07/31", "2016/08/31",
				"2016/10/31", "2016/12/31", "2400/02/29" };
		// Wrong date
		String[] wdate = { "2016/04/31", "2016/06/31", "2016/09/31", "2016/11/31", "2015/02/29", "2100/02/29" };

		for (int i = 0; i < cdate.length; i++) {
			assertTrue(isValid.calDate(cdate[i]));
		}
		for (int i = 0; i < wdate.length; i++) {
			assertFalse(isValid.calDate(wdate[i]));
		}
	}

	// TODO E05. time validation test
	@Test
	public void testIsVaildTime() {
		// Correct time
		String[] ctime = { "00:00:00", "01:59:00", "12:00:00" };
		// Wrong time
		String[] wtime = { "24:00:00", "25:01:00", "-12:00:00" };
		for (int i = 0; i < ctime.length; i++) {
			assertTrue(isValid.calTime(ctime[i]));
		}
		for (int i = 0; i < wtime.length; i++) {
			assertFalse(isValid.calTime(wtime[i]));
		}
	}

	// TODO E06. event validation test
	@Test
	public void testIsValidEvent() {
		// Correct event
		String[][] cevent = { { "2016/01/01", "00:00:00", "2016/01/01", "00:01:00" },
				{ "2016/01/01", "01:00:00", "2016/01/02", "00:00:00" } };
		String[][] wevent = { { "2016/01/01", "01:00:00", "2016/01/01", "00:00:00" },
				{ "2016/01/02", "12:00:00", "2016/01/01", "15:00:00" } };

		for (int i = 0; i < cevent.length; i++) {
			assertTrue(isValid.calEvent(cevent[i][0], cevent[i][1], cevent[i][2], cevent[i][3]));
		}

		for (int i = 0; i < wevent.length; i++) {
			assertFalse(isValid.calEvent(wevent[i][0], wevent[i][1], wevent[i][2], wevent[i][3]));
		}
	}

	// TODO E07. class reader test
	@Test
	public void testClassReader() {
		Scanner sc = new Scanner("\n");
		String result = inputReader.calClass(sc);
		assertEquals("PUBLIC", result);

		sc = new Scanner("p");
		result = inputReader.calClass(sc);
		assertEquals("PRIVATE", result);

		sc = new Scanner("c");
		result = inputReader.calClass(sc);
		assertEquals("CONFIDENTIAL", result);
		try {
			sc = new Scanner("fafaf");
		} catch (Exception e) {
			// Nothing means there is an exception
		}
	}

	// TODO E08. latitude reader test
	@Test
	public void testLatReader() {
		String testLat[] = { "-91.0f", "-90.0f", "-45.5f", "0.0f", "30.0f", "60.123456f", " 90.0f", "90.1f" };
		for (int i = 0; i < testLat.length; i++) {
			try {
				Scanner sc = new Scanner(testLat[i]);
				float result = inputReader.lat(sc);
				assertEquals(testLat[i], result);
			} catch (Exception e) {
				// Nothing means there is an exception
			}
		}
	}

	// TODO E09. longitude reader test
	@Test
	public void testLonReader() {
		String testLon[] = { "-191.0f", "-180.0f", "-78.6f", "0.0f", "66.0f", "60.123456f", " 180.0f", "180.1f" };
		for (int i = 0; i < testLon.length; i++) {
			try {
				Scanner sc = new Scanner(testLon[i]);
				float result = inputReader.lon(sc);
				assertEquals(testLon[i], result);
			} catch (Exception e) {
				// Nothing means there is an exception
			}
		}
	}

	// TODO E10. geo data input option
	@Test
	public void testMakeOptional() {
		Scanner sc = new Scanner("1");
		boolean result = inputReader.makeOptional(sc, "add the geographical position of your event");
		assertEquals(true, result);

		sc = new Scanner("2");
		result = inputReader.makeOptional(sc, "add the geographical position of your event");
		assertEquals(false, result);
	}

	// TODO E11. current date
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
