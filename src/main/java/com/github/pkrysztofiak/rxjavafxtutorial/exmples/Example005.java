package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example005 {

	public static void main(String[] args) {
		System.out.println("==observable==");

		Observable<String> lettersObservable = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

		lettersObservable
		.map(letter -> letter.length())
		.subscribe(length -> System.out.println("letter.length=" + length));

		lettersObservable
		.doOnNext(letter -> System.out.println("letter=" + letter))
		.map(letter -> letter.length())
		.doOnNext(length -> System.out.println("length=" + length))
		.subscribe(length -> System.out.println("letter.length=" + length));

		System.out.println("\n==stream==");

		Stream<String> lettersStream = Stream.of("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

		lettersStream
		.map(letter -> letter.length())
		.forEach(length -> System.out.println("letter.length=" + length));
	}
}