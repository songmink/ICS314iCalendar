import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 
 */

/**
 * @author Lucas
 *
 */
public class InsertCommentTest {

	@Test
	public void test() {
		InsertComment test = new InsertComment();
		System.out.println(test.calDistance(0, 90, 22, 91) );
	}
	
	@Test
	public void testSort() {
		InsertComment test = new InsertComment();
		String[][] events = new String[2][2];
		
		events[0][1] = "GEO:";
		events[0][0] = "DTSTART:11111111T1111";
		events[1][0] = "DTSTART:11111111T1110";
		events[1][1] = "GEO:";
		String[] fileNames = new String[2];
		
		fileNames[0] = "abc.ics";
		fileNames[1] = "abcde.ics";
		int[] array;
		array = test.getSorted(events, fileNames, 2);
		System.out.println(array[0]);
		assertEquals(array[0],1);
	}
	
	@Test
	public void calDistanceTest(){
		InsertComment test1 = new InsertComment(); 
	}
	public void insertCommentTest(){
		
	}

}