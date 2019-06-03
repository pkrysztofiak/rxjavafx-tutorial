package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example012 {

	public static void main(String[] args) {
		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.map(String::length)
		.scan(0, (current, next) -> current + next)
		.subscribe(System.out::println);
	}
}