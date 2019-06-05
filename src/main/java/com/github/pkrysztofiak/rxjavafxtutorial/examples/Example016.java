package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example016 {

	public static void main(String[] args) {
		Observable.just("one", "two", "three")
		.map(String::length)
		.scan(0, (current, next) -> current + next)
		.subscribe(System.out::println);
	}
}