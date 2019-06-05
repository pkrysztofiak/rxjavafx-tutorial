package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example001 {

	public static void main(String[] args) {

		System.out.println("Observable");
		Observable<Integer> emissionsSource = Observable.just(1, 2, 3, 4, 5);
		emissionsSource
		.subscribe(number -> System.out.println("number=" + number));

		System.out.println("\nStream");
		Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5);
		numbersStream
		.forEach(number -> System.out.println("number=" + number));
	}
}
