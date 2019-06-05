package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.Observable;

public class Example034 {

	public static void main(String[] args) {
		Observable<String> source1 = Observable.just("two", "three");

		source1.startWith("one")
		.map(String::length)
		.toList()
		.subscribe(System.out::println);
	}
}