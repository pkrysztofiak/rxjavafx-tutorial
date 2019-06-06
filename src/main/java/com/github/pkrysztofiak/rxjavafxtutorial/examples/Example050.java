package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example050 {

	public static void main(String[] args) {
		Observable.just("one", "two", "three")
		.subscribe(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next));
	}
}
