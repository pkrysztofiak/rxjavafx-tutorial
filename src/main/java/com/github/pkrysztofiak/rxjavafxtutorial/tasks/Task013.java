package com.github.pkrysztofiak.rxjavafxtutorial.tasks;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Task013 extends Application {

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

		//your code here
	}
}