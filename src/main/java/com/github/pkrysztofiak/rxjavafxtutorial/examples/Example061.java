package com.github.pkrysztofiak.rxjavafxtutorial.examples;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Example061 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ToggleButton toggleButton = new ToggleButton("Start");
		Label label = new Label("0");
		HBox hBox = new HBox(toggleButton, label);
		Scene scene = new Scene(hBox);
		stage.setScene(scene);
		stage.show();

		JavaFxObservable.valuesOf(toggleButton.selectedProperty())
		.switchMap(selected -> {
			if (selected) {
				toggleButton.setText("Stop");
				return Observable.interval(1, TimeUnit.SECONDS).map(next -> next + 1);
			} else {
				label.setText("0");
				toggleButton.setText("Start");
				return Observable.empty();
			}
		})
		.observeOn(JavaFxScheduler.platform())
		.map(String::valueOf)
		.subscribe(label::setText);
	}
}