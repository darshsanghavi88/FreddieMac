package com.FM.api.property.entity;

public class Property {
	Address address;
	String home_id;
	String owner;
	int value;
	
	public Property() {
	}
	
	public Property(Address address, String home_id, String owner, int value) {
	
		this.address = address;
		this.home_id = home_id;
		this.owner = owner;
		this.value = value;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getHome_id() {
		return home_id;
	}
	public void setHome_id(String home_id) {
		this.home_id = home_id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
