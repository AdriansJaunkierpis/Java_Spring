package lv.venta.model;

public class Product {
	//TODO Product class - title, description, price, quantity
	private String title;
	private String description;
	private float price;
	private int quantity;
	private long id;
	private static long idCounter = 1;
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public float getPrice() {
		return quantity;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product() {
		setTitle("No");
		setDescription("Nope");
		setPrice(0);
		setQuantity(0);
	}
	public Product(String title, String description, float price, int quantity) {
		setTitle(title);
		setDescription(description);
		setPrice(price);
		setQuantity(quantity);
	}
	
	
}
