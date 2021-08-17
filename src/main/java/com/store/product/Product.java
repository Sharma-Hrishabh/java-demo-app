package com.store.product;
public class Product {
	//productid, productname, description, price
	private int productId;
	private String productName;
	private String desc;
	private int price;
	
	public int getId() {
		return productId; 
	}
	public void setId(int id) {
		this.productId=id;
	}
	public String getName() {
		return productName;
	}
	public void setName(String name) {
		this.productName=name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc=desc;
	}
	public int getPrice() {
		return price; 
	}
	public void setPrice(int price) {
		this.price=price;
	}
}
