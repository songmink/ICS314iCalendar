import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * ICS 314 Spring 2016 iCalendar project
 * 
 * 	Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim
 */

/***********************************************************************************/
/*                                                                                 */
/* Please write "// TODO name" comment in head of class or method which you want   */
/*                                                                                 */
/***********************************************************************************/

/**
 * iCalendar event creator
 *
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
	private static String cUid = "UID:";				/* Calendar uid */
	
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
	 * Run this
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*  static but can modify from user input */
		cScale += "GREGORIAN";
		dtZone += "HONOLULU";
		eMethod +="PUBLIC";
		//eStatus += "";
		//eGeo += "";
		
		/* 
		 * Read event start date time
		 * Evolve this!!
		 * 
		 */
		
		/* open scanner */
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter calendar name(Empty not allowed):");
		cName += stringReader(sc);
		System.out.print("Enter calendar description(Empty not allowed):");
		cDesc += stringReader(sc);
		System.out.print("Enter evnet summary for title(Empty not allowed):");
		eSummary += stringReader(sc);
		
		/* event start date */
		System.out.print("Enter event start date(yyyy/mm/dd):");
		String startDate = dateReader(sc);
		dtStart += startDate;
		
		/* add separator */
		dtStart += "T";
		/* event start time */
		System.out.print("Enter event start time(hh:mm)");
		String startTime = timeReader(sc);
		dtStart += startTime;
		/* add end Z */
		dtStart += "Z";
		
		/* Event end date */
		System.out.print("Endter event end date(yyyy/mm/dd):");
		String endDate = dateReader(sc);
		dtEnd += endDate;
		/* add separator */
		dtEnd += "T";
		System.out.println("Enter event end time(hh:mm)");
		String endTime = timeReader(sc);
		dtEnd += endTime;
		/* add end Z */
		dtEnd += "Z";
		
		/* Compare start datetime and end datetime */
		
		System.out.print("Enter event description(Empty not allowed):");
		eDesc = stringReader(sc);
		//System.out.print("Enter the Sequence(Empty not allowed):");
		//eSeq += intReader();
		
		System.out.print("Enter Event Location(Empty not allowed):");
		eLocation += stringReader(sc);
		
		System.out.print("Enter Event File Name:");
		fileName += stringReader(sc);
		
		sc.close();
		eCreated += currentDate();
		icsNewEvent(fileName);
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
			userIn = sc.nextLine();
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
			dateInput = sc.next();
			
			if(dateInput != null) {
				dateInput = dateInput.trim();
				
				/* Date validation check */
				if(isValidDate(dateInput)){
					isEmpty = false;
				} else {
					/* Reset input data */
					dateInput = null;
				}
			} else {
				System.out.print("Please enter the valid date");
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
			timeInput = sc.next();
			if(timeInput != null) {
				timeInput = timeInput.trim();
				String[] hm = timeInput.split(":");
				
				int h = Integer.parseInt(hm[0]);
				int m = Integer.parseInt(hm[1]);
				
				if(isValidTime(h,m)) {
					isEmpty = false;
				} else {
					timeInput = null;
				}
				
			} else {
				System.out.println("Plaese enter a valid time with the format.");
			}
		}		
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
			bw.write(cScale + "n");
			bw.write(eMethod + "\n");
			bw.write(dtZone + "\n");
			bw.write(cName + "\n");
			bw.write(cDesc + "\n");
			
			/* Static */
			bw.write(begin + "VEVENT" + "\n");
			
			// Each item can be disabled
			// Unused item should be disabled
			bw.write(cUid + "\n");
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
			if(d < 0 || 31 < d) {
				return false;
			}
		} else if(m != 2) {
			if(d < 0 || 32 < d) {
				return false;
			}
		}
		
		/* Intercalation */
		if(m == 2) {
			if( y%4 == 0 && y%100 != 0 && y%400 == 0) {
				if(d < 0 || 30 < d) {
					return false;
				}
				
			} else {
				if(d < 0 || 29 < d) {
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
	private static boolean isValidTime(int h, int m) {
		/* check the time validation */
		if(0 > h || 23 < h || 0 > m || 59 < m) {
			return false;
		}	
		return true;
	}
	
	/**
	 * Compare start and end datetime 
	 * 
	 * @return Boolean 
	 */
	private static boolean isVaildEvent(String sd, String st, String ed, String et){
		return true;
		
	}
	
	/**
	 * Current date
	 * 
	 * @return String yyyymmdd
	 */
	
	private static String currentDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		Date current = Calendar.getInstance().getTime();
		String date = df.format(current);
		return date;
	}
	
	
}