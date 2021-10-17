package com.yammie.yammie.model;

import java.util.Date;


import javax.persistence.*;
@Entity
@Table(name = "ordes")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  long id;
	@Column(name = "adress")
	private String adress;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private double price;
	@Column(name = "name")
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	public Order(String adress, String description, double price, String name,Date date) {
		super();

		setAdress(adress);
		setDescription(description);
		setPrice(price);
		setName(name);
		setDate(date);
		
	}

	public Order() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
