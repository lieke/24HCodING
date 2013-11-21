package nl.ing.cla.model;

public class Chore {
	
	public final static int NEW_STATUS = 0;
	public final static int DONE_STATUS = 1;
	public final static int PAID_STATUS = 2;
	
	String name;	
	double price;
	int status;
	
	public Chore(String name, double price) {
		this.name = name;
		this.price = price;
		this.status = NEW_STATUS;
	}
	
	public void setAsDone() {
		this.status = DONE_STATUS;
	}
	
	public void setAsPaid() {
		this.status = PAID_STATUS;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
