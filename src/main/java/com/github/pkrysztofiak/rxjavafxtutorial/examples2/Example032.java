package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.Observable;

public class Example032 {

	public static void main(String[] args) {
		Observable<String> source1 = Observable.just("one", "two");
		Observable<String> source2 = Observable.just("three", "four", "five");

		source1.concatWith(source2)
		.map(String::length)
		.toList()
		.subscribe(System.out::println);
	}
}