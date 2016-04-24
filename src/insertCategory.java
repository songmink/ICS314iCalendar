import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class insertCategory {
	public static void comment(String fileName, String[] icsData, double distance) {
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
