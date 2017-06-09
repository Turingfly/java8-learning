package datesStringsLocalization;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * 
 * @author chengfeili 
 * Jun 9, 2017 3:57:38 PM
 *
 */
public class FormattingDatesAndTimes {

	public void test() {
		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(11, 12, 34);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2020-01-20
		System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME)); // 11:12:34
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2020-01-20T11:12:34

		DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		System.out.println(shortDateTime.format(dateTime)); // 1/20/20
		System.out.println(shortDateTime.format(date)); // 1/20/20
		// System.out.println(shortDateTime.format(time)); //
		// UnsupportedTemporalTypeException

		// short and medium
		DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		System.out.println(shortF.format(dateTime)); // 1/20/20 11:12 AM
		System.out.println(mediumF.format(dateTime)); // Jan 20, 2020 11:12:34AM

		// Customize your own formatter
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
		System.out.println(dateTime.format(f)); // January 20, 2020, 11:12

		// String to Datetime
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate date1 = LocalDate.parse("01 02 2015", fm);
		LocalTime time1 = LocalTime.parse("11:22");
		System.out.println(date1); // 2017–06–09 
		System.out.println(time1); // 11:22
	}

	public static void main(String[] args) {
		FormattingDatesAndTimes fdt = new FormattingDatesAndTimes();
		fdt.test();
	}
}
