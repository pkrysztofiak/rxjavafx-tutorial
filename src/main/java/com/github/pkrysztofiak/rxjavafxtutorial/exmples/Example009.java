package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example009 {

	public static void main(String[] args) {

		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.count()
		.subscribe(System.out::println);
	}
}