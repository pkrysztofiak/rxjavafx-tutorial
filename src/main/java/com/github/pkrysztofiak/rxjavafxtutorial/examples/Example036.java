package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import com.github.pkrysztofiak.rxjavafxtutorial.commons.Employee;

import io.reactivex.Observable;
import javafx.application.Application;
import javafx.stage.Stage;

public class Example036 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Employee john = new Employee("John");
		Employee emily = new Employee("Emily");
		Employee alastair = new Employee("Alastair");

		Observable.just(john, emily, alastair)
		.switchMap(employee -> employee.nameObservable())
		.subscribe(System.out::println);

		john.setName("Johnny");
		emily.setName("Em");
		alastair.setName("Al");

		john.setName("Johnny the First");
		emily.setName("Em the Greatest");
		alastair.setName("Al the Looser");
	}
}
