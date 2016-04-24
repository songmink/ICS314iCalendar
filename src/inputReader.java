import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ICS 314 Spring 2016 iCalendar project
 * 
 * Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim Due date:
 * 04/29/2016
 */

public class inputReader {
	/**
	 * Read string user information from command line
	 * 
	 * @return userIn string for user input string data
	 */
	public static String string(Scanner sc) {
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
	public static String date(Scanner sc) {
		String dateInput = null;
		dateInput = sc.nextLine();
		dateInput = dateInput.trim();

		if (dateInput.isEmpty() || !isValid.calDate(dateInput)) {
			throw new InputMismatchException("Error: Invalid date.");
		}
		return dateInput;
	}

	/**
	 * Time reader
	 * 
	 * @return String time with hh:mm:ss format
	 */
	public static String time(Scanner sc) {
		String timeInput = null;
		timeInput = sc.nextLine();

		if (timeInput.isEmpty() || !isValid.calTime(timeInput)) {
			throw new InputMismatchException("Error: Empty or invalid time.");
		}
		return timeInput;
	}

	/**
	 * Latitude reader
	 */
	public static float lat(Scanner sc) {
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
	public static float lon(Scanner sc) {
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
	 * Simple class input
	 * 
	 * @param sc
	 *            for user input
	 * @return PUBLIC, PRIVATE, or CONFIDENTIAL, default is PUBLIC
	 */
	public static String calClass(Scanner sc) {
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

	/**
	 * Event file reader
	 * 
	 * @param string
	 *            filename
	 * @return string array
	 * @throws IOException
	 */

	public static String[] event(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String cat[] = new String[20];
		int i = 0;
		while ((line = br.readLine()) != null) {
			cat[i] = line;
			i++;
		}
		br.close();
		return cat;
	}

}
