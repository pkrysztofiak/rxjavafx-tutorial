package com.github.pkrysztofiak.rxjavafxtutorial.examples2;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Example023 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ListView<String> listView = new ListView<>(FXCollections.observableArrayList(IntStream.range(0, 10).boxed().map(String::valueOf).collect(Collectors.toList())));
		Scene scene = new Scene(listView);
		stage.setScene(scene);
		stage.show();

		listView.requestFocus();

		JavaFxObservable.eventsOf(listView, KeyEvent.KEY_TYPED)
		.map(KeyEvent::getCharacter)
		.filter(character -> character.matches("[0-9]"))
		.subscribe(listView.getSelectionModel()::select);
	}
}