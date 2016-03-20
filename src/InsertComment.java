
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InsertComment {

	public static void main(String[] args) throws IOException {
		
		/* default event number and category number */
		int eventNum = 2;
		int catNum = 25;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("How many events do you want to read?(default 2):");
		eventNum = sc.nextInt();
		sc.nextLine();
		
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
		double lat1R = lat1 * R;
		double lat2R = lat2 * R;
		double dLat = R * (lat2 - lat2);
		double dLon = R * (lon2 - lon1);
		
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(lat1R) * Math.cos(lat2R) * Math.sin(dLon/2) * Math.sin(dLon/2);
		double c = 2* Math.asin(Math.sqrt(a));
		double dist = earthRMetre * c;

		return dist;
	}


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
