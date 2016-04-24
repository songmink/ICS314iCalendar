import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ICS 314 Spring 2016 iCalendar project
 * 
 * Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim Due date:
 * 04/29/2016
 */

public class eventSort {
	/**
	 * Filters out fileNames that do not contain geo dates May want to consider
	 * providing info about where the start of the circle distance is to the
	 * end,
	 * 
	 * @param events
	 * @param fileNames
	 * @param eventNum
	 * @return
	 */
	public static int[] getSorted(String[][] events, String[] fileNames, int eventNum) {

		PriorityQueue<FileNamesAndTimes> queue;
		queue = new PriorityQueue<FileNamesAndTimes>(5, new eventCompare());

		FileNamesAndTimes event = null;
		boolean okayToAdd = false;
		String dataToAdd = "";
		for (int i = 0; i < eventNum; i++) {
			event = new FileNamesAndTimes();
			event.index = i;
			event.fileName = fileNames[i];

			for (int j = 0; j < events[i].length; j++) {
				if (events[i][j] != null && events[i][j].length() >= 8
						&& events[i][j].substring(0, 8).equals("DTSTART:")) {
					dataToAdd = events[i][j].substring(8, events[i][j].length());
					int indexOfT = dataToAdd.indexOf('T');
					// search for time
					dataToAdd = dataToAdd.substring(indexOfT + 1, dataToAdd.length());
					// parse it
					event.time = Integer.parseInt(dataToAdd);

				}
				if (events[i][j] != null && events[i][j].length() >= 4 && events[i][j].substring(0, 4).equals("GEO:")) {
					okayToAdd = true;
					break;
				}

			}
			if (okayToAdd) {
				queue.add(event);
				okayToAdd = false;
			}
		}
		if (queue.size() > 0) {
			int queueSize = queue.size();
			int[] array = new int[queueSize];
			for (int i = 0; i < queueSize; i++) {
				array[i] = queue.poll().index;
			}
			return array;
		}
		return null;
	}

	public static class eventCompare implements Comparator<FileNamesAndTimes> {
		// If order is incorrect, subtract event2 from event1
		// I'm somewhat sure that they are meant to be for the same date,
		public int compare(FileNamesAndTimes event1, FileNamesAndTimes event2) {
			return event1.time - event2.time;
		}
	}

}
