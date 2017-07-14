package datesStringsLocalization;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 
 * @author chengfeili 
 * Jun 9, 2017 2:24:48 PM
 *
 */
public class DatesAndTimes {
	
	public void create() {
		LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
		LocalDate date2 = LocalDate.of(2015, 1, 20);
		Month month = Month.JANUARY;
		// boolean b1 = month == 1; // DOES NOT COMPILE
		boolean b2 = month == Month.APRIL; // false

		LocalTime time1 = LocalTime.of(6, 15);
		LocalTime time2 = LocalTime.of(6, 15, 30);
		LocalTime time3 = LocalTime.of(6, 15, 30, 200);

		LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
		LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
		ZoneId zone = ZoneId.of("US/Eastern");
		ZonedDateTime zoned1 = ZonedDateTime.of(2015, 1, 20, 6, 15, 30, 200, zone);
		ZonedDateTime zoned2 = ZonedDateTime.of(date1, time1, zone);
		ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1, zone);
	}

	public void manipulate() {
		// Date
		LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
		date = date.plusDays(2);
		date = date.plusWeeks(1);
		date = date.plusMonths(1);
		date = date.plusYears(5);
		// Date Time
		LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(5, 15);
		LocalDateTime dateTime = LocalDateTime.of(date1, time).minusDays(1).minusHours(10).minusSeconds(30);
		System.out.println(dateTime); // 2020-01-18T19:14:30
	}

	// For Period, you can specify the number of years, months, weeks or days.
	public void period() {
		Period annually = Period.ofYears(1);
		Period quarterly = Period.ofMonths(3);
		Period everyThreeWeeks = Period.ofWeeks(3);
		Period everyOtherDay = Period.ofDays(2);
		Period everyYearAndAWeek = Period.of(1, 0, 7);
		LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
		date = date.plus(annually);
		System.out.println(date);
		System.out.println(Period.of(0, 20, 47)); // P20M47D
		System.out.println(Period.of(2, 20, 47)); // P2Y20M47D
	}

	// For Duration, you can specify the number of days, hours, minutes,
	// seconds, or nanoseconds.
	public void duration() {
		Duration daily = Duration.of(1, ChronoUnit.DAYS);
		Duration hourly = Duration.of(1, ChronoUnit.HOURS);
		Duration everyMinute = Duration.of(1, ChronoUnit.MINUTES);
		Duration everyTenSeconds = Duration.of(10, ChronoUnit.SECONDS);
		Duration everyMilli = Duration.of(1, ChronoUnit.MILLIS);
		Duration everyNano = Duration.of(1, ChronoUnit.NANOS);

		LocalDate date = LocalDate.of(2015, 5, 25);
		Period period = Period.ofDays(1);
		Duration days = Duration.ofDays(1);
		System.out.println(date.plus(period)); // 2015–05–26
		// System.out.println(date.plus(days)); // Unsupported unit: Seconds
	}

	// The Instant class represents a specific moment in time in the GMT time
	// zone.
	public void instants() {
		Instant now = Instant.now(); // do something time consuming Instant
		Instant later = Instant.now();
		Duration duration = Duration.between(now, later);
		System.out.println(duration.toMillis());

		LocalDate date = LocalDate.of(2015, 5, 25);
		LocalTime time = LocalTime.of(11, 55, 00);
		ZoneId zone = ZoneId.of("US/Eastern");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(date, time, zone);
		Instant instant = zonedDateTime.toInstant();

		Instant nextDay = instant.plus(1, ChronoUnit.DAYS);
		System.out.println(nextDay);
		Instant nextHour = instant.plus(1, ChronoUnit.HOURS);
		System.out.println(nextHour);
		// Instant nextWeek = instant.plus(1, ChronoUnit.WEEKS); // exception
	}

	public static void main(String[] args) {
		DatesAndTimes dt = new DatesAndTimes();
		dt.create();
		dt.manipulate();
		dt.period();
		dt.duration();
		dt.instants();
	}
}
