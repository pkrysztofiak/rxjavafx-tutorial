package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;

public class Example012 {

	public static void main(String[] args) {
		System.out.println("Observable");
		Observable.just("one", "two", "three", "four", "five")
		.toList()
		.subscribe(
				list -> System.out.println("onSucces=" + list), //onSuccess
				throwable -> throwable.printStackTrace()		//onError
				);
	}
}
