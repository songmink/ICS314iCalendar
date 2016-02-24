/**
 * RFC5545 Properties Interface
 * @author fairisle
 *
 */
public interface CalProperty {
	/* Begin */
	void begin(String newValue);

	/* Begin */
	void end(String newValue);
	
	/* Program version */
	void version(String newValue);

	/* Product ID */
	void prodId(String newValue);

	/* Calendar scale */
	void cScale(String newValue);

	/* Calendar name */
	void cName(String newValue);

	/*Calendar description */
	void cDesc(String newValue);

	/* User date time zone */
	void dtZone(String newValue);

	/* Event start data time */
	void dtStart(String newValue);

	/* Event end data time */
	void dtEnd(String newValue);

	/* Calendar date time stamp */
	void dtStamp(String newValue);

	/* Calendar uid */
	void cUid(String newValue);

	/* Event method */
	void eMothod(String newValue);

	/* Event created date time */
	void eCreated(String newValue);

	/* Event last modify date time */ 
	void eLastMod(String newValue);

	/* Event description */
	void eDesc(String newValue);

	/* Event sequence */
	void eSeq(String newValue);

	/* Event status */
	void eStatus(String newValue);

	/* Event summary */
	void eSummary(String newValue);
	
	/* Event location */
	void eLocation(String newValue);
	
	/* Event geo */
	void eGeo(String newValue);
}
