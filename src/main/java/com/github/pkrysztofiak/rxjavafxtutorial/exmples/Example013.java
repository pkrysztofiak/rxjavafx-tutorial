package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.Observable;

public class Example013 {

	public static void main(String[] args) {
		Observable.just("Alpha;Beta;Gamma", "Delta;Epsilon;Zeta;Eta", "Theata;Iota;Kappa;Lambda;Mu")
		.flatMap(letters -> Observable.fromArray(letters.split(";")))
		.subscribe(System.out::println);
	}
}