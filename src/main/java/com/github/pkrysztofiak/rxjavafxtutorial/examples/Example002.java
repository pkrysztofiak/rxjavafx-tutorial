package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example002 {

	public static void main(String[] args) {

		Observable<String> lettersObservable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

		System.out.println("Observable");
		lettersObservable
		.map(letter -> letter.length())
		.subscribe(length -> System.out.println("length=" + length));

		Stream<String> lettersStream = Stream.of("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

		System.out.println("\nStream");
		lettersStream
		.map(letter -> letter.length())
		.forEach(length -> System.out.println("length=" + length));
	}
}
