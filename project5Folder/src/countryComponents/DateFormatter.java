package countryComponents;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class DateFormatter implements Serializable {
	/**
	 * Provides a formatter for changing String objects of the format dd/MM/yyyyy into Date objects
	 */
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyyy");
}
