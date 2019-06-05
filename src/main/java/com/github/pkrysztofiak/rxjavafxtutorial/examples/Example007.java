package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example007 {

	public static void main(String[] args) {
		Observable.just("one", "one", "two", "one", "seven", "eight", "eight", "nine")
		.distinctUntilChanged()
		.subscribe(System.out::println);
	}
}
