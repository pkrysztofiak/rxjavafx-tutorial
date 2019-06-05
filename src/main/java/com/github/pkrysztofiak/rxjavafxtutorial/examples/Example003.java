package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example003 {

	public static void main(String[] args) {

		System.out.println("Observable");

		Observable<String> numbersObservable = Observable.just("one", "two", "three");
		numbersObservable
		.doOnNext(next -> System.out.println("next=" + next))
		.map(String::length)
		.subscribe(length -> System.out.println("length=" + length));

		System.out.println("\nStream");

		Stream<String> numbersStream = Stream.of("one", "two", "three");
		numbersStream
		.peek(next -> System.out.println("next=" + next))
		.map(String::length)
		.forEach(length -> System.out.println("length=" + length));
	}
}