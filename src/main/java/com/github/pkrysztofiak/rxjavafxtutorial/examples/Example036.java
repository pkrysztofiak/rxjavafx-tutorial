package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Example036 {

	public static void main(String[] args) throws InterruptedException {
		Observable.just("one", "two", "three")
		.subscribeOn(Schedulers.newThread())
		.subscribe(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next));
		TimeUnit.SECONDS.sleep(1);
	}
}