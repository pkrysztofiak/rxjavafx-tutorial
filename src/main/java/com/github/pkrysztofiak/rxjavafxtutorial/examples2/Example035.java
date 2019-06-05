package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.Observable;

public class Example035 {

	public static void main(String[] args) {
		Observable.just("one", "two", "three")
		.subscribe(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next));
	}
}
