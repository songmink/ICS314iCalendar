import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.RandomAccessFile;
import java.util.Comparator;

public class InsertComment2 {
	public static class eventCompare implements Comparator<Event> {
		//If order is incorrect, subtract event2 from event1
		public int compare(Event event1, Event event2) {			
			return event1.time - event2.time;
		}
	} 
	//I am unfamiliar with private static classes
	private static class Event {
		public String fileName;
		public String yearMonthDay;
		public int time;
		public int line; // the line to removeor whateverit needs to be
		public String lastTwoLines;
		boolean okayToAdd = false;
		private Event(RandomAccessFile reader, String date) {
			int readChar = -1;
			String field = null;
			int noOfLines = 0;
			String dataToAdd;
			int fieldChecks = 0; //3 if the 3 fields are present
			try {
			    readChar = reader.read();
			    while(readChar != -1) {//readChar is -1 when end of file is reached, or when
			    	                   //given incorrect data
			    	System.out.print((char) readChar);
			    	readChar = reader.read();
			    	field = field + (char) readChar;
			    	System.out.print("|" + field + "|");
			    	if( (char) readChar == ':') {
			    		dataToAdd = reader.readLine();
			    		System.out.println();
			    		if(field.equals("DTSTART:") ) { //validate and store the time
			    			int indexOfT = dataToAdd.indexOf('T');
			    			System.out.println(dataToAdd);
			    			System.out.println(date);
			    			System.out.println(dataToAdd.substring(0,indexOfT) );
			    			if(date.equals(dataToAdd.substring(0,indexOfT) ) ) {
			    				System.out.println(dataToAdd);
			    		        dataToAdd = dataToAdd.substring(indexOfT+1,dataToAdd.length()-1 );
			    		        System.out.println(dataToAdd);
			    		        fieldChecks++; //supposed to be in if statement
			    		        okayToAdd = true;
			    				/*if(isValidTime(dataToAdd) ) {
			    					time = Integer.parseInt(dataToAdd);
			    		     		okayToAdd = true;
			    		     		fieldChecks++;
			    				}
			    				else {
			    					readChar = -1;//stop reading from file
				    				okayToAdd = false;
			    				}*/
			    			}
			    			else {
			    				readChar = -1;//stop reading from file
			    				okayToAdd = false;
			    			}
			    		}
			    		else if(field.equals("GEO:")) {//TODO
			    			fieldChecks++;
			    		}
			    		else if( (field + dataToAdd).equals("END:VEVENT")) {//TODO
			    			line = noOfLines;//If there are too many lines
			                                 //This will result in an error
			    			fieldChecks++;
			    		}
			    		else {
			    			
			    		}
			    		field = "";
			    		noOfLines++;
			    	}
			    }
			    if(fieldChecks != 3) {
			    	okayToAdd = false;
			    }
			    System.out.println(fieldChecks);
			    System.out.println(okayToAdd);
			}
			catch(Exception e) {
				okayToAdd = false; //indicates that it was a badFile
				System.out.println("ERROR");
			}
		}
	}
	
	public static void main(String[]args) {
		
		ReadEvent readEvent = new ReadEvent();
		String date;
		String fileNames[] = null;
		PriorityQueue<Event> queue;
		queue = new PriorityQueue<Event>(5, new eventCompare() );
		
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
	    String[] date2= date.split("/");
	    date = date2[0] + date2[1] + date2[2];
	    
	    
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
		
		
		try {
		    RandomAccessFile rfw = new RandomAccessFile(fileNames[0],"rw");
		    Event event = new Event(rfw,date);
		    rfw.close();
		    if(event.okayToAdd) {
		    	queue.add(event);
		    	System.out.println("It worked");
		    }
		}
		catch(Exception e) {
			
		}
		//event.fileName = fileNames[0];
		//event.time = getTime(rw);
		//event.final
		//queue.add(event);
		
		
		//System.out.println(fileNames[0]+fileNames[1]);
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
	
}
