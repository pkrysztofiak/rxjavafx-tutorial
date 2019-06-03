package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example011 {

	public static void main(String[] args) {
		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.map(String::length)
		.reduce(0, (current, next) -> {
			System.out.println("current=" + current + ", next=" + next);
			return current + next;
		})
		.subscribe(System.out::println);

		System.out.println("====");

		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
		.map(String::length)
		.reduce((current, next) -> {
			System.out.println("current=" + current + ", next=" + next);
			return current + next;
		})
		.subscribe(System.out::println);
	}
}
