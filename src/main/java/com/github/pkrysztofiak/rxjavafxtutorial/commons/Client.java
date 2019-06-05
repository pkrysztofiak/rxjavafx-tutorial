package com.github.pkrysztofiak.rxjavafxtutorial.commons;

public class Client {

	private final String name;
	private final String city;
	private final int itemsSold;

	public Client(String name, String city, int itemsSold) {
		this.name = name;
		this.city = city;
		this.itemsSold = itemsSold;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public int getItemsSold() {
		return itemsSold;
	}
}