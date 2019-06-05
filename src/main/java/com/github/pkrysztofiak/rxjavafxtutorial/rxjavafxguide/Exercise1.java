package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Exercise1 {


	public static void main(String[] args) {
		ConnectableObservable<String> source = Observable.just("one", "two", "three", "four", "five").publish();

		source.subscribe(next -> System.out.println("[Observer1] next=" + next));
		source.subscribe(next -> System.out.println("[Observer2] next=" + next));

		source.connect();
	}
}
