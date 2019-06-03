package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example014 {

	public static void main(String[] args) {
		Observable.just("Alpha;Beta;Gamma", "Delta;Epsilon;Zeta;Eta", "Theata;Iota;Kappa;Lambda;Mu")
		.flatMapSingle(letters ->
			Observable.fromArray(letters.split(";"))
			.map(String::length)
			.reduce(0, (current, next) -> current + next))
		.subscribe(System.out::println);
	}
}