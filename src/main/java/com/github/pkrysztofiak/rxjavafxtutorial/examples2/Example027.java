package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Example027 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		TextField textField = new TextField();
		Scene scene = new Scene(textField);
		stage.setScene(scene);
		stage.show();
		JavaFxObservable.changesOf(textField.textProperty())
		.map(change -> change.getNewVal().matches("[0-9]+") ? change.getNewVal() : change.getOldVal())
		.subscribe(textField::setText);
	}
}