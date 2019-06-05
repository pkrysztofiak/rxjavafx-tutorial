package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Example015 {

	public static void main(String[] args) {
		Observable.<String>empty()
		.map(String::length)
		.reduce((current, next) -> current + next)
		.subscribe(new MaybeObserver<Integer>() {

			@Override
			public void onSubscribe(Disposable disposable) {
			}

			@Override
			public void onSuccess(Integer single) {
				System.out.println("sum=" + single);
			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onComplete() {
				System.out.println("completed!");
			}
		});
	}
}
