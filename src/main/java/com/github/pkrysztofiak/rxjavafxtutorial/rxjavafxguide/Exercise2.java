package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Exercise2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().setAll("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
		StackPane stackPane = new StackPane(comboBox);
		stackPane.setPrefSize(400, 400);
		stage.setScene(new Scene(stackPane));
		stage.show();

		JavaFxObservable.valuesOf(comboBox.valueProperty())
		.subscribe(System.out::println);
	}
}