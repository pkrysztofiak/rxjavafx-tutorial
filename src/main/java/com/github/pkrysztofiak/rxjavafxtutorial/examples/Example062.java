package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example062 {

	public static void main(String[] args) {
		Observable.range(0, 100)
		.buffer(10)
		.subscribe(System.out::println);
	}
}