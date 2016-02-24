
public abstract class CalData implements CalProperty{
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
	/* Event Data */


	
	/* iCalendar begin */
	public void begin(String newValue) {
		begin += newValue;
	}
	
	/* End */
	public void end(String newValue) {
		end += newValue;
	}
	
	/* Version */
	public void version(String newValue) {
		version += newValue;
	}
	/* Product ID */
	public void prodId(String newValue) {
		prodId += newValue;
	}
	
	/* Calendar scale */
	public void cScale(String newValue) {
		cScale += newValue;
	}

	/* Calendar name */
	public void cName(String newValue) {
		cName += newValue;
	}

	/*Calendar description */
	public void cDesc(String newValue) {
		cDesc += newValue;
	}

	/* User date time zone */
	public void dtZone(String newValue) {
		dtZone += newValue;
	}

	/* Event start data time */
	public void dtStart(String newValue) {
		dtStart += newValue;
	}

	/* Event end data time */
	public void dtEnd(String newValue) {
		dtEnd += newValue;
	}

	/* Calendar date time stamp */
	public void dtStamp(String newValue) {
		dtStamp += newValue;
	}

	/* Calendar uid */
	public void cUid(String newValue) {
		cUid += newValue;
	}

	/* Event method */
	public void eMothod(String newValue) {
		eMethod += newValue;
	}

	/* Event created date time */
	public void eCreated(String newValue) {
		eCreated += newValue;
	}

	/* Event last modify date time */ 
	public void eLastMod(String newValue) {
		eLastMod += newValue;
	}

	/* Event description */
	public void eDesc(String newValue) {
		eDesc += newValue;
	}

	/* Event sequence */
	public void eSeq(String newValue) {
		eSeq += newValue;
	}

	/* Event status */
	public void eStatus(String newValue) {
		eStatus += newValue;
	}

	/* Event summary */
	public void eSummary(String newValue) {
		eSummary += newValue;
	}
	
	/* Event location */
	public void eLocation(String newValue) {
		eLocation += newValue;
	}
	
	/* Event geo */
	public void eGeo(String newValue) {
		eGeo += newValue;
	}

}
