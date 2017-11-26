package by.htp.speq.entity;

public class Equipment {
	private String title;
	private String category;
	
	public Equipment() {
		super();
	}

	public Equipment(String title) {
		super();
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Equipment [title=" + title + ", category=" + category + "]";
	}

}
