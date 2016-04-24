
import java.io.IOException;
import java.util.Scanner;

/**
 * ICS 314 Spring 2016 iCalendar project
 * 
 * 	Team Cinco: Lucas Calabrese, Nicolas Winters, Song Min Kim
 *  Due date: 04/29/2016
 */

public class InsertComment {

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
				event[i] = inputReader.event(fileName[i]);
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
		int sortedArray[] = eventSort.getSorted(event, fileName, eventNum);
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
					distance[sortedArray[i]] = greatDistance.cal(lat[sortedArray[i]], lon[sortedArray[i]], lat[sortedArray[j]], lon[sortedArray[j]]);
				}
				
				// Pass the null geo data schedule
				i = j - 1;
			}
		}
		
		/*
		 * insert the distance
		 */
		for (int i = 0; i < sortedArray.length - 1; i++) {
			
			insertCategory.comment(fileName[sortedArray[i]],event[sortedArray[i]],distance[sortedArray[i]]);
		}
		sc.close();
	}
}