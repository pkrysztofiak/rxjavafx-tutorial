package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Solution006 extends Application {

	private final Label label = new Label();
	private final ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta", "Epsilon"));
	private final VBox vBox = new VBox(label, comboBox);
	private final StackPane stackPane = new StackPane(vBox);
	private final Scene scene = new Scene(stackPane, 400, 400);

	private final Observable<String> selectedObservable = JavaFxObservable.valuesOf(comboBox.valueProperty());

	{
		vBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(scene);
		stage.show();

		selectedObservable
		.map(String::length)
		.scan(0, (current, next) -> current + next)
		.map(String::valueOf)
		.subscribe(label::setText);
	}
}