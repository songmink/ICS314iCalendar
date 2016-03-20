import static org.junit.Assert.*;
import java.io.*;
import java.util.Scanner;
import org.junit.Test;

public class EventCreatorTest {
    
	@Test
	public void testStringReader() {
		EventCreator test = new EventCreator();
		Scanner sc = new Scanner("a");
		@SuppressWarnings("static-access")
		String result = test.stringReader(sc);
		assertEquals("a", result);
	}
 
	@SuppressWarnings("static-access")
	@Test //requires a test file in the ICS Calendar directory
	public void testClassReader() throws FileNotFoundException {
		System.out.println("------------------------");
		System.out.println("    testClassReader     ");
		System.out.println("------------------------");
		try {
			EventCreator eTest = new EventCreator();
			File a = new File("classReaderScanDest.txt");
			Scanner scan = new Scanner(a);
			
			System.out.println("------------------------");
			System.out.println("  Test Case 1: Input 1  ");
			System.out.println("------------------------");
			String testString = eTest.classReader(scan);
			if(!testString.equals("PUBLIC") ) {
				fail("Given 1, it does not return PUBLIC");
			}
					
			System.out.println("------------------------");
			System.out.println("  Test Case 2: Input 2  ");
			System.out.println("------------------------");
			testString = eTest.classReader(scan);
			if(!testString.equals("PRIVATE") ) {
				fail("Given 2, it does not return PRIVATE");
			}
			
			System.out.println("------------------------");
			System.out.println("  Test Case 3: Input 3  ");
			System.out.println("------------------------");
			testString = eTest.classReader(scan);
			if(!testString.equals("CONFIDENTIAL") ) {
				fail("Given 3, it does not return CONFIDENTIAL");
			}
					
			System.out.println("------------------------");
			System.out.println("Test Case 4: Input adawda");
			System.out.println("------------------------");
			//enters an arbitrary String, and then 1,
			//to check if method gets valid input
			testString = eTest.classReader(scan);
			scan.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage() );
		}
	}
	
	
	@Test
	public void testDateReader() {
		EventCreator test = new EventCreator();
		// Leaf year
		Scanner sc = new Scanner("2016/02/29");
		@SuppressWarnings("static-access")
		String result = test.dateReader(sc);
		assertEquals("2016/02/29", result);
	}

	@Test
	public void testTimeReader() {
		EventCreator test = new EventCreator();
		Scanner sc = new Scanner("11:22");
		@SuppressWarnings("static-access")
		String result = test.timeReader(sc);
		assertEquals("11:22", result);
	}


	@SuppressWarnings("static-access")
	@Test
	public void testIsVaildDate(){
		EventCreator test = new EventCreator();
		// Correct dates
		String[] cdate = {"2016/01/31", "2016/02/29", "2016/03/31", "2016/05/31", "2016/07/31",
				"2016/08/31", "2016/10/31", "2016/12/31", "2400/02/29"};
		// Wrong date
		String[] wdate = {"2016/04/31", "2016/06/31", "2016/09/31", "2016/11/31", "2015/02/29", 
				"2100/02/29"};

		for(int i = 0; i < cdate.length; i++){
			assertTrue(test.isValidDate(cdate[i]));
		}
		for(int i = 0; i < wdate.length; i++){
			assertFalse(test.isValidDate(wdate[i]));
		}
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testIsVaildTime(){
		EventCreator test = new EventCreator();
		// Correct time
		String[] ctime = {"00:00", "01:59", "12:00"};
		// Wrong time
		String[] wtime = {"24:00", "25:01", "-12:00"};
		for(int i = 0; i < ctime.length; i++){
			assertTrue(test.isValidTime(ctime[i]));
		}
		for(int i = 0; i < wtime.length; i++){
			assertFalse(test.isValidTime(wtime[i]));
		}	
	}

	@SuppressWarnings("static-access")
	@Test
	public void testIsValidEvent(){
		EventCreator test = new EventCreator();
		
		// Correct event
		String[][] cevent = {{"2016/01/01", "00:00", "2016/01/01", "00:01"},
				{"2016/01/01", "01:00", "2016/01/02", "00:00"}};
		String[][] wevent = {{"2016/01/01", "01:00", "2016/01/01", "00:00"},
				{"2016/01/02", "12:00", "2016/01/01", "15:00"}};
		
		for(int i = 0; i < cevent.length; i++){
			assertTrue(test.isValidEvent(cevent[i][0], cevent[i][1], cevent[i][2], cevent[i][3]));		
		}
		
		for(int i = 0; i < wevent.length; i++){
			assertFalse(test.isValidEvent(wevent[i][0], wevent[i][1], wevent[i][2], wevent[i][3]));	
		}
	}
	
	@Test
	public void testClassReader2(){
		EventCreator test = new EventCreator();
		Scanner sc = new Scanner("\n");
		String result = test.classReader2(sc);
		assertEquals("PUBLIC", result);

		sc = new Scanner("p");
		result = test.classReader2(sc);
		assertEquals("PRIVATE", result);
		
		sc = new Scanner("c");
		result = test.classReader2(sc);
		assertEquals("CONFIDENTIAL", result);
	}

}
