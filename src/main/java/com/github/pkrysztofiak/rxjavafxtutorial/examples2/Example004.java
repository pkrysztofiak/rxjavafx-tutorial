package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example004 {

	public static void main(String[] args) {

		System.out.println("Observable");
		Observable.just("one", "two", "three", "four", "five")
		.filter(next -> next.length() > 3)
		.subscribe(next -> System.out.println("next=" + next));

		System.out.println("\nStream");
		Stream.of("one", "two", "three", "four", "five")
		.filter(next -> next.length() > 3)
		.forEach(next -> System.out.println("next=" + next));
	}
}
