package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Solution004 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Button button = new Button("+");
		Label label = new Label();
		HBox hBox = new HBox(button, label);
		stage.setScene(new Scene(hBox));
		stage.show();

		JavaFxObservable.actionEventsOf(button)
		.map(actionEvent -> 1)
		.scan((current, next) -> current + next)
		.map(String::valueOf)
		.subscribe(label::setText);
	}
}