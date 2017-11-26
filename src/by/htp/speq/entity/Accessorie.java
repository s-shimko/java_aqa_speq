package by.htp.speq.entity;

public class Accessorie extends Equipment {
	
	private String belongTo;

	public Accessorie() {
		super();
	}
	
	public Accessorie(String belongTo) {
		super();
		this.belongTo = belongTo;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}
	

}
