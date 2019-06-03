package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example002 {

	public static void main(String[] args) {
		Observable<Integer> emissionsSource = Observable.just(1, 2, 3, 4, 5);
		emissionsSource.subscribe(
				next -> System.out.println("next=" + next), //onNext
				throwable -> throwable.printStackTrace(),   //onError
				() -> System.out.println("Completed!")      //onComplete
				);
	}
}
