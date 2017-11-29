package by.htp.speq.station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import by.htp.speq.entity.RentUnit;

public class RentedCatalog extends Catalog {

	private String takenInRentDate = getDateToday();
	
	public double penaltyForItem;

//	 public static void main(String[] arg) {
//	
//	 System.out.println(getDateToday());
//	
//	 System.out.println(dateDifferenceInHours(takenInRentDate, CHECK_DAY + " 16:31:22"));
//	
//	 }
	
	public String getTakenInRentDate() {
		return takenInRentDate;
	}

	public void setTakenInRentDate(String renter) {
		this.takenInRentDate = takenInRentDate;
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
	
	@Override
	public String toString() {
		return "getUnits()=" + getUnits() + ", takenInRentDate=" + takenInRentDate + "]";
	}
	

}
