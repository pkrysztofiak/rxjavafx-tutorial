package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Observable;

public class Example004 {

	public static void main(String[] args) {
		Observable<Integer> rangeObservable = Observable.range(1, 5);
		Observable<Integer> fromIterableObservable = Observable.fromIterable(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)));
	}
}
