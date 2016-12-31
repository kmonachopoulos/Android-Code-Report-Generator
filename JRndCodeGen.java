/*
  ------------------------------------------------------------------------------------------------------------------------------------------
  Project       : Random -code/password- Generator
  File          : JRndCodeGen.java
  Description   : This file is responsible for creating the database into SQLite correct format.
  Author        : Konstantinos Monahopoulos
  ------------------------------------------------------------------------------------------------------------------------------------------
*/

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JRndCodeGen {

	public static void main(String[] args) {
		Random rand = new Random();	
		String s = "2017-01-01"; // Set start date
		String e = "2018-01-01"; // Set end date
		int Iday, Imonth;
		String Sday,Smonth;
		
		// Call methods of LocalDate class.
		LocalDate start = LocalDate.parse(s);
		LocalDate end = LocalDate.parse(e);	
		List<LocalDate> totalDates = new ArrayList<>();
		
		// Convert each date to string and print it following the correct format to the output.
		while (!start.isAfter(end)) {
		    totalDates.add(start);
	    
		    Sday=String.valueOf(start.getDayOfMonth());
		    Smonth=String.valueOf(start.getMonthValue());	
		    
		    if(start.getDayOfMonth()<10){ // Fix the value if it is a one-digit number. e.g 1 -> 01 for days
		    	Iday = start.getDayOfMonth();		    	
		    	Sday=String.valueOf(Iday);
		    	Sday = 0+Sday;
		    }
		    if(start.getMonthValue()<10){ // Fix the value if it is a one-digit number. e.g 1 -> 01 for months
		    	Imonth = start.getMonthValue();		    	
		    	Smonth=String.valueOf(Imonth);
		    	Smonth = 0+Smonth;
		    }
		    // Print the results with random -codes/passwords-
		    System.out.print("addSQLinfo(new SQLCodes(\"");		    
		    System.out.print(Sday+"-"+Smonth+"-"+start.getYear());
		    System.out.print("\", \"");	
		    System.out.print(rand.nextInt(1000) + 1);
		    System.out.print("\", \"");	
		    System.out.print(rand.nextInt(1000) + 1);
		    System.out.println("\"));");
		    start = start.plusDays(1);
		}	
	}
}
