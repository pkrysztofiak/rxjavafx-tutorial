package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example033 {

	public static void main(String[] args) {
		Observable<String> source1 = Observable.just("one", "two");
		Observable<String> source2 = Observable.just("three", "four", "five");

		source2.startWith(source1)
		.map(String::length)
		.toList()
		.subscribe(System.out::println);
	}
}