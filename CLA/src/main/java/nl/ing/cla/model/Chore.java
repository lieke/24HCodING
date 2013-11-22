package nl.ing.cla.model;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Chore {
	
	public final static int NEW_STATUS = 0;
	public final static int DONE_STATUS = 1;
	public final static int PAID_STATUS = 2;
	
	
	long id;
	String name;	
	double price;
	int status;		
	Date date = new Date();
	
	public Chore() {
		
	}
	public Chore(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.status = NEW_STATUS;
		this.date = new Date();
	}
	
	@JsonIgnore	
	public void setDate(Date date) {
		this.date = date;
	}
	@JsonSerialize(using=JsonDateSerializer.class)	
	public Date getDate() {
		return date;
	}
	@JsonIgnore
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
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
