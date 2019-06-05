package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise3 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		TextField textField = new TextField();
		Button button = new Button("Add to total");
		Label label = new Label();
		HBox hBox = new HBox(textField, button, label);
		Scene scene = new Scene(hBox);
		stage.setScene(scene);
		stage.show();


		JavaFxObservable.actionEventsOf(button)
		.map(event -> textField.getText())
		.map(Integer::valueOf)
		.scan(0, (current, next) -> current + next)
		.map(String::valueOf)
		.subscribe(label::setText);
	}

}
