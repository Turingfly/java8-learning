package datesStringsLocalization;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * 
 * @author chengfeili 
 * Jun 9, 2017 3:49:38 PM
 *
 *         Once you have the NumberFormat instance, you can call format() to
 *         turn a number into a String and parse() to turn a String into a
 *         number.
 * 
 *         The format classes are not thread-safe. Do not store them in instance
 *         variables or static variables.
 */
public class FormattingNumbers {

	public void test() throws ParseException {
		int attendeesPerYear = 3_200_000;
		int attendeesPerMonth = attendeesPerYear / 12;
		NumberFormat us = NumberFormat.getInstance(Locale.US);
		System.out.println(us.format(attendeesPerMonth)); // 266,666
		NumberFormat g = NumberFormat.getInstance(Locale.GERMANY);
		System.out.println(g.format(attendeesPerMonth)); // 266.666
		NumberFormat ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
		System.out.println(ca.format(attendeesPerMonth)); // 266 666

		double price = 48;
		NumberFormat usa = NumberFormat.getCurrencyInstance();
		System.out.println(usa.format(price)); // $48.00

		NumberFormat en = NumberFormat.getInstance(Locale.US);
		NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);
		String s = "40.45";
		System.out.println(en.parse(s)); // 40.45
		System.out.println(fr.parse(s)); // 40

		NumberFormat nf = NumberFormat.getInstance();
		String one = "456abc";
		String two = "-2.5165x10";
		String three = "x85.3";
		System.out.println(nf.parse(one)); // 456
		System.out.println(nf.parse(two)); // -2.5165
		// System.out.println(nf.parse(three)); // throws ParseException
	}

	public static void main(String[] args) throws ParseException {
		FormattingNumbers fm = new FormattingNumbers();
		fm.test();
	}
}
