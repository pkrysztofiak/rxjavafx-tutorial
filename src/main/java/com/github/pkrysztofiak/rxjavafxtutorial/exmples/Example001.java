package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import java.util.stream.Stream;

import io.reactivex.Observable;

public class Example001 {

	public static void main(String[] args) {
		Observable<Integer> emissionsSource = Observable.just(1, 2, 3, 4, 5);
		emissionsSource.subscribe(emission -> System.out.println("emission=" + emission));

		Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5);
		numbersStream.forEach(next -> System.out.println("next=" + next));
	}
}