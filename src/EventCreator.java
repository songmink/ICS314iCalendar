import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * ICS 314 Spring 2016 iCalendar project 
 * 	Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim
 */

/**
 * iCalendar event creator
 *
 */
public class EventCreator {
	/* Start calendar properties */
	private static String begin = "BEGIN:";
	
	/* End calendar */
	private static String end = "END:";
	
	/* Program version */
	private static String version = "VERSION:";
	
	/* Product ID */
	private static String prodId = "PRODID:";
	
	/* Calendar scale */
	private static String cScale = "CALSCALE:";
	
	/* Calendar name */
	private static String cName = "X-WR-CALNAME:";
	
	/* Calendar description */
	private static String cDesc = "X-WR-CALDESC:";
	
	/* User date time zone */	
	private static String dtZone = "X-WR-TIMEZONE:";
	
	/* Event start date time */
	private static String dtStart = "DTSTART:";
	
	/* Event end date time */
	private static String dtEnd = "DTEND:";
	
	/* Calendar date time stamp */
	private static String dtStamp = "DTSTAMP:";
	
	/* Calendar uid */
	private static String cUid = "UID:";
	
	/* Event method */	
	private static String eMethod = "METHOD:";
	
	/* Event created date time */
	private static String eCreated = "CREATED:";
	
	/* Event last modify date time */ 
	private static String eLastMod = "LAST-MODIFIED:";
	
	/* Event description */
	private static String eDesc = "DESCRIPTION:";
	
	/* Event sequence */
	private static String eSeq = "SEQUENCE:";
	
	/* Event status */
	private static String eStatus ="STATUS:";
	
	/* Event summary */
	private static String eSummary = "SUMMARY:";
	
	/* Event location */
	private static String eLocation = "LOCATION:";
	
	/* Event geo */
	private static String eGeo = "GEO:";

	/**
	 * Run this
	 * @param args
	 */
	public static void main(String[] args) {
		prodInfoConst();
		userInfoReader();
		
	}
	
	/**
	 * Product Information Constructor
	 */
	public static void prodInfoConst() {
		 prodId += "-//University of Hawaii at Manoa//ICS314 iCalendar Team Cinco Spring.2016//EN";
		 version += "1.0";
	}
	
	//TODO first assign
	/**
	 * Read user information from command line
	 */
	public static void userInfoReader() {
		
		while(true){
			
		}
		
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
