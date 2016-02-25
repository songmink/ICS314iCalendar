import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * ICS 314 Spring 2016 iCalendar project
 * 
 * 	Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim
 *  Due date: 02/25/2016
 */

/***********************************************************************************/
/*                                                                                 */
/* Please write "// TODO name" comment in head of class or method which you want   */
/*                                                                                 */
/***********************************************************************************/

/**
 * iCalendar event creator
 */
public class EventCreator {
	/* Start or end calendar properties */
	private static String begin = "BEGIN:";
	private static String end = "END:";
	
	/* Program version and product id */
	private static String version = "VERSION:1.0";
	private static String prodId = "PRODID:-//University of Hawaii at Manoa//ICS314 iCalendar Team Cinco Spring.2016//EN";
	
	/* Auto input */
	private static String eCreated = "CREATED:"; 		/* Event created date time */
	//private static String eLastMod = "LAST-MODIFIED:";	/* Event last modify date time */ 
	//private static String dtStamp = "DTSTAMP:";			/* Calendar date time stamp */
	//private static String cUid = "UID:";				/* Calendar uid */
	
	/*
	 * Possible user input data 13 items
	 */
	private static String cScale = "CALSCALE:";			/* Calendar scale */
	private static String cName = "X-WR-CALNAME:";		/* Calendar name */
	private static String cDesc = "X-WR-CALDESC:";		/* Calendar description */
	private static String dtZone = "X-WR-TIMEZONE:";	/* User date time zone */	
	private static String dtStart = "DTSTART:";			/* Event start date time */
	private static String dtEnd = "DTEND:";				/* Event end date time */
	private static String eMethod = "METHOD:"; 			/* Event method */	
	private static String eDesc = "DESCRIPTION:";		/* Event description */
	//private static String eSeq = "SEQUENCE:";			/* Event sequence */
	//private static String eStatus ="STATUS:";			/* Event status */
	private static String eSummary = "SUMMARY:";		/* Event summary */
	private static String eLocation = "LOCATION:";		/* Event location */
	//private static String eGeo = "GEO:";				/* Event geo */
	
	private static String fileName = "NewEvent";		/* default file name */

	/**
	 * Main 
	 * @param args ignore
	 */
	public static void main(String[] args) {
		System.out.print("Event creator start.\n");
		
		/*  static but can modify from user input */
		cScale += "GREGORIAN";
		dtZone += "HONOLULU";
		eMethod +="PUBLIC";
		//eStatus += "";
		//eGeo += "";
		
		/* open scanner */
		Scanner sc = new Scanner(System.in);
		
		/* Input user calendar data*/
		System.out.print("Enter calendar name(Empty not allowed):");
		cName += stringReader(sc);
		System.out.print("Enter calendar description(Empty not allowed):");
		cDesc += stringReader(sc);
		System.out.print("Enter evnet summary for title(Empty not allowed):");
		eSummary += stringReader(sc);
		
		/* event start date */
		System.out.print("Enter start ");

		// TODO use this variable "startDate" for comparing start and end date 
		String startDate = dateReader(sc);
		dtStart += startDate;
		
		/* add separator */
		dtStart += "T";
		/* event start time */
		System.out.print("Enter satar");
		// TODO use this variable "startTime" for comparing start and end date
		String startTime = timeReader(sc);
		dtStart += startTime;
		/* add end Z */
		dtStart += "Z";
		
		/* Event end date */
		System.out.print("Enter end ");
		// TODO use this variable "endDate" for comparing start and end date
		String endDate = dateReader(sc);
		dtEnd += endDate;
		/* add separator */
		dtEnd += "T";
		System.out.print("Enter end ");
		// TODO use this variable "endDate" for comparing start and end date
		String endTime = timeReader(sc);
		dtEnd += endTime;
		/* add end Z */
		dtEnd += "Z";
		
		// TODO who can evolve this?
		/* Compare start and end date time */
		if(!isValidEvent(startDate, startTime, endDate, endTime)) {		
			
			// TODO program termination or ?
			// 		print("There is a problem to make a event because your start and end date time is not synchronizing.");
			// 		and terminate or return first?(little hard)
			
			
			
			
			
		
			
			
			
			
		}
		
		System.out.print("Enter event description(Empty not allowed):");
		eDesc += stringReader(sc);
		//System.out.print("Enter the Sequence(Empty not allowed):");
		//eSeq += intReader();
		
		System.out.print("Enter Event Location(Empty not allowed):");
		eLocation += stringReader(sc);
		
		System.out.print("Enter Event File Name:");
		fileName = stringReader(sc);
		
		sc.close();
		eCreated += currentDate();
		icsNewEvent(fileName);
		System.out.print("The " + fileName + " event is created.\n Program End.");
	}
	
	/**
	 * Read string user information from command line
	 * 
	 * @return userIn string for user input string data / empty not allowed
	 */
	public static String stringReader(Scanner sc) {
		String userIn = null;
		Boolean isEmpty = true;
		
		while(isEmpty){
			userIn = sc.next();
			if(userIn != null) {
				isEmpty = false;
			}
		}
		return userIn;
	}
	
	/**
	 *  Date reader
	 *  
	 *  @return String date
	 */
	public static String dateReader(Scanner sc) {
		Boolean isEmpty = true;
		String dateInput = null;
		while(isEmpty) {
			System.out.print("event date(yyyy/mm/dd):");
			dateInput = sc.next();
			
			if(dateInput != null) {
				dateInput = dateInput.trim();
				
				/* Date validation check */
				if(isValidDate(dateInput)){
					isEmpty = false;
				} else {
					/* Reset input data */
					dateInput = null;
					System.out.print("- The date you input is not valid! -\n- Please enter a valid ");
				}
			} else {
				System.out.print("- The date you input is not valid! -\n- Please enter the valid ");
			}
		}
		dateInput = dateInput.replace("/", "");
		return dateInput;
	}
	
	/**
	 * Time reader
	 * @return String time with hhmm format 
	 */
	public static String timeReader(Scanner sc) {
		Boolean isEmpty = true;
		String timeInput = null;
		while(isEmpty) {
			System.out.print("event time(hh:mm):");
			timeInput = sc.next();
			if(timeInput != null) {				
				if(isValidTime(timeInput)) {
					isEmpty = false;
				} else {
					timeInput = null;
					System.out.print("- Tha time you input is not valid! -\n- Please enter a valid ");
				}
				
			} else {
				System.out.print("- The time you input is not valid! -\n- Plaese enter a valid");
			}
		}		
		timeInput = timeInput.replace(":", "");
		return timeInput;
	}
	
	/**
	 * Read integer user information from command line
	 * @return userIn integer for user input integer data / empty not allowed
	 */
	public static int intReader(Scanner sc) {
		int userIn = 1;
		Boolean isEmpty = true;

		while(isEmpty){
			/* read only integer */
			while(!sc.hasNextInt()) {
				userIn = sc.nextInt();
			}
		}		
		return userIn;
	}	
	
	
	//TODO: Unused item should be disabled
	/** 
	 * Write a new event  
	 */
	public static void icsNewEvent(String fileName) {
		StringBuilder b = new StringBuilder();
		b.append(fileName);
		b.append(".ics");
		try {
			File file = new File(b.toString());
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			/* Static DO NOT EARASE! */
			bw.write(begin + "VCALENDAR" + "\n");
			
			// Each item can be disabled
			// Unused item should be disabled
			bw.write(prodId + "\n");
			bw.write(version + "\n");
			bw.write(cScale + "\n");
			bw.write(eMethod + "\n");
			bw.write(dtZone + "\n");
			bw.write(cName + "\n");
			bw.write(cDesc + "\n");
			
			/* Static */
			bw.write(begin + "VEVENT" + "\n");
			
			// Each item can be disabled
			// Unused item should be disabled
			//bw.write(cUid + "\n");
			bw.write(dtStart + "\n");
			bw.write(dtEnd + "\n");
			bw.write(eDesc + "\n");
			bw.write(eLocation + "\n");
			bw.write(eSummary + "\n");
			bw.write(eCreated + "\n");
			//bw.write(eSeq + "\n");
			//bw.write(eStatus + "\n");
			//bw.write(dtStamp + "\n");
			//bw.write(eLastMod + "\n");
			//bw.write(eGeo + "\n");
			
			/* Static DO NOT EARASE! */
			bw.write(end + "VEVENT" + "\n");
			bw.write(end + "VCALENDAR" + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// TODO Later
	/**
	 * Modify an event
	 */
	public static void icsModEvent() {
		
	}
	
	//TODO Later
	/**
	 * Add an event
	 */
	public static void icsAddEvent() {
		
	}
	
	/**
	 * Date validation
	 * @return true if valid otherwise false
	 */
	private static boolean isValidDate(String date) {
		date = date.trim();
		
		String[] ymd  = date.split("/");
		if(ymd.length != 3 ) {
			return false;
		}
		int y = Integer.parseInt(ymd[0]);
		int m = Integer.parseInt(ymd[1]);
		int d = Integer.parseInt(ymd[2]);
		
		/* year */
		if(y < 0) {
			return false;
		}
		
		/* month */
		if(0 > m || 12 < m) {
			return false;
		}
		
		/* day 31  or 30*/
		if(m == 4 || m == 6 || m == 9 || m == 11) {
			if(d < 0 || 30 < d) {
				return false;
			}
		} else if(m != 2) {
			if(d < 0 || 31 < d) {
				return false;
			}
		}
		
		/* Intercalation */
		if(m == 2) {
			if( y%4 == 0 && y%100 != 0 && y%400 == 0) {
				if(d < 0 || 29 < d) {
					return false;
				}
				
			} else {
				if(d < 0 || 28 < d) {
					return false;
				}
			}
		}
		return true;
	}
	
	/** 
	 * Time validation
	 * @return boolean true if valid otherwise false
	 */
	private static boolean isValidTime(String t) {
		t = t.trim();
		String[] hm = t.split(":");
		
		if(hm.length != 2) {
			return false;
		}
		
		int h = Integer.parseInt(hm[0]);
		int m = Integer.parseInt(hm[1]);
		/* check the time validation */
		if(0 > h || 23 < h || 0 > m || 59 < m) {
			return false;
		}
		return true;
	}
	
	//TODO Please implement this validation      
	/**
	 * Compare start and end date time 
	 * 
	 * @return Boolean false if not valid otherwise true
	 */
	private static boolean isValidEvent(String sd, String st, String ed, String et){
		
		// TODO if not valid return false
		// Hint: Easiest way - append sd and st and append ed and et
		//       change string sdst and edet to int sdst and edet
		//       edet must be bigger than sdst
		//		 but string mm and dd should be mm dd style like 02 not 2 
		
		
		
		
		
		
		
		
		return true;		
	}
	
	/**
	 * Current date
	 * @return String yyyymmdd
	 */
	
	private static String currentDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		Date current = Calendar.getInstance().getTime();
		String date = df.format(current);
		return date;
	}
	
	
}
