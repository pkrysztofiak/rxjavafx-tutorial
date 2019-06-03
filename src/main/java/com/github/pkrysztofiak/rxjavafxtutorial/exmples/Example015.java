package com.github.pkrysztofiak.rxjavafxtutorial.exmples;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Example015 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Button button = new Button("Click me!");
		VBox vBox = new VBox(button);
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.show();

		JavaFxObservable.actionEventsOf(button)
		.subscribe(event -> System.out.println("observable \t event=" + event));

		button.setOnAction(event -> System.out.println("handler \t event=" + event));
	}
}