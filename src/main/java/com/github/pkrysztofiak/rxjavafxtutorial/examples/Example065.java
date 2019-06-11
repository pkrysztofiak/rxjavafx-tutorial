package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Example065 {

	public static void main(String[] args) throws InterruptedException {
		Observable<String> source = Observable
				.concat(Observable
								.interval(100, TimeUnit.MILLISECONDS)
								.take(10)
								.map(i -> "A" + i),
						Observable
								.interval(2, TimeUnit.SECONDS)
								.take(3)
								.map(i -> "B" + i),
						Observable
								.interval(500, TimeUnit.MILLISECONDS)
								.take(4)
								.map(i -> "C" + i));
		source.debounce(1, TimeUnit.SECONDS).subscribe(System.out::println);

		TimeUnit.SECONDS.sleep(20);
	}

}
