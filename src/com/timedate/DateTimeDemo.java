package com.timedate;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeDemo 
{
	private static void testLegacyDateAPI() {
		System.out.println("Date class ... ");
		Date date = new Date();
		System.out.println(date);
		
		System.out.println("\nCalendar class ... ");
		Calendar calendar = new GregorianCalendar();
		System.out.println(calendar.toString());
		
		Calendar calendar1 = new GregorianCalendar(1996,7,21);
		System.out.println(calendar1.getTime());
		calendar1.add(Calendar.MONTH, 8);
		System.out.println(calendar1.getTime());
		
		Calendar calendar2 = new GregorianCalendar(1996,7,21);
		calendar2.roll(Calendar.MONTH, 8);
		System.out.println(calendar2.getTime());
		
		String[] timeZones=TimeZone.getAvailableIDs();
		 
		for(String tz:timeZones)
		{
			Calendar allCal = new GregorianCalendar(TimeZone.getTimeZone(tz));
			System.out.println(tz+" - "+allCal.getTime());
		}
		Calendar londonCal = new GregorianCalendar(TimeZone.getTimeZone("Europe/London"));
		System.out.println("London time -- MONTH/DAY at hr:min:sec (AM/PM) -- " + (londonCal.get(Calendar.MONTH) + 1) + "/" + londonCal.get(Calendar.DAY_OF_MONTH) 
		+ " at " + londonCal.get(Calendar.HOUR) + ":" + londonCal.get(Calendar.MINUTE) + " (" + ((londonCal.get(Calendar.AM_PM) == 0) ? "AM" : "PM") + ")");
		
		
		Calendar indianTime = new GregorianCalendar(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Asian time -- MONTH/DAY at hr:min:sec (AM/PM) -- " + (indianTime.get(Calendar.MONTH) + 1) + "/" + indianTime.get(Calendar.DAY_OF_MONTH)
		+ " at " + indianTime.get(Calendar.HOUR) + ":" + indianTime.get(Calendar.MINUTE) + " (" + ((indianTime.get(Calendar.AM_PM) == 0) ? "AM" : "PM") + ")");
		
	}
	private static void testDateTimeAPI() {

		// Use-case 1: Software renewal		
		LocalDate expiryDate = LocalDate.of(2017, Month.JUNE, 30);
		System.out.println("expiryDate: " + expiryDate);
		LocalDate newExpiryDate = expiryDate.plusMonths(8);
		System.out.println("newExpiryDate: " + newExpiryDate);

		// Other methods: plus & minus methods, isBefore, isAfter
		
		 System.out.println("\nyear: " + newExpiryDate.getYear());
		 System.out.println("month: " + newExpiryDate.getMonth());
		 System.out.println("day of month: " + newExpiryDate.getDayOfMonth()); // returns Month enum 
		 System.out.println("day of week: " + newExpiryDate.getDayOfWeek()); 
		 System.out.println("Leap Year? " +	 newExpiryDate.isLeapYear()); 
		 System.out.println("length of month: " + newExpiryDate.lengthOfMonth());
		 
		 // get returns an int 
		 System.out.println("year again: " + newExpiryDate.get(ChronoField.YEAR)); 
		 System.out.println("month again: " + newExpiryDate.get(ChronoField.MONTH_OF_YEAR));
		 System.out.println("day of month again: " + newExpiryDate.get(ChronoField.DAY_OF_MONTH));
		 
		 // parse string 
		 LocalDate epoch = LocalDate.parse("1970-01-01"); // yyyy-mm-dd 
		 System.out.println("epoch year: " + epoch.getYear());
		 

		// Use-case 2: Game start time localized to time zone

		// LocalTime
		LocalTime time = LocalTime.of(9, 00, 00);
		System.out.println("\ntime -- hr: " + time.getHour());

		// LocalDate
		LocalDate gameStartDate = LocalDate.of(2017, Month.JULY, 03);

		// LocalDateTime
		LocalDateTime gameStartTime = LocalDateTime.of(gameStartDate, time);
		System.out.println("gameStartTime: " + gameStartTime);

		// TimeZone ==> ZoneId
		ZonedDateTime zonedGameStartTime = ZonedDateTime.of(gameStartTime, ZoneId.of("Europe/London"));
		System.out.println("zonedGameStartTime: " + zonedGameStartTime);
		
		ZonedDateTime indiaTime = zonedGameStartTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
		System.out.println("indiaTime: " + indiaTime);
		
		ZonedDateTime pst = zonedGameStartTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
		System.out.println("pst: " + pst);
		
		// Use-Case 3: Age calculation (Period)
		
		 LocalDate birthDay = LocalDate.of(1978, Month.JANUARY, 1); 
		 LocalDate today = LocalDate.now(); // current date from system clock 
		 Period period = birthDay.until(today); 
		 System.out.println("\nComplete Age: " + period.toString()); 
		 System.out.println("Age: " + period.getYears());
		 

		// Use-Case 4: Interval timing (Instant & Duration)
		
		 Instant startTime = Instant.now(); 
		 testLegacyDateAPI(); 
		 Instant endTime = Instant.now(); 
		 Duration timeElapsed =	 Duration.between(startTime, endTime); 
		 System.out.println("timeElapsed in ms: " + timeElapsed.toMillis());
		 

		// Partial Classes
		System.out.println("Christmas: " + MonthDay.of(Month.DECEMBER, 25));
		System.out.println("Credit card expiry date: " + YearMonth.of(2017, Month.JULY));

		// Don't forget to check out the API

	}
	public static void main(String args[])
	{
		
		testDateTimeAPI();
	}
}
