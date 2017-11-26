package by.htp.speq.entity;

public class RentUnit {
	private Equipment equipment;
	private double hourRate;
	private Accessorie accessorie;
	private String backDate;
	
	public RentUnit() {
	
	}
	
	public RentUnit(Equipment equipment, double hourRate) {
		this.equipment = equipment;
		this.hourRate = hourRate;
	}

	public RentUnit(Accessorie accessorie, double hourRate) {
		this.accessorie = accessorie;
		this.hourRate = hourRate;
	}
	
	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public double getHourRate() {
		return hourRate;
	}

	public void setHourRate(double hourRate) {
		this.hourRate = hourRate;
	}

	@Override
	public String toString() {
		return "RentUnit [equipment=" + equipment + ", hourRate=" + hourRate + ", backDate=" + backDate + "]";
	}

	
}
