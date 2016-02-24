
import java.io.*;
import java.util.Scanner;
public class main {
   public static void main(String[]args) {
	   System.out.println("This software is currently only meant for you to add only one event");
	   /* Stores what the user enters*/
	   String response;
	   
	   /* Used for storing Descriptions */
	   //StringBuilder description = new StringBuilder();
	   
	   /*used for storing an option that the user chose*/
	   //int option;
	   

	  // boolean exit = true;
	   Scanner scan = new Scanner(System.in);
	   boolean fileMade = false;
	   
	   PrintWriter writer;
	   BufferedWriter buffWriter;
	   /* Begin running the interface*/
	   //while(exit) {
		 /*  
		   System.out.println("\n" + 
                   "Which of these options would you like to add? Press an number and then press enter"
                   + "\n" + "(1) Create an Event"  
		             + "\n" + "(2) Exit");
		   */
		   //option = scan.nextInt();
		 //  if(option == 1) { // Add an event
			   try {
				 
				   
				   if(!fileMade) {
					   scan.nextLine();
					   System.out.print("Please enter your email: ");					   
					   response = scan.nextLine();
					   String icsFile = response + ".ics";
					   writer = new PrintWriter(icsFile);
			       	   buffWriter = new BufferedWriter(writer);		
			       	   fileMade = true;
			           System.out.print("Please enter the name of your calender: ");
			           response = scan.nextLine();
			           buffWriter.write("BEGIN:VCALENDAR\n" +
						       "PRODID:-//Google Inc//Google Calendar 70.9054//EN\n" +
							   "VERSION:2.0\n" +
							   "CALSCALE:GREGORIAN\n" +
							   "METHOD:PUBLISH\n" +
							   "X-WR-CALNAME:" + response +"\n" +
							   "X-WR-TIMEZONE:Pacific/Honolulu\n" +
							   "BEGIN:VTIMEZONE\n" +
							   "TZID:Pacific/Honolulu\n" +
							   "X-LIC-LOCATION:Pacific/Honolulu\n" +
							   "BEGIN:STANDARD\n" +
							   "TZOFFSETFROM:-1000\n" +
							   "TZOFFSETTO:-1000\n" +
							   "TZNAME:HST\n" +
							   "DTSTART:19700101T000000\n" +
							   "END:STANDARD\n" +
							   "END:VTIMEZONE\n" +
							   "BEGIN:VEVENT\n"); 
			           /*date start
			            * might need to add variables for month and year I don't think 
			            * events last longer than a month*/
			           
					   System.out.println("Please enter the year this starts in 4 integers");
						
					   response = scan.nextLine();
					   buffWriter.write("DTSTART;TZID=Pacific/Honolulu:" + response);
					   System.out.println("Please enter the month this starts in 2 integers followed by the day,"
								+ "place 0's when necessary, for example July 4 would be 0704");
					   response = scan.nextLine();
					  
					   buffWriter.write(response + "T");
				       /** I need to double check this one*/
					   System.out.println("Enter the start time in 4 digits, after 12:00pm is 13:00, " + 
					       "add zeros where necessary");
					   response = scan.nextLine();
					   buffWriter.write(response + "00\n");
						
						/*date end*/ 
						/*System.out.println("Please enter the year this ends in 4 integers");
						response = scan.nextLine();
						buffWriter.write("DTEND;TZID=Pacific/Honolulu:" + response);
						System.out.println("Please enter the month this ends in 2 integers followed by the day,"
								+ "place 0's when necessary, for example July 4 would be 0704");
						response = scan.nextLine();
						buffWriter.write(response + "T");
						/** I need to double check this one*/
						/*System.out.println("Enter the start time in 4 digits, after 12:00pm is 13:00, " + 
								"add zeros where necessary");
						response = scan.nextLine();
						buffWriter.write(response + "00\n");
						*/
						buffWriter.close();
						scan.close();
				   }
			       
			   }
			   catch(FileNotFoundException e) {
				   System.out.println("oops");
				   scan.close();
				   
			   } catch (IOException e) {
				   // TODO Auto-generated catch block
				   e.printStackTrace();
				   scan.close();
		       }
			   
			  
			   
			   /*
			   System.out.println("Please enter a description, "
			   		+ "and press enter a second to indicate that you have finished: "
			   		+ "to skip this option, just press enter");
		       boolean enter = false;
		       scan.nextLine();
		       
		       while(!enter) {
		    	   
		    	   response = "";
		    	   response = scan.nextLine();
		    	   if(response.length() == 0) {
		    		   enter = true;
		    		   //System.out.print(description);
		    	   }
		    	   else {
			           description.append(response + "\n");
		    	   }
		    	  
		       }*/
			   
		   //}
		   
		      
	       //if(option == 2) {
	    	   System.out.println("Thank you for using this software");
	    	   //exit = false; 
	      // }
	   //}

	   
	   /*    
	   BufferedReader stdout = new BufferedReader(new InputStreamReader(sstdout));
	   BufferedWriter stdin = new BufferedWriter(new OutputStreamWriter(sstdin2));*/
	   
   }
}
