package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import com.github.pkrysztofiak.rxjavafxtutorial.commons.Rep;

import io.reactivex.Observable;

public class Solution1 {

	public static void main(String[] args) {
		Rep john = new Rep();

		Observable.fromIterable(john.getRoutes())
		.doOnNext(route -> System.out.println("==route=="))
		.flatMap(route ->
			Observable.fromIterable(route.getAddresses())
			.filter(address -> !address.equals(john.getCompanyAddress()))
			.distinctUntilChanged(address -> address.getCity())
			.map(address -> address.getCity()))
		.subscribe(System.out::println);
	}
}