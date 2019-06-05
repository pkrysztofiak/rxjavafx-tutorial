package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.Observable;

public class Example017 {

	public static void main(String[] args) {
		Observable.just("1;2;3", "1;1", "4;4;1;2")
		.flatMap(numbers -> Observable.fromArray(numbers.split(";")))
		.map(Integer::valueOf)
		.reduce((current, next) -> current + next)
		.subscribe(System.out::println);
	}
}