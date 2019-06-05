package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example030 {

	public static void main(String[] args) {
		Observable.just("one", "two", "three")
		.subscribe(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next));
	}
}
