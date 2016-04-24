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
				cName += inputReader.string(sc);
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
				cDesc += inputReader.string(sc);
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
				eSummary += inputReader.string(sc);
				loop = false;
			} catch (NullPointerException e) {
				System.out.println("\n*** Warning: event summary should exist.");
			}
		}

		// Refresh loop for new loop
		loop = true;
		/* Read event start date */
		String startDate = null;
		while (loop) {
			System.out.print("Please, enter event start date (format:yyyy/mm/dd):");
			try {
				startDate = inputReader.date(sc);
				dtStart += startDate.replace("/", ""); // Remove separators
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
				startTime = inputReader.time(sc);
				dtStart += startTime.replace(":",
						""); /* Remove time separator */
				loop = false;
			} catch (InputMismatchException e) {
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
				endDate = inputReader.date(sc);
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
				endTime = inputReader.time(sc);
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
		if (!isValid.calEvent(startDate, startTime, endDate, endTime)) {
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
				eDesc += inputReader.string(sc);
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
				eClass += inputReader.calClass(sc);
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
				eLocation += inputReader.string(sc);
				loop = false;
			} catch (NullPointerException e) {
				System.out.println("\n*** Warning: Empty is not allowed.");
			}
		}

		GeoCheck = inputReader.makeOptional(sc, "add the geographical position of your event");
		if (GeoCheck) {
			System.out.print("Geographical position of your event.\n");
			// Refresh loop for new loop
			loop = true;
			/* Read latitude */
			Float lat = null;
			while (loop) {
				System.out.print("Please, enter your event location logitude (Range: -90.0 <= x <= 90.0)");
				try {
					lat = inputReader.lat(sc);
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
					lon = inputReader.lon(sc);
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
			fileName = inputReader.string(sc);
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
}
