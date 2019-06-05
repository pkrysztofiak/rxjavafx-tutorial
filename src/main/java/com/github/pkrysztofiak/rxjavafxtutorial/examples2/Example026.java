package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Example026 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta", "Epsilon"));
		StackPane stackPane = new StackPane(comboBox);
		Scene scene = new Scene(stackPane, 400, 400);
		stage.setScene(scene);
		stage.show();
		JavaFxObservable.nullableValuesOf(comboBox.valueProperty()).subscribe(optional -> System.out.println(optional.orElse("N/A")));
	}
}