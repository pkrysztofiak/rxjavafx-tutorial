package com.github.pkrysztofiak.rxjavafxtutorial.commons;

import java.util.ArrayList;
import java.util.List;

public class Rep {

	private final List<Route> routes = new ArrayList<>();

	private final Address companyAddress = new Address("Łódź", "Piękna 1");

	public Rep() {
		Route route1 = new Route(
				companyAddress,
				new Address("Zgierz", "Parzęczewska 35"),
				new Address("Turek", "Mickiewicza 1"),
				new Address("Turek", "Żeromskiego 22"),
				new Address("Konin", "Przemysłowa 1"),
				companyAddress
				);

		Route route2 = new Route(
				companyAddress,
				new Address("Pabianice", "Popławska 4"),
				new Address("Pabianice", "Kresowa 41"),
				new Address("Pabianice", "Modrzewiowa 2"),
				new Address("Łask", "Tylna 4"),
				companyAddress
				);

		routes.add(route1);
		routes.add(route2);
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public Address getCompanyAddress() {
		return companyAddress;
	}
}