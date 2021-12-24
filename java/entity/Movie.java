package entity;

public class Movie {
	private String id;
	private String title;
	private String category;
	
	public Movie() {
		
	}
	public Movie(String id, String title, String category) {
		this.id = id;
		this.title = title;
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getCategory() {
		return category;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
