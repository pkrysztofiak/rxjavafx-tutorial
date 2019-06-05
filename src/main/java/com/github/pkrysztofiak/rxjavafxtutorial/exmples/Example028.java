package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example028 {

	public static void main(String[] args) {
		Observable<String> source1 = Observable.just("one", "two");
		Observable<String> source2 = Observable.just("three", "four", "five");

		source2.startWith(source1)
		.map(String::length)
		.toList()
		.subscribe(System.out::println);
	}
}