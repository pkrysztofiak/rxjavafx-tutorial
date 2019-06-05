package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class Example013 {

	public static void main(String[] args) {
		Observable.<String>empty()
		.map(String::length)
		.reduce(0, (current, next) -> current + next)
		.subscribe(new SingleObserver<Integer>() {

			@Override
			public void onSubscribe(Disposable disposable) {

			}

			@Override
			public void onSuccess(Integer single) {
				System.out.println("sum=" + single);
			}

			@Override
			public void onError(Throwable throwable) {

			}
		});
	}
}