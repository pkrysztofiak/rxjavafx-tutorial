package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example006 {

	public static void main(String[] args) {
		System.out.println("==observable==");

		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.filter(letter -> letter.length() >= 5)
		.subscribe(letter -> System.out.println("letter=" + letter));


		System.out.println("\n==stream==");

		Stream.of("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.filter(letter -> letter.length() >= 5)
		.forEach(letter -> System.out.println("letter=" + letter));
	}
}