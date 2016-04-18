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
 *  @author Songmin Kim
 */

/**
 * iCalendar event creator
 */
public class EventCreator {
	public EventCreator() {

	}

	/* Start or end calendar properties */
	private static String begin = "BEGIN:";
	private static String end = "END:";

	/* Program version and product id */
	private static String version = "VERSION:2.0";
	private static String prodId = "PRODID:-//University of Hawaii at Manoa//ICS314 iCalendar Team Cinco Spring.2016//EN";

	/* Auto input */
	private static String eCreated = "CREATED:"; /* Event created date time */
	// private static String eLastMod = "LAST-MODIFIED:"; /* Event last modify date time */
	// private static String dtStamp = "DTSTAMP:"; /* Calendar date time stamp */
	// private static String cUid = "UID:"; /* Calendar uid */

	/*
	 * Possible user input data 13 items
	 */
	private static String cScale = "CALSCALE:"; /* Calendar scale */
	private static String cName = "X-WR-CALNAME:"; /* Calendar name */
	private static String cDesc = "X-WR-CALDESC:"; /* Calendar description */
	private static String dtZone = "X-WR-TIMEZONE:"; /* User date time zone */
	private static String dtStart = "DTSTART:"; /* Event start date time */
	private static String dtEnd = "DTEND:"; /* Event end date time */
	private static String eMethod = "METHOD:"; /* Event method */
	private static String eDesc = "DESCRIPTION:"; /* Event description */
	// private static String eSeq = "SEQUENCE:"; /* Event sequence */
	// private static String eStatus ="STATUS:"; /* Event status */
	private static String eSummary = "SUMMARY:"; /* Event summary */
	private static String eLocation = "LOCATION:"; /* Event location */
	private static String eGeo = "GEO:"; /* Event geo location */
	private static String eClass = "CLASS:"; /* Event Classification */
	// private static String eComment = "COMMENT:"; /* Event comment(distance) */
	private static boolean GeoCheck = true; /* Check if geo field to see if user sets*/


	private static String fileName = "NewEvent"; /* default file name */

	/**
	 * Main
	 * 
	 * @param args
	 *            ignore
	 */
	public static void main(String[] args) {
		System.out.print("Event creator start.\n");

		/*
		 * static but can modify from user input
		 */
		cScale += "GREGORIAN";
		dtZone += "HONOLULU";
		eMethod += "PUBLIC";
		// eStatus += "";
		// eGeo += "";

		/* open scanner */
		Scanner sc = new Scanner(System.in);

		/*
		 * Input user calendar data
		 */
		System.out.print("Welcome, this is the Event Creator program by Team Cinco");
		System.out.println("Some things you should know:");
		System.out.println("Timezone is set to HONOLULU by default");
		System.out.println("Description can only be one line by default");
		System.out.println("Filenames are by default the date and time of the file created");
		System.out.println("Geographic position is optional");
		
		System.out.print("Enter calendar name(Empty not allowed):");
		/* Read user data from command line using "stringReader" function */
		cName += stringReader(sc);
		System.out.print("Enter calendar description(Empty not allowed):");
		/* Read user data from command line using "stringReader" function */
		cDesc += stringReader(sc);
		System.out.print("Enter event summary for title(Empty not allowed):");
		/* Read user data from command line using "stringReader" function */
		eSummary += stringReader(sc);

		/*
		 * Event start date and time reader
		 */
		System.out.print("Enter start ");
		/* Read user data from command line using "dateReader" function */
		String startDate = dateReader(sc);
		/* Remove separators */
		dtStart += startDate.replace("/", "");
		/* add date and time separator "T" */
		dtStart += "T";
		/* event start time */
		System.out.print("Enter start");
		/* Read user data from command line using "timeReader" function */
		String startTime = timeReader(sc);
		/* Remove time separator */
		dtStart += startTime.replace(":", "");
		/* add UTC mark "Z" */
		//dtStart += "Z";

		/*
		 * Event end date and time reader
		 */
		System.out.print("Enter end ");
		/* Read user data from command line using "dateReader" function */
		String endDate = dateReader(sc);
		/* Remove separators */
		dtEnd += endDate.replace("/", "");
		/* add date and time separator "T" */
		dtEnd += "T";
		System.out.print("Enter end ");
		/* Read user data from command line using "timeReader" function */
		String endTime = timeReader(sc);
		/* Remove time separator */
		dtEnd += endTime.replace(":", "");
		/* add UTC mark "Z" */
		//dtEnd += "Z";
		
		

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

		/* Read user input text for information using "stringReader" function */
		System.out.print("Enter event description(Empty not allowed):");
		eDesc += stringReader(sc);

		/* Read user input integer for sequence using "intReader" function */
		// System.out.print("Enter the Sequence(Empty not allowed):");
		/*
		 * Read user input using "intReader" function, intReader reads only
		 * integer value
		 */
		// eSeq += intReader();

		// TODO select one of them
		/*
		 * print statements are contained in class reader
		 */
		eClass += classReader(sc);

		/*
		 * Second version
		 */
		// System.out.print("Enter Class(p for private, c for confidential,
		// empty enter for public");
		// eClass += classReader2(sc);

		/*
		 * Reader user input text for information using "stringReader" function
		 */
		System.out.print("Enter Event Location(Empty not allowed):");
		eLocation += stringReader(sc);

		/*
		 * Read two float numbers from command line and return combined string
		 * the value
		 */
		GeoCheck = makeOptional(sc,"add the geographical position of your event");
		if (GeoCheck){
		System.out.print("Geographical position of your event.\n");
		eGeo += floatReader(sc);
		}
		/*
		 * Reader user input text for information using "stringReader" function
		 */
		System.out.print("Enter Event File Name:");
		if (stringReader(sc).isEmpty()) {
			fileName = currentDate();
		} else {
			fileName = stringReader(sc);
		}
		/* close the scanner */
		sc.close();

		/* Get the current date value from system */
		eCreated += currentDate();
		/*
		 * Write out ics file with the user input file name the file name will
		 * be appended the current date time string automatically.
		 */
		icsNewEvent(fileName);

		/* Last comment and finish */
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

		while (isEmpty) {
			userIn = sc.nextLine();
			if (userIn != null) {
				isEmpty = false;
			}
		}
		return userIn;
	}

	/**
	 * Date reader
	 * 
	 * @return String date
	 */
	public static String dateReader(Scanner sc) {
		Boolean isEmpty = true;
		String dateInput = null;
		while (isEmpty) {
			System.out.print("event date(yyyy/mm/dd):");
			dateInput = sc.next();

			if (dateInput != null) {
				dateInput = dateInput.trim();

				/* Date validation check */
				if (isValidDate(dateInput)) {
					isEmpty = false;
				} else {
					/* Reset input data */
					dateInput = null;
					System.out.print("->>> The date you input is not valid! -\n- Please enter a valid ");
				}
			} else {
				System.out.print("->>> The date you input is not valid! -\n- Please enter the valid ");
			}
		}
		return dateInput;
	}

	/**
	 * Time reader
	 * 
	 * @return String time with hhmmss format
	 */
	public static String timeReader(Scanner sc) {
		Boolean isEmpty = true;
		String timeInput = null;
		while (isEmpty) {
			sc = new Scanner(System.in);
			System.out.print("event time(hh:mm:ss):");
			timeInput = sc.next();
			if (timeInput != null) {
				if (isValidTime(timeInput)) {
					isEmpty = false;
				} else {
					timeInput = null;
					System.out.print("->>> The time you input is not valid! -\n- Please enter a valid ");
				}

			} else {
				System.out.print("->>> The time you input is not valid! -\n- Plaese enter a valid");
			}
		}
		return timeInput;
	}

	/**
	 * Read integer user information from command line
	 * 
	 * @return userIn integer for user input integer data / empty not allowed
	 */
	public static int intReader(Scanner sc) {
		int userIn = 1;
		Boolean isEmpty = true;

		while (isEmpty) {
			/* read only integer */
			while (!sc.hasNextInt()) {
				userIn = sc.nextInt();
			}
		}
		return userIn;
	}

	/*
	 * float number reader from command line
	 */
	public static String floatReader(Scanner sc) {
		/* sample Geographic data POST */
		/* https://www.google.com/maps/@21.2973964,-157.8162139,19.55z */
		/* Default geographical position */
		float lat = 21.2973964f;
		float lon = -157.8162139f;

		Boolean checker = false;
		while (!checker) {
			try {
				System.out.print("Enter your event location latitude as a float number.(Range:-90.0 to 90.0)");
				lat = sc.nextFloat();
				checker = true;

				/* For latitude: -90 < lon < 90 */
				if (-90.0 > lat || lat > 90.0) {
					System.out.print("->>> Please, ");
					checker = false;
				}
			} catch (Exception InputMismatchException) {
				System.out.print("->>> The latitude should be a float number. Please, ");
				checker = false;
				sc.next();
			}
		}

		checker = false;
		while (!checker) {
			try {
				System.out.print("Enter your event location longitude as a float number.(Ragen:-180.0 to 180.0)");
				lon = sc.nextFloat();
				checker = true;

				/* For Longitude: -180 < lat < 180 */
				if (-180.0 > lon || lon > 180.0) {
					System.out.print("->>> Please, ");
					checker = false;
				}
			} catch (Exception InputMissmatchException) {
				System.out.print("->>> The logitude should be a float number. Please, ");
				checker = false;
				sc.next();
			}
		}
		/* Change the latitude and the longitude and combine */

		String latLon = Float.toString(lat) + ";" + Float.toString(lon);
		return latLon;
	}

	/**
	 * Write a new event
	 */
	public static void icsNewEvent(String fileName) {
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
			// bw.write(cUid + "\n");
			bw.write(dtStart + "\n");
			bw.write(dtEnd + "\n");
			bw.write(eDesc + "\n");
			bw.write(eLocation + "\n");
			bw.write(eSummary + "\n");
			bw.write(eCreated + "\n");
			// bw.write(eSeq + "\n");
			// bw.write(eStatus + "\n");
			// bw.write(dtStamp + "\n");
			// bw.write(eLastMod + "\n");

			/* added two new properties */
			if (GeoCheck){
			bw.write(eGeo + "\n");
			};
			bw.write(eClass + "\n");

			/* added one new property */
			// bw.write(eComment + "\n");

			/* Static DO NOT EARASE! */
			bw.write(end + "VEVENT" + "\n");
			bw.write(end + "VCALENDAR" + "\n");

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    /**
     * 
     */
     private static boolean makeOptional(Scanner sc, String prompt) {
    	 String option;
    	 do {
 			System.out.println("Would you like to " + prompt + "?");
 			System.out.print("Enter 1 for Yes\n" + "Enter 2 for No\n");
 			System.out.print("Response: ");
 			option = sc.nextLine().trim();
 		} while (!option.equals("1") && !option.equals("2") );


 		if (option.equals("1") ) {
 			return true;
 		} 
 		else {
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
		if (0 > h || 23 < h || 0 > m || 59 < m || 0 <s || 59 < s) {
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
	 * Prompts the user to choose a privacy setting Numbers are used to
	 * represent options because it is easier to type and verify
	 * 
	 * @param scan
	 * @return privacy (1) returns "PUBLIC" if 1 is entered (2) returns
	 *         "PRIVATE" if 2 is entered (3) returns "CONFIDENTIAL" if 3 is
	 *         entered
	 */
	public static String classReader(Scanner scan) {
		String privacy;

		do {
			System.out.println("Enter Privacy Settings:");
			System.out.print("Enter 1 for PUBLIC\n" + "Enter 2 for PRIVATE\n" + "Enter 3 for CONFIDENTIAL\n");
			System.out.print("Response: ");
			privacy = scan.nextLine().trim();
		} while (!isValidClass(privacy));

		int option = Integer.parseInt(privacy);

		if (option == 1) {
			System.out.println("Your privacy was set as PUBLIC");
			return "PUBLIC";
		} else if (option == 2) {
			System.out.println("Your privacy was set as PRIVATE");
			return "PRIVATE";
		} else {
			System.out.println("Your privacy was set as CONFIDENTIAL");
			return "CONFIDENTIAL";
		}
	}

	/**
	 * Given a String, it checks if 1,2, or 3 was entered.
	 * 
	 * @param privacy
	 * @return (1) true if 1,2, or 3 were given as input (2) false otherwise
	 */
	private static boolean isValidClass(String privacy) {
		if (privacy.equals("1") || privacy.equals("2") || privacy.equals("3") || privacy.isEmpty()) {

			return true;
		} else {
			System.out.println("- The setting you input is not valid! -");
			return false;
		}
	}

	/**
	 * Simple class input
	 * 
	 * @param sc
	 *            for user input
	 * @return PUBLIC, PRIVATE, or CONFIDENTIAL, default is PUBLIC
	 */
	public String classReader2(Scanner sc) {
		String userIn;
		boolean loop = false;

		while (!loop) {
			userIn = sc.nextLine();
			if (userIn.isEmpty()) {
				loop = true;
				return "PUBLIC";
			} else if (userIn.equals("p")) {
				loop = true;
				return "PRIVATE";
			} else if (userIn.equals("c")) {
				loop = true;
				return "CONFIDENTIAL";
			} else {
				loop = false;
				System.out.print("Please, Enter p, c, or empty");
			}
		}
		return "PUBLIC";
	}
}
