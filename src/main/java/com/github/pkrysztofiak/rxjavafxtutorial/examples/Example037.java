package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Example037 {

	public static void main(String[] args) throws InterruptedException {
		Observable<String> sourceObservable = Observable.just("one", "two", "three", "four", "five")
		.subscribeOn(Schedulers.newThread());
		sourceObservable.subscribe(next -> System.out.println("Obserer1 [" + Thread.currentThread().getName() + "] next=" + next));
		sourceObservable.subscribe(next -> System.out.println("Obserer2 [" + Thread.currentThread().getName() + "] next=" + next));
		TimeUnit.SECONDS.sleep(2);

	}
}