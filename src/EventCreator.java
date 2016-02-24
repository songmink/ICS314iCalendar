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
	
	/*
	 * Possible user input data 14 items
	 */
	private static String cScale = "CALSCALE:";			/* Calendar scale */
	private static String cName = "X-WR-CALNAME:";		/* Calendar name */
	private static String cDesc = "X-WR-CALDESC:";		/* Calendar description */
	private static String dtZone = "X-WR-TIMEZONE:";	/* User date time zone */	
	private static String dtStart = "DTSTART:";			/* Event start date time */
	private static String dtEnd = "DTEND:";				/* Event end date time */
	private static String cUid = "UID:";				/* Calendar uid */
	private static String eMethod = "METHOD:"; 			/* Event method */	
	private static String eDesc = "DESCRIPTION:";		/* Event description */
	private static String eSeq = "SEQUENCE:";			/* Event sequence */
	private static String eStatus ="STATUS:";			/* Event status */
	private static String eSummary = "SUMMARY:";		/* Event summary */
	private static String eLocation = "LOCATION:";		/* Event location */
	private static String eGeo = "GEO:";				/* Event geo */

	/**
	 * Run this
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*  static but can modify from user input */
		cScale += "GREGORIAN";
		
		
	}
	
	/**
	 * Read string user information from command line
	 * @return userIn string for user input string data / empty not allowed
	 */
	public static String stringReader() {
		Scanner sc = new Scanner(System.in);
		String userIn = "";
		Boolean isEmpty = true;

		while(isEmpty){
			
			if(userIn != "") {
				isEmpty = false;
			}
		}
		
		return userIn;
	}
	
	/**
	 * Read int user information from command line
	 * @return userIn integer for user input integer data / empty not allowed
	 */
	public static String intReader() {
		Scanner sc = new Scanner(System.in);
		String userIn = "";
		Boolean isEmpty = true;

		while(isEmpty){
			
			/* read only integer */
			while(!sc.hasNextInt()) {
				userIn = sc.next();
			}
			
			if(!userIn.isEmpty()) {
				isEmpty = false;
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
			
			// Each item can be disabled
			// Unused item should be disabled
			bw.write(begin + "VCALENDAR" + "\n");
			bw.write(prodId + "\n");
			bw.write(version + "\n");
			bw.write(cScale + "n");
			bw.write(eMethod + "\n");
			bw.write(dtZone + "\n");
			bw.write(cName + "\n");
			bw.write(cDesc + "\n");
			
			bw.write(begin + "VEVENT" + "\n");
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
			bw.write(eGeo + "\n");
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
	 * input dateValid
	 * @return true valid otherwise false
	 */
	private boolean dateValid(SimpleDateFormat d) {
		if(!true){
			return false;
		}
		
		return true;
	}
	
	//TODO first assign
	/** 
	 * start datetime must before end datetime
	 * @return true valid otherwise fale
	 */
	private boolean startEndDatetimeValid(SimpleDateFormat dt) {
		if(!true) {
			return false;
		}
		return true;
	}
	
}
