import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * ICS 314 Spring 2016 iCalendar project
 * 
 * 	Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim
 *  Due date: 04/29/2016
 */

/**
 * iCalendar event creator
 */
public class EventCreator {

	/* Static variables for ICS category. */
	private static String begin = "BEGIN:";
	private static String end = "END:";
	private static String version = "VERSION:2.0"; /* Version 2.0 */
	private static String prodId = "PRODID:-//University of Hawaii at Manoa//ICS314 iCalendar Team Cinco Spring.2016//EN";
	private static String eCreated = "CREATED:"; /* Event created date time */
	private static String cScale = "CALSCALE:"; /* Calendar scale */
	private static String cName = "X-WR-CALNAME:"; /* Calendar name */
	private static String cDesc = "X-WR-CALDESC:"; /* Calendar description */
	private static String dtZone = "X-WR-TIMEZONE:"; /* User date time zone */
	private static String dtStart = "DTSTART:"; /* Event start date time */
	private static String dtEnd = "DTEND:"; /* Event end date time */
	private static String eMethod = "METHOD:"; /* Event method */
	private static String eDesc = "DESCRIPTION:"; /* Event description */
	private static String eSummary = "SUMMARY:"; /* Event summary */
	private static String eLocation = "LOCATION:"; /* Event location */
	private static String eGeo = "GEO:"; /* Event geo location */
	private static String eClass = "CLASS:"; /* Event Classification */
	private static String fileName = "NewEvent"; /* default file name */

	/* geo Field option */
	private static boolean GeoCheck = true;

	/**
	 * Main
	 */
	public static void main(String[] args) {
		System.out.print("Event creator start.\n");

		/* Default value for some static variables */
		cScale += "GREGORIAN";
		dtZone += "HONOLULU";
		eMethod += "PUBLIC";

		/* Open scanner for reading user input */
		Scanner sc = new Scanner(System.in);

		/* Input user calendar data */
		System.out.print("Welcome, this is the Event Creator program by Team Cinco");
		System.out.println("Some things you should know:");
		System.out.println("Timezone is set to HONOLULU by default");
		System.out.println("Description can only be one line by default");
		System.out.println("Filenames are by default the date and time of the file created");
		System.out.println("Geographic position is optional");

		// Loop checker
		boolean loop = true;

		/* Read calendar name using stringReader */
		while (loop) {
			System.out.print("1. Please, Enter calendar name (Empty not allowed):");
			try {
				cName += stringReader(sc);
				loop = false;
			} catch (NullPointerException e) {
				System.out.println("\n*** Warning: Calendar name should exist.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read calendar description using stringReader */
		while (loop) {
			System.out.print("2. Please, enter calendar description (Empty not allowed):");
			try {
				cDesc += stringReader(sc);
				loop = false;
			} catch (NullPointerException e) {
				System.out.println("\n*** Warning: Calendar description should exist.");
			}
		}

		// loop refresh for new loop
		loop = true;
		/* Read event summary using stringReader */
		while (loop) {
			System.out.print("3. Please, enter event summary for title Empty not allowed):");
			try {
				eSummary += stringReader(sc);
				loop = false;
			} catch (NullPointerException e) {
				System.out.println("\n*** Warning: event summary should exist.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read event start date */
		String startDate = null;
		String subString;
		while (loop) {
			System.out.print("Please, enter event start date (format:yyyy/mm/dd):");
			try {
				startDate = dateReader(sc);
                dtStart += completeString(startDate,"/");
				dtStart += "T"; // add date and time separator "T"
				loop = false;
			} catch (InputMismatchException e) {
				System.out.println("\n*** Warning: The date is invalid.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read event start time */
		String startTime = null;
		while (loop) {
			System.out.print("Please, enter event start time (24 hr format - HH:mm:ss)");
			try {
				startTime = timeReader(sc);
				
				dtStart += startTime.replace(":",
						""); /* Remove time separator */
				loop = false;
			} catch (InputMismatchException e) {
				startTime = "";
				System.err.println(e.getMessage() );
				System.out.print("\n*** Warning: The time is invalid.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read event end date */
		String endDate = null;
		while (loop) {
			System.out.print("Please, enter event end date (format: yyyy/mm/dd):");
			try {
				endDate = dateReader(sc);
				dtEnd += endDate.replace("/", ""); // Remove separators
				dtEnd += "T"; // Add date and time separator "T"
				loop = false;
			} catch (InputMismatchException e) {
				System.out.println("\n*** Warning: The date is invalid.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read event end time */
		String endTime = null;
		while (loop) {
			System.out.print("Please, enter event end time(24 hr format - hh:mm:ss");
			try {
				endTime = timeReader(sc);
				dtEnd += endTime.replace(":", ""); // Remove time separator
				loop = false;
			} catch (InputMismatchException e) {
				System.out.println("\n*** Warning: The time is invalid.");
			}
		}

		/*
		 * Event validation check
		 * 
		 * Check the validation checker function "isValidEvent" Send start and
		 * end event's date and time to as it is user input "isValidEvent" will
		 * return false for not valid or true for valid if not valid, then
		 * program will be terminated.
		 * 
		 */
		if (!isValidEvent(startDate, startTime, endDate, endTime)) {
			/* print out information */
			System.out.print(
					" ->>> There is a problem to make an event because your start and end date time is not synchronizing.\n");
			System.out.print("** Program halt! **");

			/* program terminate */
			System.exit(1);
		}

		// Refresh loop for new loop
		loop = true;
		/* Read Description */
		while (loop) {
			System.out.print("Please, enter event description(Empty not allowed):");
			try {
				eDesc += stringReader(sc);
				loop = false;
			} catch (NullPointerException e) {
				System.out.println("\n*** Warning: Empty is not allowed.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read class */
		while (loop) {
			System.out.print("Please, enter event class(p for private, c for confidencial, empty for public):");
			try {
				eClass += classReader(sc);
				loop = false;
			} catch (InputMismatchException e) {
				System.out.println("\n*** Warning: Input mismatch.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read location */
		while (loop) {
			System.out.print("Enter Event Location(Empty not allowed):");
			try {
				eLocation += stringReader(sc);
				loop = false;
			} catch (NullPointerException e) {
				System.out.println("\n*** Warning: Empty is not allowed.");
			}
		}

		GeoCheck = makeOptional(sc, "add the geographical position of your event");
		if (GeoCheck) {
			System.out.print("Geographical position of your event.\n");
			// Refresh loop for new loop
			loop = true;
			/* Read latitude */
			Float lat = null;
			while (loop) {
				System.out.print("Please, enter your event location logitude (Range: -90.0 <= x <= 90.0)");
				try {
					lat = latReader(sc);
					loop = false;
				} catch (InputMismatchException e) {
					System.out.println("\n*** Warning: Range is invalid.");
				}
			}

			// Refresh loop for new loop
			loop = true;
			/* Read longitude */
			Float lon = null;
			while (loop) {
				System.out.print("Please, enter your event location latitude (Range: -180.0 <= y <= 180.0)");
				try {
					lon = lonReader(sc);
					loop = false;
				} catch (InputMismatchException e) {
					System.out.println("\n*** Warning: Range is invalid");
				}
			}
			// Write the geo data on a category
			eGeo += Float.toString(lat) + ";" + Float.toString(lon);
		}
		/*
		 * Reader user input text for information using "stringReader" function
		 */

		try {
			System.out.print("Please, enter event file name:");
			fileName = stringReader(sc);
		} catch (NullPointerException e) {
			System.out.println(
					"\n*** Caution: You did not input your event file name! \nCurrent date will be your evnet file name.");
			fileName = currentDate();
		}

		/* close the scanner */
		sc.close();

		/* Time stamp */
		eCreated += currentDate();

		/* Write a file */
		StringBuilder b = new StringBuilder();
		b.append(fileName);
		b.append(".ics");
		try {
			File file = new File(b.toString());
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			/* Static DO NOT EARASE! */
			bw.write(begin + "VCALENDAR" + "\n");

			bw.write(prodId + "\n");
			bw.write(version + "\n");
			bw.write(cScale + "\n");
			bw.write(eMethod + "\n");
			bw.write(dtZone + "\n");
			bw.write(cName + "\n");
			bw.write(cDesc + "\n");

			/* Static */
			bw.write(begin + "VEVENT" + "\n");
			bw.write(dtStart + "\n");
			bw.write(dtEnd + "\n");
			bw.write(eDesc + "\n");
			bw.write(eLocation + "\n");
			bw.write(eSummary + "\n");
			bw.write(eCreated + "\n");

			/* geo option */
			if (GeoCheck) {
				bw.write(eGeo + "\n");
			}
			bw.write(eClass + "\n");

			/* Static DO NOT EARASE! */
			bw.write(end + "VEVENT" + "\n");
			bw.write(end + "VCALENDAR" + "\n");

			bw.close();
		} catch (IOException e) {
			System.out.println("\n*** Warning: File write error.");
		}

		/* Last comment and finish */
		System.out.print("*** The " + fileName + " event is created. ***\n Thank you.");
	}
    
	/**
	 * Given a delimiter it adjusts the given string to create a correct string for 
	 * both DTSTART and DTEND
	 * @param string the string to edit
	 * @param delimiter the character or String that separates information in this case it is either '/' or ':'
	 * @return
	 */
	private static String completeString(String string, String delimiter) {
		String stringToReturn;
		String subString;
		
		stringToReturn = appendZero(4,string.substring(0,string.indexOf(delimiter) ) );
		subString= string.substring(string.indexOf(delimiter)+1, string.length() );
		stringToReturn += appendZero(2,subString.substring(0,string.indexOf(delimiter) ) );
		subString= subString.substring(string.indexOf(delimiter)+1, subString.length() );
		stringToReturn += appendZero(2,subString.substring(0,string.indexOf(delimiter) ) );
		return stringToReturn;
	}

	/**
	 * Read string user information from command line
	 * 
	 * @return userIn string for user input string data
	 */
	public static String stringReader(Scanner sc) {
		String userIn = null;
		userIn = sc.nextLine();

		// If data is null, throw the null pointer error.
		if (userIn.isEmpty()) {
			throw new NullPointerException("Error: Null data is not allowed.");
		}
		return userIn;
	}

	/**
	 * Date reader
	 * 
	 * @param sc
	 * @return string or throw an error
	 */
	public static String dateReader(Scanner sc) {
		String dateInput = null;
		dateInput = sc.nextLine();
		dateInput = dateInput.trim();

		if (dateInput.isEmpty() || !isValidDate(dateInput)) {
			throw new InputMismatchException("Error: Invalid date.");
		}
		return dateInput;
	}

	/**
	 * Time reader
	 * 
	 * @return String time with hh:mm:ss format
	 */
	public static String timeReader(Scanner sc) {
		String timeInput = null;
		timeInput = sc.nextLine();

		if (timeInput.isEmpty() || !isValidTime(timeInput)) {
			throw new InputMismatchException("Error: Empty or invalid time.");
		}
		return timeInput;
	}

	/**
	 * Latitude reader
	 */
	public static float latReader(Scanner sc) {
		float lat = 21.2973964f;
		lat = sc.nextFloat();
		if (lat < -90.0 || 90.0 < lat) {
			throw new InputMismatchException("Error: Out of range.");
		}
		return lat;
	}

	/**
	 * Longitude reader
	 */
	public static float lonReader(Scanner sc) {
		float lot = -157.8162139f;
		lot = sc.nextFloat();
		if (lot < -180.0 || 180.0 < lot) {
			throw new InputMismatchException("Error: Out of range.");
		}
		return lot;
	}

	/**
	 * Geo data option
	 */
	public static boolean makeOptional(Scanner sc, String prompt) {
		String option;
		do {
			System.out.println("Would you like to " + prompt + "?");
			System.out.print("Enter 1 for Yes\n" + "Enter 2 for No\n");
			System.out.print("Response: ");
			option = sc.nextLine().trim();
		} while (!option.equals("1") && !option.equals("2"));

		if (option.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Date validation
	 * 
	 * @return true if valid otherwise false
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
		} else if (m == 2) {
			if (d < 0 || 28 < d) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Time validation
	 * 
	 * @return boolean true if valid otherwise false
	 */
	public static boolean isValidTime(String t) {
		t = t.trim();
		String[] hms = t.split(":");

		if (hms.length != 3) {
			return false;
		}

		int h = Integer.parseInt(hms[0], 10);
		int m = Integer.parseInt(hms[1], 10);
		int s = Integer.parseInt(hms[2], 10);
		/* check the time validation */
		if (0 > h || 23 < h || 0 > m || 59 < m || 0 > s || 59 < s) {
			return false;
		}
		return true;
	}

	/**
	 * Compare start and end date time
	 * 
	 * @return Boolean false if not valid otherwise true
	 */
	public static boolean isValidEvent(String sd, String st, String ed, String et) {

		/* date check */
		String[] symd = sd.split("/");
		String[] eymd = ed.split("/");

		int syear = Integer.parseInt(symd[0], 10);
		int eyear = Integer.parseInt(eymd[0], 10);
		/* start year is bigger than end year */
		if (syear > eyear) {
			return false;
		}

		int smon = Integer.parseInt(symd[1], 10);
		int emon = Integer.parseInt(eymd[1], 10);
		/* same year, start month is bigger than end month */
		if (syear == eyear && smon > emon) {
			return false;
		}

		int sday = Integer.parseInt(symd[2], 10);
		int eday = Integer.parseInt(eymd[2], 10);
		/* same year, same month, start day is bigger than end day */
		if (syear == eyear && smon == emon && sday > eday) {
			return false;
		}

		/* time check */
		String[] shm = st.split(":");
		String[] ehm = et.split(":");

		int shour = Integer.parseInt(shm[0], 10);
		int ehour = Integer.parseInt(ehm[0], 10);
		/* start time is bigger than end time */
		if (syear == eyear && smon == emon && sday == eday && shour > ehour) {
			return false;
		}

		int smin = Integer.parseInt(shm[1], 10);
		int emin = Integer.parseInt(ehm[1], 10);
		/* same time, start minutes is bigger than end minutes */
		if (syear == eyear && smon == emon && sday == eday && shour == ehour && smin > emin) {
			return false;
		}

		return true;
	}

	/**
	 * Current date
	 * 
	 * @return String yyyymmdd
	 */

	public static String currentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd/HHmmss");
		Date current = Calendar.getInstance().getTime();
		String date = df.format(current);
		date = date.replace("/", "T");
		return date;
	}

	 /**
	  * Appends zeros to beginning of String until it meets the specified length
	  * @param length the length that the string will become
	  * @param string the string to append 0's to
	  * @return
	  */
	 public static String appendZero(int length, String string) {
		 while(string.length() < length) {
			 string = "0" + string;
		 }
		 return string;
	 }
	
	/**
	 * Simple class input
	 * 
	 * @param sc
	 *            for user input
	 * @return PUBLIC, PRIVATE, or CONFIDENTIAL, default is PUBLIC
	 */
	public static String classReader(Scanner sc) {
		String userIn;

		userIn = sc.nextLine();
		if (userIn.isEmpty()) {
			return "PUBLIC";
		} else if (userIn.equals("p")) {
			return "PRIVATE";
		} else if (userIn.equals("c")) {
			return "CONFIDENTIAL";
		} else {
			throw new InputMismatchException("Error: Input mismatch.");
		}
	}
}
