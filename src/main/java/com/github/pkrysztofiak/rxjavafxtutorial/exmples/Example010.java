package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example010 {

	public static void main(String[] args) {
		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.toList()
		.subscribe(System.out::println);
	}
}