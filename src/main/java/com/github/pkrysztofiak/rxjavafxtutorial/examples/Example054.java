package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Example054 {

	public static void main(String[] args) throws InterruptedException {
		Observable.just("one", "two", "three")
		.subscribeOn(Schedulers.computation())
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.observeOn(Schedulers.io())
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.observeOn(Schedulers.computation())
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.subscribe(next -> System.out.println("Observer[" + Thread.currentThread().getName() + "] next=" + next));
		TimeUnit.SECONDS.sleep(4);
	}
}