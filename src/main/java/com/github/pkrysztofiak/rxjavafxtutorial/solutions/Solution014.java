package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Solution014 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ListView<String> listView = new ListView<>(FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta"));
		ListView<String> lettersListView = new ListView<>();
		HBox hBox = new HBox(listView, lettersListView);
		Scene scene = new Scene(hBox);
		stage.setScene(scene);
		stage.show();

		JavaFxObservable.valuesOf(listView.getSelectionModel().selectedItemProperty())
		.flatMapSingle(letter -> Observable.fromArray(letter.split("(?!^)")).toList())
		.delay(3, TimeUnit.SECONDS, Schedulers.io())
		.observeOn(JavaFxScheduler.platform())
		.subscribe(lettersListView.getItems()::setAll);
	}
}