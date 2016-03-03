import static org.junit.Assert.*;
import java.io.*;
import java.util.Scanner;

import org.junit.Test;


public class EventCreatorTest {

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}
    
	@Test
	public void testStringReader() {
		
		fail("Not yet implemented");
	}
   
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
		fail("Not yet implemented");
	}

	@Test
	public void testTimeReader() {
		fail("Not yet implemented");
	}

	@Test
	public void testIntReader() {
		fail("Not yet implemented");
	}

	@Test
	public void testIcsNewEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIcsModEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIcsAddEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
