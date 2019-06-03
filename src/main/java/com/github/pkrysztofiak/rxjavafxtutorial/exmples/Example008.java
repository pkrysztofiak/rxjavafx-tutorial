package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example008 {

	public static void main(String[] args) {

		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.take(3)
		.subscribe(System.out::println);

		System.out.println("====");

		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta")
		.takeWhile(letter -> letter.endsWith("a"))
		.subscribe(System.out::println);

		System.out.println("====");

		Observable.just("Epsilon", "Zeta", "Eta")
		.takeWhile(letter -> letter.endsWith("a"))
		.subscribe(System.out::println);

		System.out.println("====");

		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.takeUntil((String letter) -> letter.endsWith("lta"))
		.subscribe(System.out::println);
	}
}