package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example005 {

	public static void main(String[] args) {
		System.out.println("Observable");
		Observable.just("one", "two", "three", "four", "five")
		.map(String::length)
		.distinct()
		.subscribe(System.out::println);

		System.out.println("\nStream");
		Stream.of("one", "two", "three", "four", "five")
		.map(String::length)
		.distinct()
		.forEach(System.out::println);
	}
}