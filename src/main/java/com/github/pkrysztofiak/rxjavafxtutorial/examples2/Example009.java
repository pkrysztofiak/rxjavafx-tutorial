package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.Observable;

public class Example009 {

	public static void main(String[] args) {
		System.out.println("Observable");
		Observable.just("one", "two", "three", "four", "five")
		.takeWhile(number -> number.length() == 3)
		.subscribe(
				next -> System.out.println("next=" + next), //onNext
				throwable -> throwable.printStackTrace(),	//onError
				() -> System.out.println("completed!")		//onComplete
				);
	}
}
