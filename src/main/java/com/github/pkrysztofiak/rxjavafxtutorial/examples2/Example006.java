package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.Observable;

public class Example006 {

	public static void main(String[] args) {
		Observable.just("one", "two", "three", "four", "five")
		.distinct(String::length)
		.subscribe(System.out::println);
	}
}