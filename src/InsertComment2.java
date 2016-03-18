import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.RandomAccessFile;

public class InsertComment2 {
	
	//I am unfamiliar with private static classes
	private static class Event {
		public String fileName;
		public String yearMonthDay;
		public int time;
		public int line; // the line to removeor whateverit needs to be
		private Event() {
			
		}
	}
	
	public static void main(String[]args) {
		ReadEvent readEvent = new ReadEvent();
		String date;
		String fileNames[] = null;
		PriorityQueue queue = new PriorityQueue();
		
		System.out.println("-----------------------------------------------------");
		System.out.println("This program will calculate the great circle distance"
						  +"\nfor each event file you enter based on the start"
						  +"\ntime of your given files. The entered event files"
						  +"\nmust begin on the first day.");
		System.out.println("-----------------------------------------------------");
		System.out.println("Please enter the following information to begin");
        		
		 
		Scanner scan = new Scanner(System.in);
	    do {
	    	System.out.print("Please enter a date (yyyy/mm/dd):");
	    	date = scan.nextLine();
	    }
	    while(!isValidDate(date));    
	    System.out.println("-----------------------------------------------------");
	    
		System.out.println("Now to add files");
		System.out.println("Files not containing the proper date will be ignored");	
		System.out.println("-----------------------------------------------------");
		//call Nicks Method
		fileNames = readEvent.GetFileNames(scan);
		System.out.println("-----------------------------------------------------");
		
		/* Double check if there are repeat names and remove them*/
		/* Double check if files contain valid dates and remove the ones
		 * that do not fit.*/
		
		//queue.add();
		
		
		//System.out.println(fileNames[0]+fileNames[1]);
	}
	
	
	
	/* We already know that the code below works*/
	/**
	 * @author Song Min Kim
	 * @param date
	 * @return
	 */
	public static boolean isValidDate(String date) {
		
		date = date.trim();
	
		String[] ymd = date.split("/");
		if (ymd.length != 3) {
			return false;
		}
		int y = Integer.parseInt(ymd[0], 10);
		int m = Integer.parseInt(ymd[1], 10);
		int d = Integer.parseInt(ymd[2], 10);
	
		/* year and */
		if (y < 0) {
			return false;
		}
	
		/* month */
		if (0 >= m || 12 < m) {
			return false;
		}
	
		/* day 31 or 30 */
		if (m == 4 || m == 6 || m == 9 || m == 11) {
			if (d < 0 || 30 < d) {
				return false;
			}
		} else if (m != 2) {
			if (d < 0 || 31 < d) {
				return false;
			}
		}
	
		/* LeapYear */
		if (m == 2 && y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
			if (d < 0 || 29 < d) {
				return false;
			}
		} else if (m == 2){
			if (d < 0 || 28 < d) {
				return false;
			}
		}
	
		return true;
	}
	
	/**
	 * Time validation
	 * @author Song Min Kim
	 * @return boolean true if valid otherwise false
	 */
	public static boolean isValidTime(String t) {
		t = t.trim();
		String[] hm = t.split(":");
	
		if (hm.length != 2) {
			return false;
		}
	
		int h = Integer.parseInt(hm[0], 10);
		int m = Integer.parseInt(hm[1], 10);
		/* check the time validation */
		if (0 > h || 23 < h || 0 > m || 59 < m) {
			return false;
		}
		return true;
	}
}