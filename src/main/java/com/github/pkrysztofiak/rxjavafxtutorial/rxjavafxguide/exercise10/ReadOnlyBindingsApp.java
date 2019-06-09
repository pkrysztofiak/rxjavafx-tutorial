package com.github.pkrysztofiak.rxjavafxtutorial.rxjavafxguide.exercise10;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.observers.JavaFxObserver;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReadOnlyBindingsApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		Label label = new Label("days off:");
		TextField textField = new TextField();
		HBox hBox = new HBox(label, textField);
		Scene scene = new Scene(hBox);
		stage.setScene(scene);
		stage.show();

		Employee employee = new Employee("John", 28);

		Observable<String> textObservable = JavaFxObservable.valuesOf(textField.textProperty());

		Binding<Number> binding = JavaFxObserver.toBinding(textObservable.filter(text -> text.matches("[0-9]+")).map(Integer::valueOf));

	}
}