package com.github.pkrysztofiak.rxjavafxtutorial.commons;

public class Address {

	private final String city;
	private final String street;

	public Address(String city, String street) {
		this.city = city;
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	@Override
	public String toString() {
		return "[" + city + ", " + street + "]";
	}
}