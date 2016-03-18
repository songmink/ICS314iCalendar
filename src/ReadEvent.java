import java.io.*;
import java.util.Scanner;

public class ReadEvent {
	public static void main(String[] args) {
	    //String[] fileNames = GetFileNames();
	   // System.out.println(Arrays.toString(fileNames));
	    }	    
	public static String[] GetFileNames(Scanner sc) {
	  String userinput;
	  String checkextension;
	  String[] FileNamesArray;
	  File file;
	  System.out.println("Enter ICS File Names sperated by spaces (ex: filename.ics filename.ics):");
	  userinput = sc.nextLine();
	  FileNamesArray = userinput.split("\\s+");
	  for (int i = 0; i < FileNamesArray.length; i++) {
		  file = new File(FileNamesArray[i]);
		  checkextension = FileNamesArray[i].substring(FileNamesArray[i].lastIndexOf(".") + 1, FileNamesArray[i].length());
		  if (checkextension.equalsIgnoreCase("ics")){
			  if (file.exists()) {
				  return FileNamesArray;
			  }
		  }
	  }		
	  return null;
	}
}

