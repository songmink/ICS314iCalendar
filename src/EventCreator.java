import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * ICS 314 Spring 2016 iCalendar project 
 * 	Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim
 */

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
	private static String eCreated = "CREATED:"; 	/* Event created date time */
	private static String eLastMod = "LAST-MODIFIED:";	/* Event last modify date time */ 
	private static String dtStamp = "DTSTAMP:";	/* Calendar date time stamp */
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
	private static String eSeq = "SEQUENCE:";			/* Event sequence */
	private static String eStatus ="STATUS:";			/* Event status */
	private static String eSummary = "SUMMARY:";		/* Event summary */
	private static String eLocation = "LOCATION:";		/* Event location */
	//private static String eGeo = "GEO:";				/* Event geo */
	
	private static String fileName = "NewEvent";			/* default file name */

	/**
	 * Run this
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*  static but can modify from user input */
		cScale += "GREGORIAN";
		dtZone += "HONOLULU";
		eMethod +="PUBLIC";
		eStatus += "";
		//eGeo += "";
		
		/* 
		 * Read event start date time
		 * Evolve this!!
		 * 
		 */
		System.out.print("Enter calendar name(Empty not allowed):");
		cName += stringReader();
		System.out.print("Enter calendar description(Empty not allowed):");
		cDesc += stringReader();
		System.out.print("Enter evnet summary for title(Empty not allowed):");
		eSummary = stringReader();
		
		Boolean isDate = false;
		while(!isDate) {
			System.out.print("Enter event start date (mm/dd/yyyy):");
			String temp = dateReader();
			if(isValidDate(temp)) {
				isDate = true;
				dtStart += temp;
			} else {
				System.out.print("Please check the date again\n");
			}
		}		
		/* add separator */
		dtStart += "T";
		System.out.println("Enter event start time(hh:mm)");
		dtStart += timeReader();
		/* add end Z */
		dtStart += "Z";
		
		/* 
		 * Read event end date time
		 */
		
		isDate = false; /* Initial isDate as false for restart */
		while(!isDate) {
			System.out.print("Enter event start date (mm/dd/yyyy):");
			String temp = dateReader();
			if(isValidDate(temp)) {
				isDate = true;
				dtEnd += temp;
			} else {
				System.out.print("Please check the date again\n");
			}
		}

		/* add separator */
		dtEnd += "T";
		System.out.println("Enter event end time(hh:mm)");
		dtEnd += timeReader();
		/* add end Z */
		dtEnd += "Z";
		
		System.out.print("Enter event description(Empty not allowed):");
		eDesc = stringReader();
		System.out.print("Enter the Sequence(Empty not allowed):");
		eSeq += intReader();
		
		System.out.print("Enter Event Location(Empty not allowed):");
		eLocation += stringReader();
		
		System.out.print("Enter Event File Name:");
		fileName += stringReader();
		icsNewEvent(fileName);
		
	}
	
	/**
	 * Read string user information from command line
	 * @return userIn string for user input string data / empty not allowed
	 */
	public static String stringReader() {
		Scanner sc = new Scanner(System.in);
		String userIn = null;
		Boolean isEmpty = true;

		while(isEmpty){
			
			if(userIn != null) {
				isEmpty = false;
			}
		}
		
		return userIn;
	}
	
	public static String dateReader() {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
		Boolean isEmpty = true;
		String dateInput = null;
		while(isEmpty) {
			dateInput = sc.next();
			if(dateInput != null) {
				dateInput = dateInput.trim();
				sc.close();
				isEmpty = false;
			}
			System.out.print("Please enter the valid date");
		}
		
		dateInput = dateInput.replace("/", "");
		return dateInput;
	}
	
	/*
	 * Time reader
	 * @return String time with hhmm format 
	 */
	public static String timeReader() {
		Scanner sc = new Scanner(System.in);
		Boolean isEmpty = true;
		String timeInput = null;
		while(isEmpty) {
			timeInput = sc.next();
			if(timeInput != null) {
				timeInput = timeInput.trim();
				String[] hhmm = timeInput.split(":");
				
				int hh = Integer.parseInt(hhmm[0]);
				int mm = Integer.parseInt(hhmm[1]);
				
				if(isValidTime(hh,mm)) {
					isEmpty = false;
				} else {
					timeInput = null;
				}
				
			} else {
				System.out.println("Plaese enter a valid time with the format.");
			}
		}
		sc.close();
		
		return timeInput;
	}
	
	/**
	 * Read integer user information from command line
	 * @return userIn integer for user input integer data / empty not allowed
	 */
	public static int intReader() {
		Scanner sc = new Scanner(System.in);
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
			bw.write(eSeq + "\n");
			bw.write(eStatus + "\n");
			bw.write(dtStamp + "\n");
			bw.write(eLastMod + "\n");
			//bw.write(eGeo + "\n");
			
			/* Static DO NOT EARASE! */
			bw.write(end + "VEVENT" + "\n");
			bw.write(end + "VCALENDAR" + "\n");
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
	
	//TODO first assign
	/**
	 * Date validation
	 * @return true if valid otherwise false
	 */
	private static boolean isValidDate(String d) {
		
		return true;
	}
	
	//TODO first assign
	/** 
	 * Time validation
	 * @return true if valid otherwise false
	 */
	private static boolean isValidTime(int hh, int mm) {
		/* check the time validation */
		if(0 > hh || 23 < hh || 0 > mm || 59 < mm) {
			return false;
		}
		
		return true;
	}
	
}
