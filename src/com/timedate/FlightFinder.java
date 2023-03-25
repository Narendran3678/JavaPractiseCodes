package com.timedate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class FlightFinder {

	private Map<String, List<Flight>> allFlights = new HashMap<>();

	public FlightFinder(Map<String, List<Flight>> allFlights) {
		this.allFlights = allFlights;
	}

	public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year, 
	        int preferredDepartureStartHour, int preferredDepartureEndHour, 
	        String departureCity, String arrivalCity, String finalArrivalCity,
			String departureCityTimeZone, String arrivalCityTimeZone) {
		
		List<NavigableSet<Flight>> result = new ArrayList<>();
        
        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart
		LocalDate departDate = LocalDate.of(year, month, dayOfMonth);
		LocalTime departTime = LocalTime.of(preferredDepartureStartHour, 00);
		LocalDateTime departDateTime = LocalDateTime.of(departDate, departTime);
		ZonedDateTime departTZ = ZonedDateTime.of(departDateTime, ZoneId.of(departureCityTimeZone));
		
		LocalDate arrivalDate = LocalDate.of(year, month, dayOfMonth);
		LocalTime arrivalTime = LocalTime.of(preferredDepartureEndHour, 00);
		LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);
		ZonedDateTime arrivalTZ = ZonedDateTime.of(arrivalDateTime, ZoneId.of(arrivalCityTimeZone));
		
		//System.out.println("DepartTime..."+departTZ);
		//System.out.println("ArrivalTime..."+arrivalTZ);
		// Your code

        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);
        NavigableSet<Flight> departingflights = new TreeSet<>();
        //System.out.println();
        // Your code
        // Tip: Methods like isAfter can be used to find flights in the specified user time interval
     
        for(Flight flight : allDepartingFlights)
        {
        	ZonedDateTime currentFlightZ =ZonedDateTime.of(flight.getDepartureTime(), ZoneId.of(departureCityTimeZone));
        	
        	if(currentFlightZ.isAfter(departTZ))
        	{
        		departingflights.add(flight);
        	}
        }
        departingflights.forEach(System.out::println);
        
      
           
        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set
        // Your code  
        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);        
        
        NavigableSet<Flight> connectingflights = new TreeSet<>();   
        for(Flight depFlight : departingflights)
        {
    	  for(Flight destFlight : allConnectingFlights)
    	  { 		  
    		  if(destFlight.getDepartureTime().isAfter(depFlight.getArrivalTime().plusHours(2)))
    		  {
   				  connectingflights.add(destFlight);
    		  }
    	  }
        }
        connectingflights.forEach(System.out::println);
        
        result.add(departingflights);
        result.add(connectingflights);
        
        return result;
	}
	
	public static void main(String[] args) {
	    Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
			LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Rome",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 8, 0), LocalDateTime.of(2017, 12, 20, 8, 0));
		Flight f5 = new Flight(5, "5", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 9, 30), LocalDateTime.of(2017, 12, 20, 9, 30));
		Flight f6 = new Flight(6, "6", "Indigo", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 11, 30), LocalDateTime.of(2017, 12, 20, 11, 30));
		Flight f7 = new Flight(7, "7", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 12, 30), LocalDateTime.of(2017, 12, 20, 12, 30));
		Flight f8 = new Flight(8, "8", "Indigo", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 4, 0), LocalDateTime.of(2017, 12, 20, 4, 0));
		Flight f9 = new Flight(9, "9", "Emirates", "Paris", "New Delhi",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f10 = new Flight(10, "10", "Indigo", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20, 9, 30), LocalDateTime.of(2017, 12, 20, 9, 30));
		Flight f11 = new Flight(11, "11", "Delta", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20,10, 30), LocalDateTime.of(2017, 12, 20, 10, 30));
		Flight f12 = new Flight(12, "12", "Delta", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20,12, 00), LocalDateTime.of(2017, 12, 20, 12, 00));
		Flight f13 = new Flight(13, "13", "Delta", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20,14, 00), LocalDateTime.of(2017, 12, 20, 14, 00));
		Flight f14 = new Flight(13, "13", "Delta", "Dubai", "Mumbai",
				LocalDateTime.of(2017, 12, 20,14, 00), LocalDateTime.of(2017, 12, 20, 14, 00));

		Map<String, List<Flight>> allFlights = new HashMap<>();
		
		allFlights.put("New York", Arrays.asList(f1));
		allFlights.put("San Francisco", Arrays.asList(f2,f3,f4,f5,f6,f7,f8));
		allFlights.put("Paris", Arrays.asList(f9));
		allFlights.put("Dubai", Arrays.asList(f10,f11,f12,f13));
		
		List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");
		 
	}

}