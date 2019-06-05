package com.github.pkrysztofiak.rxjavafxtutorial.solutions;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Solution007 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ListView<String> listView = new ListView<>();
		ListView<Integer> distincSortedLenghtsListView = new ListView<>();
		HBox hBox = new HBox(listView, distincSortedLenghtsListView);
		TextField textField = new TextField();
		Button button = new Button("Add");
		HBox hBox2 = new HBox(textField, button);
		VBox vBox = new VBox(hBox, hBox2);
		Scene scene = new Scene(vBox);
		stage.setScene(scene);
		stage.show();

		JavaFxObservable.actionEventsOf(button).map(event -> textField.getText()).subscribe(listView.getItems()::add);

		JavaFxObservable.emitOnChanged(listView.getItems())
		.flatMapSingle(items -> Observable.fromIterable(items).map(String::length).distinct().sorted().toList())
		.subscribe(distincSortedLenghtsListView.getItems()::setAll);
	}
}