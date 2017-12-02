package by.htp.speq.entity;

public class Accessorie extends Equipment {
	
	private String belongTo;

	public Accessorie() {
		super();
	}
	
	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	@Override
	public String toString() {
		return "type=" + super.getType() + ", title=" + super.getTitle() + ", category=" + super.getCategory() + ", belongTo=" + belongTo;
	}
	
	

}
