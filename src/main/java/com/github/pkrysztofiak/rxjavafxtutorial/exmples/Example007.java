package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example007 {

	public static void main(String[] args) {
		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.map(String::length)
		.distinct()
		.subscribe(System.out::println);

		System.out.println();

		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.distinct(String::length)
		.subscribe(System.out::println);

		System.out.println();

		Observable.just("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
		.distinctUntilChanged(String::length)
		.subscribe(System.out::println);
	}
}
