package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Example063 {

	public static void main(String[] args) throws InterruptedException {
		Observable.interval(300, TimeUnit.MILLISECONDS)
		.buffer(1, TimeUnit.SECONDS)
		.subscribe(System.out::println);

		TimeUnit.SECONDS.sleep(10);
	}
}
