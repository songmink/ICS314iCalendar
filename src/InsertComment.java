
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

	public static void main(String[] args) throws IOException {
		
		/* default event number and category number */
		int eventNum = 2;
		int catNum = 25;
		
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
			System.out.println("ERROR: problem with integer");
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
			for(int j = 0; j < event[i].length; j++) {
				if(event[i][j].contains("GEO")) {
					geoData[i] = event[i][j];
					break;
				}
			}
		}
		
		/*
		 * string geo data to double geo data / need function???
		 */
		double lat[] = new double[eventNum];
		double lon[] = new double[eventNum];
		for(int i = 0; i < eventNum; i++) {
			geoData[i] = geoData[i].replace("GEO:", "");
			String temp[] = geoData[i].split(";");
			
			lat[i] = Double.parseDouble(temp[0]);
			lon[i] = Double.parseDouble(temp[1]);
			
			// TODO remove later
			System.out.println(lat[i] + ":" + lon[i]);
		}
		
		/*
		 *  Calculate distance by input order 
		 */
		double distance[] = new double [eventNum - 1];
		for(int i = 0; i < eventNum - 1; i++){
			distance[i] = calDistance(lat[i], lon[i], lat[i+1], lon[i+1]);
			
			//TODO remove later
			System.out.println(distance[i]);
		}
		
		/*
		 * insert the distance
		 */
		for (int i = 0; i < eventNum - 1; i++) {
			insertComment(fileName[i],event[i],distance[i]);
		}
		sc.close();
	}

	/**
	 * Event file reader
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

	/*
	 * I used the first formula of wikipedia, haversin, but the distance is a bit large...
	 */
	public static double calDistance(double lat1, double lon1, double lat2, double lon2) {
		// same position
		if(lat1 == lat2 && lon1 == lon2) {
			return 0;
		}
		/* Earth Mean Radius */
		//final double earthRmile = 3953.0;
		final double earthRMetre = 6371;
		final double R = Math.PI / 180;
		double lat1R = lat1 * R; // convert to radians pi/180 * 180*x
		double lat2R = lat2 * R;
		double dLat = R * (lat2 - lat2);
		double dLon = R * (lon2 - lon1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1R) * Math.cos(lat2R) * Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2* Math.asin(Math.sqrt(a));
		double dist = earthRMetre * c;

		return dist;
	}
	/* code for filtering (if necessary)
	 //Do we allow the same file to be used more than once?

      example of user input
	  file1 
	  file2
	  file1
	  file3
	  file1
	  
	  sorts
	  file1 <- circle distance from file1 to file1
	  file1 <- circle distance from file1 to file1
	  file1 <- circle distance from file2 to file1
	  file2 <- circle distance from file2 to file3
	  file3<- empty
	  
	  
	  Do we filter files where the two files next to each other
	  where at least one of them does not contain a correct Geo field?
	  
	  file1.geo = null <- cannot calculate <- useless
	  file2.geo = null <- cannot calculate <- useless
	  file3.geo = 2,2 <- can calculate <- required to use
	  file4.geo = 4,4 <- cannot calculate <- required to use
	 
	 */
	
	/* code for sorting
	
	
	//Can access like this, int a = map.time
	//Or add setters and getters
	private static class FileNamesAndTimes {
	   //-1 marks a bad/blank time value
	   //parse the time portion into a int
	   //Because we use military time
	   //1704 > 1204
	   //0404 > 0400
	   //0499 makes no sense, but I don't know what we can assume about times
		int time = -1;
		String fileName;
		int index;
		public FileNamesAndTimes() {
			
		}	
	}
	
	public static class eventCompare implements Comparator<FileNamesAndTimes> {
		//If order is incorrect, subtract event2 from event1
	    //I'm somewhat sure that they are meant to be for the same date,
		public int compare(FileNamesAndTimes event1, FileNamesAndTimes event2) {			
			return event1.time - event2.time;
		}
	} 

    //Assume index of fileName = index of Double Array
	//Rearrange fileName array Based on time 
	//do different times need to be taken into consideration?
	// model 2 contains a 'z', do we assume that the files that are
	// used are okay?
    public static void sort(String[][] events, String fileNames,int eventNum) {
    	PriorityQueue<FileNamesAndTimes> queue;
		queue = new PriorityQueue<FileNamesAndTimes>(5, new eventCompare() );
		FileNamesAndTimes event = null;
		String dataToAdd;
    	 for(int i = 0; i < eventNum; i++) {
    	     event = new FileNamesAndTimes();
    	     event.index = i;
    	     event.fileName = fileNames[0];
    	     for(int j = 0; j < 20; j++) {
    	        if(events[i][j].contains("DTSTART:") ) {
    	        	int indexOfT = events[i][j].indexOf('T');
    	        	//search for time
    	        	dataToAdd = dataToAdd.substring(indexOfT+1,dataToAdd.length() );
    	        	//parse it
    	        	event.time = Integer.parseInt(dataToAdd);
                    
    	        	break;
    	        }
    	        
    	     }
    	     queue.add(event);
    	     
    	 }
    	
    }
	
	*/
	public static void insertComment(String fileName, String[] icsData, double distance) {
		StringBuilder b = new StringBuilder();
		b.append("new-");
		b.append(fileName);

		for (int i = 0; i < icsData.length; i++) {
			if(icsData[i] == null){
				break;
			}
			System.out.println(icsData[i]);
			if(icsData[i].contains("GEO")) {
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
				bw.write(icsData[i] + "\n");
				if(icsData[i].contains("GEO")) {
					bw.write("COMMENT:" + distance + "\n");
				}

			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}