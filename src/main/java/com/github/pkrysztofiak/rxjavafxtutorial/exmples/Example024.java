package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Example024 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ObservableList<String> numbers = FXCollections.observableArrayList();
		JavaFxObservable.additionsOf(numbers)
		.subscribe(System.out::println);
		numbers.add("one");
		numbers.addAll("two", "three");
	}
}