
public class dataVerifcationFunctions {
   public boolean verifyYear(String year) {
	   try{		  
		   int year2 = Integer.parseInt(year);
	      if( 2000 <= year2 && year2 <= 2100) {
	         return true;
	      }
	   }
	   catch(Exception e) {
		   return false;
	   }
	   return false;
   }
   public boolean verifyDayAndMonth(int year,String response) {
	   try {
		   
		   int month2 = Integer.parseInt(response.substring(0,2) );
		   int day2 = Integer.parseInt(response.substring(2) );
			   
			   switch(month2) {
			    case 1:
			    	if(day2 <= 31 && day2 >= 1) {
			    		return true;
			    	}
			        break;
			    case 2:
			    	if(day2 == 29 && year % 4 != 0) {
						   return false;
					 }
			    	if(day2 <= 29 && day2 >= 1) {
			    		return true;
			    	}
			        break;
			    case 3:
			    	if(day2 <= 31 && day2 >= 1) {
			    		return true;
			    	} 
			        break;
			    case 4:
			    	if(day2 <= 30 && day2 >= 1) {
			    		return true;
			    	}  
			        break;
			    case 5:
			    	if(day2 <= 31 && day2 >= 1) {
			    		return true;
			    	}
			        break;
			    case 6:
			    	if(day2 <= 30 && day2 >= 1) {
			    		return true;
			    	}
			        break;
			    case 7:
			    	if(day2 <= 31 && day2 >= 1) {
			    		return true;
			    	} 
			        break;
			    case 8:
			    	if(day2 <= 31 && day2 >= 1) {
			    		return true;
			    	}   
			        break;
			    case 9:
			    	if(day2 <= 30 && day2 >= 1) {
			    		return true;
			    	} 
			        break;
			    case 10:
			    	if(day2 <= 31 && day2 >= 1) {
			    		return true;
			    	}  
			        break;
			    case 11:
			    	if(day2 <= 30 && day2 >= 1) {
			    		return true;
			    	} 
			        break;
			    case 12:
			    	if(day2 <= 31 && day2 >= 1) {
			    		return true;
			    	} 
			        break;
			    default:
			        return false;
			} 
				  
	   }
	   //TODO nbn
	   catch(Exception e1) {
		   return false;
	   }
	return false;
   }
   
   public boolean verifyTime(String time){ 
	   return false;
   }
   
   public boolean verifyFuture(String start, String end) {
	   return false;
   }
   
}

