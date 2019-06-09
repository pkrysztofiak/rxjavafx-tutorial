package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Example050 {

	public static void main(String[] args) throws InterruptedException {
		Observable.just("one", "two", "three")
		.subscribe(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next));
		TimeUnit.SECONDS.sleep(4);
	}
}
