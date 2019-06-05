package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example020 {

	public static void main(String[] args) {
		Observable<String> source = Observable.just("one", "two", "three", "four", "five");
		source.subscribe(next -> System.out.println("[Observer1] next=" + next));
		source.subscribe(next -> System.out.println("[Observer2] next=" + next));
	}
}
