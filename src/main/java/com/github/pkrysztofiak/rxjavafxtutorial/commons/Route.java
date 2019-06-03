package com.github.pkrysztofiak.rxjavafxtutorial.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Route {

	private final List<Address> addresses = new ArrayList<>();

	public Route(Address... addresses) {
		this.addresses.addAll(Arrays.asList(addresses));
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	@Override
	public String toString() {
		return "Route" + addresses + "]";
	}
}