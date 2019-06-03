package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.ResourceObserver;

public class Example003 {

	public static void main(String[] args) {
		Observer<Integer> observer = new ResourceObserver<Integer>() {

			@Override
			public void onNext(Integer next) {
				System.out.println("next=" + next);
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("Completed!");
			}
		};


		Observable<Integer> emissionsSource = Observable.just(1, 2, 3, 4, 5);
		emissionsSource.subscribe(observer);
	}
}