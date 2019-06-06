package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import com.github.pkrysztofiak.rxjavafxtutorial.commons.Employee;

import io.reactivex.Observable;

public class Example035 {

	public static void main(String[] args) {
		Employee john = new Employee("John");
		Employee emily = new Employee("Emily");
		Employee alastair = new Employee("Alastair");

		Observable.just(john, emily, alastair)
		.flatMap(employee -> employee.nameObservable())
		.subscribe(System.out::println);

		john.setName("Johnny");
		emily.setName("Em");
		alastair.setName("Al");
	}
}