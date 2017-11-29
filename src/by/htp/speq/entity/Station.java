package by.htp.speq.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Station {

	public static String RENT_DAY = getDateToday();
	public static String CHECK_DAY = "30-11-2017";
	public double penaltyForItem;

	 public static void main(String[] arg) {
	
	 System.out.println(getDateToday());
	
	 System.out.println(dateDifferenceInHours(RENT_DAY, CHECK_DAY + " 16:31:22"));
	
	 }

	public Station() {
		super();
	}

	public static String getRENT_DAY() {
		return RENT_DAY;
	}

	public static void setRENT_DAY(String rENT_DAY) {
		RENT_DAY = rENT_DAY;
	}

	public static String getCHECK_DAY() {
		return CHECK_DAY;
	}

	public static void setCHECK_DAY(String cHECK_DAY) {
		CHECK_DAY = cHECK_DAY;
	}

	static String getDateToday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	

	static int dateDifferenceInHours(String rent_day, String check_day) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(rent_day);
			date2 = sdf.parse(check_day);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int differenceInHours = (int) ((date2.getTime() - date1.getTime()) / (60 * 60 * 1000));

		return differenceInHours;
	}

}
