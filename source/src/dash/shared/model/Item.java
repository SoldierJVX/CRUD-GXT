package dash.shared.model;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{

	private int id;
	private String description;
	private int amount;
	private Date dateCreated;
	private Date dateUpdated;
	
	public Item() {
	}

	public Item(int id, String description, int amount, Date dateCreated, Date dateUpdated) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

}
