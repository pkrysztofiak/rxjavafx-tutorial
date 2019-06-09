package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Example059 {

	private static final Random random = new Random();

	public static void main(String[] args) throws InterruptedException {
		Observable.just("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
		.doOnNext(next -> System.out.println("[" + Thread.currentThread().getName() + "] next=" + next))
		.flatMap(number -> Observable.just(number).subscribeOn(Schedulers.computation()).map(Example059::process))
		.subscribe(next -> System.out.println("Observer [" + Thread.currentThread().getName() + "] next=" + next));
		TimeUnit.SECONDS.sleep(30);
	}

	private static String process(String number) throws InterruptedException {
		int randomInt = random.nextInt(10) + 1;
		System.out.println("[" + Thread.currentThread().getName() + "] number=" + number + " will be processing for " + randomInt + " seconds");
		TimeUnit.SECONDS.sleep(randomInt);
		number = number + "!";
		System.out.println("[" + Thread.currentThread().getName() + "] number processed=" + number);
		return number;
	}
}