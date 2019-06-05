package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example011 {

	public static void main(String[] args) {
		System.out.println("Observable");
		Observable.just("one", "two", "three", "four", "five")
		.count()
		.subscribe(
				result -> System.out.println("onSucces=" + result), //onSuccess
				throwable -> throwable.printStackTrace()			//onError
				);
	}
}
