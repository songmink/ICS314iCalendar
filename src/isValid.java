/**
 * ICS 314 Spring 2016 iCalendar project
 * 
 * Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim Due date:
 * 04/29/2016
 */

public class isValid {
	/**
	 * Date validation
	 * 
	 * @return true if valid otherwise false
	 */
	public static boolean calDate(String date) {
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
	public static boolean calTime(String t) {
		t = t.trim();
		String[] hms = t.split(":");

		if (hms.length != 3) {
			return false;
		}

		int h = Integer.parseInt(hms[0], 10);
		int m = Integer.parseInt(hms[1], 10);
		int s = Integer.parseInt(hms[2], 10);
		/* check the time validation */
		if (h < 0 || 23 < h || m < 0 || 59 < m || s < 0 || 59 < s) {
			return false;
		}
		return true;
	}

	/**
	 * Compare start and end date time
	 * 
	 * @return Boolean false if not valid otherwise true
	 */
	public static boolean calEvent(String sd, String st, String ed, String et) {

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

}
