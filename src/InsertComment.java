
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InsertComment {

	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		/*
		 *  call reader function
		 */
		boolean loop = true;
		int eCount = 0;
		String[][] event = null;
		while (loop) {
			System.out.print("Input event file name:");
			String filename = sc.nextLine();
			event[eCount] = eventReader(sc, filename);

			// First event should exist, but ?
			if (eCount == 0 && event[eCount] == null) {
				System.out.print("There is no event. Program stop.");
				loop = false;
				System.exit(1);
			} else if (event[eCount] == null) {
				loop = false;
			}
			eCount++;
		}
		
		/*
		 *  If there is only one event, then terminate
		 */
		if(event.length == 1) {
			System.out.print("You put only one event. You do not need to caculate distances between events");
			System.exit(1);
		}
		/*
		 * find geo data / need function ???
		 */
		String[] geoData = null;
		for (int i = 0; i < event.length; i++) {
			for(int j = 0; j < event[j].length; j++) {
				if(event[i][j].contains("Geo")) {
					geoData[i] = event[i][j];
				}
			}
		}
		
		/*
		 * string geo data to double geo data / need function???
		 */
		double lat[] = null, lon[] = null;
		for(int i = 0; i < geoData.length; i++) {
			String temp[] = geoData[i].split(":");
			lat[i] = Double.parseDouble(temp[0]);
			lon[i] = Double.parseDouble(temp[1]);
		}
		
		/*
		 *  Calculate distance by input order 
		 */
		double distance[] = null;
		for(int i = 0; i < lat.length - 1; i++){
			distance[i] = calDistance(lat[i], lon[i], lat[i+1], lon[i+1]); 
		}
	}

	/**
	 * Event file reader
	 * @param scanner sc
	 * @param string filename
	 * @return string array
	 * @throws IOException 
	 */
	
	@SuppressWarnings("null")
	public static String[] eventReader(Scanner sc, String filename) throws IOException {
		FileReader f = new FileReader(filename);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(f);
		String line;
		String cat[] = null;
		int i = 0;
		while((line = br.readLine()) != null) {
			cat[i] = line;
			i++;
		}
		return cat;
	}

	public static double calDistance(double lat1, double lon1, double lat2, double lon2) {
		/* Earth Mean Radius */
		//final double earthRmile = 3953.0;
		final double earthRKilo = 6371.0;
		
		double dX = Math.cos(lat2) * Math.cos(lon2) - Math.cos(lat1) * Math.cos(lon1);
		double dY = Math.cos(lat2) * Math.sin(lon2) - Math.cos(lat1) * Math.cos(lat1);
		double dZ = Math.sin(lat2) - Math.sin(lat1);
		double C = Math.sqrt(Math.pow(2, dX) + Math.pow(2, dY) +Math.pow(2, dZ));
		double dc = 2 * Math.asin(C/2);
		double dist = earthRKilo * dc;

		return dist;
	}

	public static void insertComment(String s) {
		// TODO insert comment before END:VEVENT

	}

}
