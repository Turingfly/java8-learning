package datesStringsLocalization;

import java.util.*;

/**
 * 
 * @author chengfeili 
 * Jun 9, 2017 2:56:03 PM
 *
 *         Internationalization is the process of designing your program so it
 *         can be adapted.
 *         Localization means actually supporting multiple locales.
 */
public class InternationalizationLocalization {

	public void locale() {
		Locale locale = Locale.getDefault();
		System.out.println(locale); // en_US
		Locale l1 = new Locale.Builder().setLanguage("en").setRegion("US").build();
		Locale l2 = new Locale.Builder().setRegion("US").setLanguage("en").build();
	}

	public static void main(String[] args) {
		InternationalizationLocalization ii = new InternationalizationLocalization();
		ii.locale();
	}
}
