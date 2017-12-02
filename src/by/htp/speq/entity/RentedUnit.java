package by.htp.speq.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentedUnit extends RentUnit {

	private static final int HOURS_RENT_LIMIT = 72;
	private static final double PERCENT_PENALTY = 0.1;
	public static final String CHECK_DATE = "06-12-2017 20:57:51";

	private String takenInRentDate;
	public double penaltyForItem = 0;

	public RentedUnit() {
		super();
	}

	public RentedUnit(String takenInRentDate) {
		super();
		this.takenInRentDate = takenInRentDate;
	}

	public String getTakenInRentDate() {
		return takenInRentDate;
	}

	public void setTakenInRentDate(String takenInRentDate) {
		this.takenInRentDate = takenInRentDate;
	}

	public static double calculatePenalty(String rentDate, String checkDate, Double price) {
		int hoursInRent = dateDifferenceInHours(rentDate, checkDate);

		double penalty = 0.0;
		
		int penaltyHours = hoursInRent - HOURS_RENT_LIMIT;
		if (penaltyHours > 0) {
			penalty = price * PERCENT_PENALTY * penaltyHours;
		}
		return penalty;
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
		penaltyForItem = calculatePenalty(takenInRentDate, CHECK_DATE, super.getHourRate());
		return "RentedUnit [" + super.getEquipment() + ", hourRate=" + super.getHourRate() + ", takenInRentDate="
				+ takenInRentDate + ", penaltyForItem=" + penaltyForItem + "]";
	}

}
