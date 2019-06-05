package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import java.util.Arrays;
import java.util.List;

import com.github.pkrysztofiak.rxjavafxtutorial.commons.Client;
import com.github.pkrysztofiak.rxjavafxtutorial.commons.Route;

import io.reactivex.Observable;

public class Solution002 {

	public static void main(String[] args) {

		Route route1 = new Route(
				new Client("Betonix", "Lodz", 100),
				new Client("Allad", "Warsaw", 30),
				new Client("Bicor", "Warsaw", 75),
				new Client("Wiesiex", "Poznan", 10)
				);

		Route route2 = new Route(
				new Client("Betonix", "Lodz", 55),
				new Client("Zzant", "Poznan", 90),
				new Client("Bicor", "Gdansk", 25),
				new Client("AlphaBud", "Poznan", 70)
				);

		List<Route> routes = Arrays.asList(route1, route2);

		Observable.fromIterable(routes)
		.doOnNext(next -> System.out.println("==route=="))
		.flatMap(route ->
			Observable.fromIterable(route.getClients())
			.map(Client::getCity)
			.distinctUntilChanged())
		.subscribe(System.out::println);
	}
}
