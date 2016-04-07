import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertThat;

/**
 * 
 */

/**
 * @author Lucas
 *
 */
public class InsertCommentTest {

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
		//System.out.println(array[0]);
		assertEquals(array[0],1);
	}
	
	@Test
	public void calDistanceTest(){
		InsertComment test = new InsertComment();
		double d = 0;
		double[] lat1 = {-45, 0, 45};
		double[] lon1 = {-90, 0, 90};
		double[] lat2 = {0, 45, 90};
		double[] lon2 = {0,45, -45};
		
		int[] answer = {10010, 6672, 5004};
		for(int i = 0; i < lat1.length; i++) {
			Boolean max = false;
			Boolean min = false;
			double result = test.calDistance(lat1[i], lon1[i], lat2[i], lon2[i]);
			int r = (int) result;
			//System.out.println("Distance:" + r);
			if ( r < (answer[i] + 5)){
				max = true;
			}
			if ( r > (answer[i] - 5)) {
				min = true;
			}
			assertEquals(max, min);
		}
		
	}
	public void insertCommentTest(){
		
	}

}