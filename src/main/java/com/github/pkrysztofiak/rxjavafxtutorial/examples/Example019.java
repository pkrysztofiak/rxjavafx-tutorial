package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Example019 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Button button = new Button("Click me!");
		stage.setScene(new Scene(button));
		stage.show();

		JavaFxObservable.actionEventsOf(button).subscribe(actionEvent -> {
			System.out.println(actionEvent.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(actionEvent)));
		});
		button.setOnAction(actionEvent -> {
			System.out.println(actionEvent.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(actionEvent)));
		});
	}
}