/**
 * Accepts files that were created by EventCreator
 * and looks for usable files and writes the great circle
 * distance between two events
 * in the comment field of the event that occurs first
 * It does this for every file that contains a geo field,only if
 * an event that occurs after it contains the geo field
 * @author Song Min Kim, Lucas Calabrese, Nicholas Winters
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class InsertComment {
 	/**
   	 * the main method
    	 * @param args
         * @throws IOException
         */
	public static void main(String[] args) throws IOException {
		
		/* default event number and category number */
		int eventNum = 2;
		int catNum = 50;
		
		Scanner sc = new Scanner(System.in);
		
		try{
			do {	
				System.out.print("How many events do you want to read?(default 2):");
				eventNum = sc.nextInt();
				sc.nextLine();
			}
			while(eventNum < 0); 
		}
		catch(Exception e) {
			System.out.println("ERROR: integer");
			System.exit(0);
		}
		/*
		 *  call reader function
		 */
		String[][] event = new String[eventNum][catNum];
		String fileName[] = new String[eventNum]; 
		
			for (int i = 0; i < eventNum; i++) {
				System.out.print("Input event file name:");
				fileName[i] = sc.nextLine();
				event[i] = eventReader(fileName[i]);
			}
		

		/*
		 * find geo data / need function ???
		 */
		String[] geoData = new String[eventNum];
		for (int i = 0; i < event.length; i++) {
			Boolean hasgeo = false;
			for(int j = 0; j < event[i].length; j++) {
				if(event[i][j].contains("END:VCALENDAR")) {
					break;
				}
    	        if(event[i][j] != null && event[i][j].length() >= 4 && event[i][j].substring(0,4).equals("GEO:")  ) {
					geoData[i] = event[i][j];
					hasgeo = true;
					break;
				}
			}
			if(!hasgeo){
				geoData[i] = null;
			}
		}
		
		/*
		 * string geo data to double geo data / need function???
		 */
		double lat[] = new double[eventNum];
		double lon[] = new double[eventNum];
		for(int i = 0; i < eventNum; i++) {
			if (geoData[i] != null) {
				geoData[i] = geoData[i].replace("GEO:", "");
				String temp[] = geoData[i].split(";");
			
				lat[i] = Double.parseDouble(temp[0]);
				lon[i] = Double.parseDouble(temp[1]);
			}
		}
		
		/*
		 *  Calculate distance by input order 
		 */
		int sortedArray[] = getSorted(event, fileName, eventNum);
		if (sortedArray == null || sortedArray.length < 2){
			System.out.println("Needs more than 1 file with Geo field");
			System.exit(0);
		}
		double distance[] = new double [eventNum];
		for(int i = 0; i < sortedArray.length - 1; i++){
			
			// If the schedule does not have GEO data, pass the next schedule
			int j = i + 1;
			if(geoData[sortedArray[i]] != null){
				
				// Go to the next schedule until the schedule has GEO data
				while(geoData[sortedArray[i+1]] == null && j < sortedArray.length - 1){
					j++;
				}
				// Calculate the distance when the next schedule has GEO data (for the last data)
				if(geoData[sortedArray[j]] != null) {
					distance[sortedArray[i]] = calDistance(lat[sortedArray[i]], lon[sortedArray[i]], lat[sortedArray[j]], lon[sortedArray[j]]);
				}
				
				// Pass the null geo data schedule
				i = j - 1;
			}
		}
		
		/*
		 * insert the distance
		 */
		for (int i = 0; i < sortedArray.length - 1; i++) {
			
			insertComment(fileName[sortedArray[i]],event[sortedArray[i]],distance[sortedArray[i]]);
		}
		sc.close();
	}

	/**
	 * Event file reader
	 * Stores the text within a file into an array that can only contain up to 20 lines
	 * @param string filename
	 * @return string array
	 * @throws IOException 
	 */	
	public static String[] eventReader(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line;
		String cat[] = new String[20];
		int i = 0;
		while((line = br.readLine()) != null) {
			cat[i] = line;
			i++;
		}
		br.close();
		return cat;
	}

	/**
	 * Uses the haversine function formula
	 * from https://en.wikipedia.org/wiki/Great-circle_distance
	 * Calculates the great circle distance given the latitude and
	 * longitude of two events
	 * @param lat1 the latitude of the first occurring event
	 * @param lon1 the longitude of the first occurring event
	 * @param lat2 the latitude of the second occurring event
	 * @param lon2 the longitude of the second occurring event
	 * @return dist the great circle distance
	 */
	public static double calDistance(double lat1, double lon1, double lat2, double lon2) {
		// same position
		if(lat1 == lat2 && lon1 == lon2) {
			return 0;
		}
		/* Earth Mean Radius */
		//final double earthRmile = 3953.0;
		final double earthRKm = 6371;
		double lat1R = Math.toRadians(lat1);
		double lat2R = Math.toRadians(lat2);
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1R) * Math.cos(lat2R) * Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		double dist = earthRKm * c;
		return dist;
	}
	
	/**
	 * Object that contains the index of where a fileName, 
	 * and other data would be found for a event file.
	 * @author Lucas Calabrese
	 */
	private static class FileNamesAndTimes {
		int time = -1;
		String fileName;
		int index;
		public FileNamesAndTimes() {
			
		}	
	}
	
        /**
	 * Comparator for a priority queue that will help sort the event files
	 * @author Lucas Calabrese
	 */
	public static class eventCompare implements Comparator<FileNamesAndTimes> {
		public int compare(FileNamesAndTimes event1, FileNamesAndTimes event2) {			
			return event1.time - event2.time;
		}
	} 

    /**
     * Filters out fileNames that do not contain geo dates
     * and creates an array where the first index contains the
     * index of the index of first occurring event within the event array
     * for the file array and other arrays within this class, the 2nd index,
     * contains the 2nd index contains the index of the second occurring event and so on
     * @param events the double array that contains the text of each entered file
     * @param eventNum the amount of events entered
     * @return (1) array - an integer array that contains the indexes of 
     *                     the fileNames in sorted order
     *         (2) null  - if there were no files to sort 
     */
    public static int[] getSorted(String[][] events, String[] fileNames,int eventNum) {
    	
    	PriorityQueue<FileNamesAndTimes> queue;
		queue = new PriorityQueue<FileNamesAndTimes>(5, new eventCompare() );
		FileNamesAndTimes event = null;
		boolean okayToAdd = false;
		String dataToAdd = "";
    	 for(int i = 0; i < eventNum; i++) {
    	     event = new FileNamesAndTimes();
    	     event.index = i;
    	     event.fileName = fileNames[i];
    	     
    	     for(int j = 0; j < events[i].length; j++) {
    	        if(events[i][j] != null && events[i][j].length() >= 8 && events[i][j].substring(0,8).equals("DTSTART:") ) {
                    dataToAdd = events[i][j].substring(8,events[i][j].length() );
    	        	int indexOfT = dataToAdd.indexOf('T');
    	        	//search for time
    	        	dataToAdd = dataToAdd.substring(indexOfT+1,dataToAdd.length() );
    	        	//parse it
    	        	event.time = Integer.parseInt(dataToAdd); 
    	        	
    	        }
    	        if(events[i][j] != null && events[i][j].length() >= 4 && events[i][j].substring(0,4).equals("GEO:")  ) {
    	        	okayToAdd = true;
    	        	break;
    	        }
    	        
    	     }
    	     if(okayToAdd) {
    	    	 queue.add(event);
    	    	 okayToAdd = false;
    	     }
    	 }
    	 if(queue.size() > 0) {
    		  int queueSize = queue.size();
    	      int[] array = new int[queueSize];
              for(int i = 0; i < queueSize; i++) {
            	  array[i] = queue.poll().index;
              }
    	      return array;
    	 }
    	 return null;
    }

    	/**
    	 * To do
    	 * @param fileName
    	 * @param icsData
     	 * @param distance
    	 */
	public static void insertComment(String fileName, String[] icsData, double distance) {
		StringBuilder b = new StringBuilder();
		b.append(fileName);

		for (int i = 0; i < icsData.length; i++) {
			if(icsData[i] == null){
				break;
			}
			System.out.println(icsData[i]);
	        if(icsData[i] != null && icsData[i].length() >= 4 && icsData[i].substring(0,4).equals("GEO:")  ) {
				System.out.print("COMMENT:" + distance +"\n");
			}
		}
		
		try {
			File file = new File(b.toString());
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < icsData.length; i++) {
				if(icsData[i] == null){
					break;
				}
				
				// update previous COMMENT data
				if( !(icsData[i].length() >= 8 && icsData[i].substring(0,8).equals("COMMENT:") )  ) {
					bw.write(icsData[i] + "\n");
	    	        if(icsData[i].length() >= 4 && icsData[i].substring(0,4).equals("GEO:")  ) {
						bw.write("COMMENT:" + distance + "\n");
					}
				}

			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}